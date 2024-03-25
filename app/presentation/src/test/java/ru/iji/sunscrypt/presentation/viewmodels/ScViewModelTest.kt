//package ru.iji.sunscrypt.presentation.viewmodels
//
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito.`when`
//import org.mockito.kotlin.mock
//import ru.iji.sunscrypt.common.Resource
//import ru.iji.sunscrypt.domain.models.CoinModel
//import ru.iji.sunscrypt.domain.usecases.GetCoins
//
//class ScViewModelTest {
//
//    private val getCoins = mock<GetCoins>()
//
//    @Test
//    fun `should get a flow of CoinState class`() {
//
//        val scViewModel = ScViewModel(getCoins = getCoins)
//        val expected = scViewModel.state.value.javaClass
//        val actual = CoinsState().javaClass
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `should get a flow of CoinState`() {
//
//        val v = getCoins.
//        val getCoins: Flow<Resource<List<CoinModel>>> = flow {
//            emit(Resource.Loading())
//        }
//
//        val scViewModel = ScViewModel(getCoins = this.getCoins)
//
//        `when`(scViewModel).thenReturn(scViewModel)
//
//        val expected = scViewModel.state.value.javaClass
//        val actual = CoinsState().javaClass
//
//        assertEquals(expected, actual)
//    }
//}