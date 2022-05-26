package com.example.dota2statistics.domain.useCases

import com.example.dota2statistics.PlayersRepositoryTestDouble
import com.example.dota2statistics.domain.repository.PlayersRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking


import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetPlayerByIDUseCaseTest {

    lateinit var sut: GetPlayerByIDUseCase
    lateinit var playersRepository: PlayersRepository


//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        playersRepository = PlayersRepositoryTestDouble()
        sut = GetPlayerByIDUseCase(playersRepository)
    }

    @Test
    fun `get player with passed profile ID`() = runBlocking {
        var profile =  sut.getPlayerById(1111111111)
        assertThat(profile?.accountId).isEqualTo(1111111111)
    }

    @Test
    fun `get player avatar Name with passed profile ID`() = runBlocking {
        var profile =  sut.getPlayerById(1111111111)
        assertThat(profile?.avatar).isEqualTo("myAvatar")
    }

    @After
    fun tearDown() {
    }
}