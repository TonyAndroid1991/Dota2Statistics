package com.example.dota2statistics.presentation.viewmodels

import Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import com.example.dota2statistics.domain.useCases.GetPlayerByIDUseCase
import com.example.dota2statistics.domain.useCases.GetPlayersByPersonaNameUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class EmptyHomeViewModel(
    private val getPlayerByIDUseCase: GetPlayerByIDUseCase,
    private val getPlayersByPersonaNameUseCase: GetPlayersByPersonaNameUseCase
) : ViewModel() {

    private val _listOfPlayersByNameLiveData: MutableLiveData<Resource<List<PlayerByPersonaNameItem>>> =
        MutableLiveData()
    val listOfPlayersByNameLiveData: LiveData<Resource<List<PlayerByPersonaNameItem>>> =
        _listOfPlayersByNameLiveData

    private val _playerByIdLiveData: MutableLiveData<Resource<Profile>> = MutableLiveData()
    val playerByIDLiveData: LiveData<Resource<Profile>> = _playerByIdLiveData

    private val _listOfProfilesByID: MutableLiveData<Resource<List<Profile>>> = MutableLiveData()
    val listOfProfilesByID = _listOfProfilesByID

    fun getPlayersListByName(playerName: String) = viewModelScope.launch {
        _listOfPlayersByNameLiveData.postValue(Resource.Loading())
        val playersList = getPlayersByPersonaNameUseCase.getPlayersByName(playerName)
        _listOfPlayersByNameLiveData.postValue(playersList)
    }

    fun getPlayerProfileByID(playerID: Int) = viewModelScope.launch {
        _playerByIdLiveData.postValue(Resource.Loading())
        _playerByIdLiveData.postValue(getPlayerByIDUseCase.getPlayerById(playerID))
    }

    fun getPlayersProfileByName(playersByName: List<PlayerByPersonaNameItem>) =
        viewModelScope.launch {
            _listOfPlayersByNameLiveData.postValue(Resource.Loading())
            val playersProfileList: List<Profile> = playersByName.map { playerByName ->
                async {
                    getPlayerByIDUseCase.getPlayerById(playerByName.accountId).data
                }
            }.awaitAll().filterNotNull()
            if (playersProfileList.isNotEmpty()) {
                _listOfProfilesByID.postValue(Resource.Success(playersProfileList))
            }
        }
}