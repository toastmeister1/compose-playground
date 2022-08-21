package com.joseph.composeplayground.ui.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun TestMain(
    text1: String = "Hello",
    text2: String = "There",
    modifier: Modifier = Modifier
) {
    var leftCount by remember { mutableStateOf(1) }
    var rightCount by remember { mutableStateOf(1) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = modifier.height(IntrinsicSize.Max)) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
                    .wrapContentWidth(Alignment.Start),
                text = text1.repeat(leftCount)
            )
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
                    .wrapContentWidth(Alignment.End),
                text = text2.repeat(rightCount)
            )
        }

        Spacer(Modifier.height(200.dp))

        Row {
            Button(
                onClick = { ++leftCount },
                content = { Text(text = "left") }
            )
            Button(
                onClick = { ++rightCount },
                content = { Text(text = "right") }
            )
        }

        Row {
            Button(
                onClick = { --leftCount },
                content = { Text(text = "--left") }
            )
            Button(
                onClick = { --rightCount },
                content = { Text(text = "--right") }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BoxWithConstraintsTest(
    modifier: Modifier = Modifier
) {
    var height by remember { mutableStateOf(200) }

    Column(Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(height.dp)
                .background(color = Color.Magenta)
        ) {

        }

        BoxWithConstraints(Modifier.background(Color.Yellow)) {
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = "Size [Height : ${minHeight}, Width ${minWidth}] \n Size [Height : ${maxHeight}, Width ${maxWidth}]"
            )
        }

        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { if (height >= 50) height -= 50 },
                content = { Text(text = "--height") }
            )

            Button(
                onClick = { height += 50 },
                content = { Text(text = "++height") }
            )
        }
    }

}