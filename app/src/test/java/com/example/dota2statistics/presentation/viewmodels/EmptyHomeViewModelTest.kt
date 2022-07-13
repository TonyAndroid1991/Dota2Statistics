package com.example.dota2statistics.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dota2statistics.data.implementations.PlayersRepositoryDouble
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.domain.useCases.GetPlayerByIDUseCase
import com.example.dota2statistics.domain.useCases.GetPlayersByPersonaNameUseCase
import com.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class EmptyHomeViewModelTest {

    /**Esta regla ejecuta todos los trabajos en background en el mismo hilo
     * de esta forma todos los resultados de lso test ocurren sincronizados */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var sut: EmptyHomeViewModel
    private lateinit var playersRepositoryDouble: PlayersRepositoryDouble

    @Before
    fun setUp() {
        playersRepositoryDouble = PlayersRepositoryDouble()
        val getPlayerByIDUseCase = GetPlayerByIDUseCase(playersRepositoryDouble)
        val getPlayersByPersonaNameUseCase = GetPlayersByPersonaNameUseCase(playersRepositoryDouble)
        sut = EmptyHomeViewModel(getPlayerByIDUseCase, getPlayersByPersonaNameUseCase)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `check liveData is updated with same ID`() {
        sut.getPlayerProfileByID(12345)
        var expected = sut.playerByIDLiveData.getOrAwaitValue()
        assertThat(expected.accountId).isEqualTo(12345)
    }

    @Test
    fun `check liveData is updated with list of players with names`() = runBlocking{
        var listOfPlayersToCompare = playersRepositoryDouble.getPlayersByPersonaName("atila")
        sut.getPlayersListByName("atila")
        var expected = sut.listOfPlayersByNameLiveData.getOrAwaitValue()

        //assertThat(listOfPlayersToCompare?.size).isEqualTo(expected.size)
        assertThat(listOfPlayersToCompare?.get(0)?.personaname).isEqualTo(expected[0].personaname)
    }
}