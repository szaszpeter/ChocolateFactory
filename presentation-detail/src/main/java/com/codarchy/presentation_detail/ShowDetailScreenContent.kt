package com.codarchy.presentation_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codarchy.common.extensions.generateImageRequest
import com.codarchy.data.model.CharacterAdvancedDetails

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowDetailScreenContent(viewModel: ShowDetailViewModel = hiltViewModel()) {
    viewModel.state.value.let { state ->
        when (state) {
            is CharacterDetailsReady -> CharacterPage(character = state.person)
            is Empty -> {}
        }
    }
}

@Composable
fun CharacterPage(character: CharacterAdvancedDetails) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(Color.Black)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).generateImageRequest(character.image),
                contentDescription = stringResource(R.string.show_image),
                contentScale = ContentScale.None,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.DarkGray)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        ParagraphWithTag(
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
            tag = stringResource(id = R.string.name_tag),
            content = "${character.firstName} ${character.lastName}",
            fontSize = 24.sp
        )

        // Profession
        ParagraphWithTag(
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
            tag = stringResource(id = R.string.profession_tag),
            content = character.profession,
        )

        // Country
        ParagraphWithTag(
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
            tag = stringResource(id = R.string.country_tag),
            content = character.country,
        )

        // Description
        ParagraphWithTag(
            modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
            tag = stringResource(id = R.string.description_tag),
            content = character.description,
        )

        // Specs
        Box(
            modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 16.dp)
                .border(0.5.dp, Color.LightGray)
                .fillMaxWidth()
                .background(Color.Transparent),
            contentAlignment = Alignment.CenterStart
        ) {
            Row {
                ParagraphWithTag(
                    modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp),
                    tag = stringResource(id = R.string.height_tag),
                    content = character.height.toString(),
                )

                ParagraphWithTag(
                    modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp),
                    tag = stringResource(id = R.string.gender_tag),
                    content = character.gender,
                )
            }
        }

    }
}

@Composable
fun ParagraphWithTag(
    modifier: Modifier = Modifier,
    tag: String,
    content: String,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                withStyle(
                    style = SpanStyle(
                        color = Color.Yellow,
                        fontWeight = FontWeight.Light,
                        fontSize = fontSize
                    )
                ) {
                    append("$tag ")
                }

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Light,
                        color = Color.LightGray,
                        fontSize = fontSize
                    )
                ) {
                    append(HtmlCompat.fromHtml(content, 0))
                }
            }
        },
        modifier = modifier,
    )
}
