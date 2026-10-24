package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Palette

@Composable
fun Sparkline(
    values: List<Float>,
    modifier: Modifier = Modifier,
    height: Dp = 56.dp,
    lineColor: Color = Palette.Orange,
    showFill: Boolean = true,
    showEndDot: Boolean = true,
    showAxisTicks: Boolean = false,
    smoothCurve: Boolean = false,
    lineWidth: Dp = 2.5.dp,
) {
    if (values.size < 2) return
    val muted = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.25f)
    val prepared = remember(values) { values }
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
    ) {
        val w = size.width
        val h = size.height
        val padY = 6.dp.toPx()
        val min = prepared.minOrNull() ?: 0f
        val max = prepared.maxOrNull() ?: min
        val range = (max - min).coerceAtLeast(max * 0.02f).coerceAtLeast(0.01f)
        fun yAt(v: Float): Float {
            val t = (v - min) / range
            return h - padY - t * (h - padY * 2)
        }
        val stepX = w / (prepared.size - 1).coerceAtLeast(1)
        val points = prepared.mapIndexed { i, v ->
            Offset(i * stepX, yAt(v))
        }
        val linePath = Path().apply {
            if (points.isEmpty()) return@apply
            moveTo(points.first().x, points.first().y)
            if (!smoothCurve || points.size < 3) {
                points.drop(1).forEach { lineTo(it.x, it.y) }
            } else {
                for (i in 0 until points.lastIndex) {
                    val p0 = points[i]
                    val p1 = points[i + 1]
                    val midX = (p0.x + p1.x) / 2f
                    cubicTo(midX, p0.y, midX, p1.y, p1.x, p1.y)
                }
            }
        }
        if (showFill) {
            val fillPath = Path().apply {
                addPath(linePath)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(
                fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        lineColor.copy(alpha = 0.5f),
                        Palette.GoldFill.copy(alpha = 0.35f),
                        lineColor.copy(alpha = 0.02f),
                    ),
                    startY = 0f,
                    endY = h,
                ),
            )
        }
        drawPath(
            linePath,
            color = lineColor,
            style = Stroke(width = lineWidth.toPx(), cap = StrokeCap.Round),
        )
        if (showEndDot) {
            val lastX = points.last().x
            val lastY = points.last().y
            drawLine(
                color = muted,
                start = Offset(lastX, lastY),
                end = Offset(lastX, h),
                strokeWidth = 1.dp.toPx(),
            )
            drawCircle(color = lineColor, radius = 4.dp.toPx(), center = Offset(lastX, lastY))
            drawCircle(color = Color.White, radius = 2.dp.toPx(), center = Offset(lastX, lastY))
        }
        if (showAxisTicks) {
            val tickCount = 12
            val tickStep = w / tickCount
            for (i in 0..tickCount) {
                val x = i * tickStep
                drawLine(
                    color = muted,
                    start = Offset(x, h - 2.dp.toPx()),
                    end = Offset(x, h),
                    strokeWidth = 1.dp.toPx(),
                )
            }
        }
    }
}
