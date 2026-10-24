package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.steamapps.companion.designsystem.theme.Palette

data class Bar(
    val label: String,
    val value: Float,
)

@Composable
fun BarChart(
    bars: List<Bar>,
    modifier: Modifier = Modifier,
    valueSuffix: String = "",
) {
    val max = bars.maxOfOrNull { it.value }?.takeIf { it > 0f } ?: 1f
    Column(modifier.fillMaxWidth()) {
        bars.forEach { bar ->
            val fraction = (bar.value / max).coerceIn(0f, 1f)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    bar.label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(0.42f),
                    maxLines = 1,
                )
                Canvas(
                    Modifier
                        .weight(0.38f)
                        .height(8.dp)
                        .padding(horizontal = 8.dp),
                ) {
                    drawRoundRect(
                        color = Palette.BorderSubtle,
                        cornerRadius = CornerRadius(8f, 8f),
                    )
                    drawRoundRect(
                        color = Palette.Up,
                        size = Size(size.width * fraction, size.height),
                        cornerRadius = CornerRadius(8f, 8f),
                    )
                }
                Text(
                    "${formatChartValue(bar.value)}$valueSuffix",
                    style = MaterialTheme.typography.labelSmall,
                    color = Palette.Up,
                    modifier = Modifier.weight(0.2f),
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
fun Sparkline(
    points: List<Float>,
    modifier: Modifier = Modifier,
) {
    if (points.size < 2) return
    val min = points.min()
    val max = points.max()
    val span = (max - min).takeIf { it > 0f } ?: 1f
    Canvas(modifier.fillMaxWidth().height(72.dp)) {
        val path = Path()
        points.forEachIndexed { index, value ->
            val x = size.width * index / (points.lastIndex)
            val y = size.height - ((value - min) / span) * size.height * 0.85f - size.height * 0.08f
            if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        drawPath(
            path = path,
            color = Palette.Up,
            style = Stroke(width = 4f, cap = StrokeCap.Round),
        )
        val lastX = size.width
        val lastY = size.height - ((points.last() - min) / span) * size.height * 0.85f - size.height * 0.08f
        drawCircle(Palette.Up, radius = 6f, center = Offset(lastX, lastY))
    }
}

private fun formatChartValue(value: Float): String =
    if (value >= 100f) value.toInt().toString() else String.format("%.1f", value)
