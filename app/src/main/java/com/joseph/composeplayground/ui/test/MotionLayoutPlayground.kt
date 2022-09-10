package com.joseph.composeplayground.ui.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.joseph.composeplayground.R


@Composable
@Preview(showBackground = true)
fun MotionLayoutPlayground(
    modifier: Modifier = Modifier,
) {

    var progress by remember { mutableStateOf(0F) }

    Column(
        modifier = modifier,
    ) {
        MotionLayoutWithMotionScene(
            modifier = Modifier.weight(1F),
            progress = progress
        )
        ButtonController(
            modifier = Modifier
                .weight(0.1F)
                .fillMaxWidth()
                .wrapContentHeight(),
            onPreviousButtonClicked = {
                if (progress >= 0.01F) {
                    progress -= 0.1F
                }
            },
            onNextButtonClicked = {
                if (progress <= 1F) {
                    progress += 0.1F
                }
            }
        )
        SlideController(
            modifier = Modifier
                .weight(0.1F)
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            progress = progress,
            onProgressChanged = { changedProgress ->
                progress = changedProgress
            }
        )
    }
}


@Composable
@Preview(showBackground = true)
fun ButtonController(
    modifier: Modifier = Modifier,
    onPreviousButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Button(
            onClick = onPreviousButtonClicked,
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 12.dp)
        ) {
            Text(text = "Decrease")
        }

        Button(
            onClick = onNextButtonClicked,
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 12.dp)
        ) {
            Text(text = "Increase")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SlideController(
    modifier: Modifier = Modifier,
    progress: Float = 0.0F,
    onProgressChanged: (Float) -> Unit = {},
) {
    Slider(
        modifier = modifier,
        value = progress,
        onValueChange = {
            onProgressChanged.invoke(it)
        },
    )
}


@OptIn(ExperimentalMotionApi::class)
@Preview(showBackground = true)
@Composable
fun MotionLayoutWithMotionScene(
    modifier: Modifier = Modifier,
    progress: Float = 0F
) {
    val context = LocalContext.current

    val motionSceneContent = remember{
        context.resources
            .openRawResource(R.raw.motion_layout_younha_page)
            .readBytes()
            .decodeToString()
    }

    val imagePainter = painterResource(R.drawable.younha_album)

    Column(modifier = modifier.fillMaxSize()) {
        MotionLayout(
            motionScene = MotionScene(motionSceneContent),
            progress = progress,
            modifier = Modifier
                .weight(1F)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(84.dp)
                    .layoutId("album_art_background")
                    .background(Color(57, 57, 57)),
            )

            Image(
                modifier = Modifier.layoutId("album_art"),
                painter = imagePainter,
                contentDescription = "YounHa"
            )
        }
    }
}



