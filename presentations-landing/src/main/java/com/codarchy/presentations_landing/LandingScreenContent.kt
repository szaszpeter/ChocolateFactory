package com.codarchy.presentations_landing

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.codarchy.common.ErrorContent
import com.codarchy.common.Loading
import com.codarchy.presentations_landing.views.EmptyScreen
import com.codarchy.presentations_landing.views.SearchBar
import com.codarchy.presentations_landing.views.CharacterList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LandingScreenContent(viewModel: LandingViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { SearchBar() },
        scaffoldState = scaffoldState
    ) {
        viewModel.state.value.let { state ->
            when (state) {
                is Loading -> Loading()
                is NetworkError -> ErrorContent()
                is GenericError -> ErrorContent()
                is CharacterListReady -> CharacterList()
                is Empty -> EmptyScreen()
            }
        }
    }
}