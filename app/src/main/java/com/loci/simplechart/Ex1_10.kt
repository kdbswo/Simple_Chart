package com.loci.simplechart

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loci.simplechart.ui.theme.SimpleChartTheme

@Composable
fun Ex1_10() {

    val items = listOf(
        ChartModel(fraction = 0.2f, color = Color.Red),
        ChartModel(fraction = 0.3f, color = Color.Green),
        ChartModel(fraction = 0.5f, color = Color.Black)
    )

    var animationProgress by remember {
        mutableStateOf(0f)
    }

    val animationState by animateFloatAsState(
        targetValue = animationProgress,
        animationSpec = tween(durationMillis = 3000, easing = LinearOutSlowInEasing),
        label = ""
    )

    LaunchedEffect(Unit) {
        animationProgress = 1f
    }

    var startAngle = 0f

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(modifier = Modifier.size(300.dp)) {

            items.forEach {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawArc(
                        color = it.color,
                        startAngle = startAngle,
                        sweepAngle = it.fraction * animationState * 360f - 1f,
                        useCenter = false,
                        size = Size(size.width, size.height),
                        style = Stroke(width = 150f)
                    )

                    startAngle += it.fraction * 360f

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Ex1_10_Preview() {
    SimpleChartTheme {
        Ex1_10()
    }
}