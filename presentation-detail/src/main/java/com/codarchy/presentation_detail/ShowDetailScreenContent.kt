package com.codarchy.presentation_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.codarchy.data.model.PersonDetails

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowDetailScreenContent(viewModel: ShowDetailViewModel = hiltViewModel()) {
    viewModel.state.value.let { state ->
        when (state) {
            is ShowDetailsReady -> ShowDetails(person = state.person)
            is Empty -> {}
        }
    }
}

@Composable
fun ShowDetails(person: PersonDetails) {
    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        Row {
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(240.dp)
                    .background(Color.Black)
            ) {
                AsyncImage(
                    model = person.image,
                    contentDescription = stringResource(R.string.show_image),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.DarkGray)
                )
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Title(person = person)
            }
        }
    }
}

@Composable
fun Title(person: PersonDetails) {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                ) {
                    append(person.firstName)
                }
            }
        },
        modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
    )
}