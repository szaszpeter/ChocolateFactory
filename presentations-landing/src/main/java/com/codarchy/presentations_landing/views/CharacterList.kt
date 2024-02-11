package com.codarchy.presentations_landing.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.Navigation
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codarchy.common.extensions.generateImageRequest
import com.codarchy.data.model.CharacterDetails
import com.codarchy.presentations_landing.LandingViewModel
import com.codarchy.presentations_landing.extensions.navigateToDetails

@Composable
fun CharacterList(viewModel: LandingViewModel = hiltViewModel()) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        items(viewModel.filteredData) {
            CharacterItem(it)
        }
    }
}

@Composable
fun CharacterItem(character: CharacterDetails, viewModel: LandingViewModel = hiltViewModel()) {
    val view = LocalView.current
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                viewModel.onCharacterClicked(character)
                Navigation.findNavController(view).navigateToDetails()
            }
            .fillMaxWidth()
            .background(Color.Transparent, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).generateImageRequest(character.image),
                contentDescription = "Show Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(128.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 28.sp)) {
                        withStyle(
                            style = SpanStyle(
                                color = Color.LightGray,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                            )
                        ) {
                            append(character.firstName)
                        }
                    }
                },
                modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
                maxLines = 1
            )
        }
    }
}