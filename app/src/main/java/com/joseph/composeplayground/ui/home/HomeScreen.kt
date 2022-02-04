package com.joseph.composeplayground.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joseph.composeplayground.ui.theme.ComposePlaygroundTheme
import com.joseph.composeplayground.ui.theme.MainBlue
import com.joseph.composeplayground.ui.theme.Suit

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navigateToDetailScreen = { id -> id })
}

typealias MovieId = Int

@Composable
fun HomeScreen(
    navigateToDetailScreen: (MovieId) -> Unit
) {
    ComposePlaygroundTheme {
        Surface(
            color = MainBlue,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                TitleText()
                Spacer(modifier = Modifier.height(16.dp))
                SearchBar()
            }
        }
    }
}

@Preview
@Composable
fun TitleText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .height(20.dp),
            text = "Compose Playground",
            fontFamily = Suit,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .height(13.dp),
            text = "Jetpack Compose + MVI",
            fontFamily = Suit,
            fontSize = 10.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun SearchBar(
    onSearch: (String) -> Unit = { it }
) {
    val inputText by remember { mutableStateOf("Search Movie") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .height(34.dp)
            .background(Color(0xFFF0F0F0)),
    ) {
        Image(
            modifier = Modifier
                .size(34.dp)
                .padding(12.dp),
            painter = painterResource(id = com.joseph.composeplayground.R.drawable.ic_search),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color(0xFFA9A9A9))
        )

        BasicTextField(
            value = inputText,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .wrapContentHeight(),
            onValueChange = onSearch,
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = Suit,
                fontWeight = FontWeight.Light,
                color = Color(0xFFA9A9A9),
                textAlign = TextAlign.Start,
                fontSize = 10.sp
            ),
        )
    }
}