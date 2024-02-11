package com.codarchy

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.mapping.toCharacterResult
import com.codarchy.data.mapping.toCharacter
import com.codarchy.data.network.CharacterDetails
import com.codarchy.data.network.Favorite
import com.codarchy.data.network.CharacterResponse
import com.codarchy.domain.list.CharactersInventoryUseCase
import com.codarchy.domain.selection.SaveSelectedCharacterUseCase
import com.codarchy.presentations_landing.CharacterListReady
import com.codarchy.presentations_landing.Empty
import com.codarchy.presentations_landing.GenericError
import com.codarchy.presentations_landing.LandingViewModel
import com.codarchy.presentations_landing.NetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class LandingViewModelTest {

    private lateinit var viewModel: LandingViewModel
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Mock
    lateinit var charactersInventoryUseCase: CharactersInventoryUseCase

    @Mock
    lateinit var saveSelectedCharacterUseCase: SaveSelectedCharacterUseCase

    val favorite = Favorite("Blue", "Pizza", "Random", "Imagine")
    val testCharacterDetails = CharacterDetails(
        firstName = "John",
        lastName = "Doe",
        favorite = favorite,
        gender = "Male",
        image = "https://example.com/image.jpg",
        profession = "Engineer",
        email = "john.doe@example.com",
        age = 30,
        country = "USA",
        height = 180,
        id = 123
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = LandingViewModel(
            charactersInventoryUseCase,
            saveSelectedCharacterUseCase,
            testDispatcher
        )
    }

    @Test
    fun `test fetchCharacters success`() = runTest {
        val mockCharacterResponse = ResultWrapper.Success(
            CharacterResponse(
                1,
                1,
                listOf(testCharacterDetails)
            ).toCharacterResult()
        )
        Mockito.`when`(charactersInventoryUseCase.invoke(1)).thenReturn(mockCharacterResponse)

        viewModel.fetchCharacters()

        assertEquals(CharacterListReady(mockCharacterResponse.value.results), viewModel.state.value)
    }

    @Test
    fun `test fetchCharacters empty`() = runTest {
        val mockCharacterResponse =
            ResultWrapper.Success(CharacterResponse(1, 0, emptyList()).toCharacterResult())
        Mockito.`when`(charactersInventoryUseCase.invoke(1)).thenReturn(mockCharacterResponse)

        viewModel.fetchCharacters()

        assertEquals(viewModel.state.value, Empty)
    }

    @Test
    fun `test fetchCharacters network error`() = runTest {
        val mockCharacterResponse = ResultWrapper.NetworkError
        Mockito.`when`(charactersInventoryUseCase.invoke(1)).thenReturn(mockCharacterResponse)

        viewModel.fetchCharacters()

        assertEquals(viewModel.state.value, NetworkError)
    }

    @Test
    fun `test fetchCharacters generic error`() = runTest {
        val mockCharacterResponse = ResultWrapper.GenericError(500)
        Mockito.`when`(charactersInventoryUseCase.invoke(1)).thenReturn(mockCharacterResponse)

        viewModel.fetchCharacters()

        assertEquals(viewModel.state.value, GenericError)
    }

    @Test
    fun `test onCharacterClicked`() {
        viewModel.onCharacterClicked(testCharacterDetails.toCharacter())
        verify(saveSelectedCharacterUseCase).invoke(testCharacterDetails.toCharacter())
    }

    @Test
    fun `test onSearch`() = runTest {
        val query = "John"
        val data = listOf(testCharacterDetails.toCharacter())

        val mockCharacterResponse = ResultWrapper.Success(
            CharacterResponse(
                1,
                1,
                listOf(testCharacterDetails)
            ).toCharacterResult()
        )
        Mockito.`when`(charactersInventoryUseCase.invoke(1)).thenReturn(mockCharacterResponse)

        viewModel.fetchCharacters()

        viewModel.filteredData.addAll(data)

        viewModel.onSearch(query)

        assertEquals(listOf(testCharacterDetails.toCharacter()), viewModel.filteredData.toList())
    }
}
