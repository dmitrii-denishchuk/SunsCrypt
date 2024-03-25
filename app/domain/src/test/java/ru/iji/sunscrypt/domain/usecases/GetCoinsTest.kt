//package ru.iji.sunscrypt.domain.usecases
//
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.flow.single
//import kotlinx.coroutines.test.runTest
//import org.junit.jupiter.api.AfterEach
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito
//import org.mockito.Mockito.`when`
//import org.mockito.kotlin.mock
//import ru.iji.sunscrypt.domain.models.CoinModel
//import ru.iji.sunscrypt.domain.repository.CoinsRepository
//
//class GetCoinsTest {
//
//    private val testCoinsRepository = mock<CoinsRepository>()
//
//    @AfterEach
//    fun `reset repository`() {
//        Mockito.reset(testCoinsRepository)
//    }
//
//    @Test
//    fun `should return loading class`() = runTest {
//
//        // arrange
//        val getCoins = GetCoins(coinsRepository = testCoinsRepository)
//        val actual = CoinState.Loading<List<CoinModel>>().javaClass
//        val loading = emptyList<CoinModel>()
//
//        `when`(testCoinsRepository.getCoins()).thenReturn(loading)
//
//        // act
//        val expected = getCoins().single().javaClass
//
//        // assert
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `should return success class`() = runTest {
//
//        // arrange
//        val getCoins = GetCoins(coinsRepository = testCoinsRepository)
//        val actual = CoinState.Success<List<CoinModel>>(emptyList()).javaClass
//        val success = emptyList<CoinModel>()
//
//        `when`(testCoinsRepository.getCoins()).thenReturn(success)
//
//        // act
//        val expected = getCoins().single().javaClass
//
//        // assert
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `should return error class`() = runTest {
//
//        // arrange
//        val getCoins = GetCoins(coinsRepository = testCoinsRepository)
//        val actual = CoinState.Error<List<CoinModel>>().javaClass
//        val error = emptyList<CoinModel>()
//
//        `when`(testCoinsRepository.getCoins()).thenReturn(error)
//
//        // act
//        val expected = getCoins().single().javaClass
//
//        // assert
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `should get a flow of an empty list of coins`() = runTest {
//
//        // arrange
//        val getCoins = GetCoins(coinsRepository = testCoinsRepository)
//        val actual = flowOf(CoinState.Success<List<CoinModel>>(emptyList())).single().data
//        val emptyCoinsList = emptyList<CoinModel>()
//
//        `when`(testCoinsRepository.getCoins()).thenReturn(emptyCoinsList)
//
//        // act
//        val expected = getCoins().single().data
//
//        // assert
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `should get an error message`() = runTest {
//
//        // arrange
//        val getCoins = GetCoins(coinsRepository = testCoinsRepository)
//        val actual = flowOf(CoinState.Error<List<CoinModel>>("test")).single().data
//        val errorMessage = emptyList<CoinModel>()
//
//        `when`(testCoinsRepository.getCoins()).thenReturn(errorMessage)
//
//        // act
//        val expected = getCoins().single().data
//
//        // assert
//        assertEquals(expected, actual)
//    }
//}