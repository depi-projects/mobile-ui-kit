package com.steamapps.companion.designsystem.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.lineModel
import kotlin.math.roundToInt

@Composable
fun LineChart(
    values: List<Float>,
    modifier: Modifier = Modifier,
    pointCaptions: List<String> = emptyList(),
) {
    if (values.isEmpty()) return
    val modelProducer = remember { CartesianChartModelProducer() }
    var selected by remember(values, pointCaptions) { mutableStateOf<Int?>(null) }
    LaunchedEffect(values) {
        modelProducer.runTransaction {
            lineModel { series(values) }
        }
    }
    Column(modifier) {
        val caption = selected?.let { pointCaptions.getOrNull(it) }
        if (!caption.isNullOrBlank()) {
            Text(
                caption,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 6.dp),
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) {
            CartesianChartHost(
                chart = rememberCartesianChart(
                    rememberLineCartesianLayer(),
                    startAxis = VerticalAxis.rememberStart(),
                    bottomAxis = HorizontalAxis.rememberBottom(),
                ),
                modelProducer = modelProducer,
                modifier = Modifier.fillMaxSize(),
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .pointerInput(values.size) {
                        detectTapGestures { offset ->
                            if (values.isEmpty()) return@detectTapGestures
                            val fraction = (offset.x / size.width).coerceIn(0f, 1f)
                            selected = (fraction * values.lastIndex).roundToInt().coerceIn(0, values.lastIndex)
                        }
                    },
            )
        }
    }
}
