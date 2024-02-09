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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import coil.compose.AsyncImage
import com.codarchy.data.model.PersonDetails
import com.codarchy.presentations_search.R
import com.codarchy.presentations_landing.LandingViewModel

@Composable
fun EmployeeList(viewModel: LandingViewModel = hiltViewModel()) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        items(viewModel.data) {
            PersonDetail(it)
        }
    }
}

@Composable
fun PersonDetail(person: PersonDetails, viewModel: LandingViewModel = hiltViewModel()) {
    val view = LocalView.current
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                viewModel.onPersonClicked(person)
                val navOptions =
                    NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_right)
                        .setExitAnim(R.anim.slide_out_left)
                        .setPopEnterAnim(R.anim.slide_in_left)
                        .setPopExitAnim(R.anim.slide_out_right)
                        .build()
                val request = NavDeepLinkRequest.Builder
                    .fromUri("android-app://com.codarchy.presentationdetail.details".toUri())
                    .build()
                Navigation.findNavController(view).navigate(request, navOptions)
            }
            .fillMaxWidth()
            .background(Color.Transparent, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {

            AsyncImage(
                model = person.image,
                contentDescription = "Show Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(240.dp)
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
                            append(person.firstName)
                        }
                    }
                },
                modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
                maxLines = 1
            )
        }
    }
}