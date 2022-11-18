package com.example.dota2statistics.presentation.viewmodels

import Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dota2statistics.data.implementations.PlayersRepositoryDouble
import com.example.dota2statistics.domain.useCases.GetPlayerByIDUseCase
import com.example.dota2statistics.domain.useCases.GetPlayersByPersonaNameUseCase
import com.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class EmptyHomeViewModelTest {

    /**Esta regla ejecuta todos los trabajos en background en el mismo hilo
     * de esta forma todos los resultados de los tests ocurren sincronizados */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: EmptyHomeViewModel
    private lateinit var playersRepositoryDouble: PlayersRepositoryDouble
    private lateinit var getPlayerByIDUseCase: GetPlayerByIDUseCase
    private lateinit var getPlayersByPersonaNameUseCase: GetPlayersByPersonaNameUseCase

    @Before
    fun setUp() {
        playersRepositoryDouble = PlayersRepositoryDouble()
        getPlayerByIDUseCase = mock()
        getPlayersByPersonaNameUseCase = mock()
        sut = EmptyHomeViewModel(getPlayerByIDUseCase, getPlayersByPersonaNameUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `check liveData is updated with same ID`() {
        sut.getPlayerProfileByID(12345)
        val expected = sut.playerByIDLiveData.getOrAwaitValue().data
        assertThat(expected?.accountId).isEqualTo(12345)
    }

    @Test
    fun `check liveData is updated with list of players with names`() = runBlocking {
        val expected = playersRepositoryDouble.getPlayersByPersonaName("atila").data
        whenever(getPlayersByPersonaNameUseCase.getPlayersByName("atila"))
            .thenReturn(playersRepositoryDouble.getPlayersByPersonaName("atila").data?.let {
                Resource.Success(it)
            })
        sut.getPlayersListByName("atila")
        val result = sut.listOfPlayersByNameLiveData.getOrAwaitValue().data
        assertThat(expected?.get(0)?.personaname).isEqualTo(result?.get(0)?.personaname)
    }
}