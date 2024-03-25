//package ru.iji.sunscrypt.data.repository
//
//import kotlinx.coroutines.flow.flow
//import org.junit.jupiter.api.AfterEach
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import ru.iji.sunscrypt.common.Resource
//import ru.iji.sunscrypt.data.dto.CoinDto
//
//class CoinsRepositoryImplTest {
//
//    @BeforeEach
//    fun setUp() {
//    }
//
//    @AfterEach
//    fun tearDown() {
//    }
//
//    @Test
//    fun `should return list of coins`() {
//
//        val coin = CoinDto(
//            symbol = "test symbol",
//            isActive = false,
//            isNew = false,
//            name = "test name",
//            rank = 0L,
//            id = "test id",
//            type = "test type"
//        )
//        val coins = listOf(coin)
//        val resource = Resource.Success(coins)
//        val result = flow { emit(resource) }
//
////        Assertions.assertEquals(expected = "", actual = "")
//    }
//}