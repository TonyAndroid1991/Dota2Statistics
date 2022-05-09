package com.example.dota2statistics.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.data.models.byPersonaName.PlayerByPersonaNameItem
import com.example.dota2statistics.domain.useCases.GetPlayerByIDUseCase
import com.example.dota2statistics.domain.useCases.GetPlayersByPersonaNameUseCase
import kotlinx.coroutines.launch

class EmptyHomeViewModel(
    private val getPlayerByIDUseCase: GetPlayerByIDUseCase,
    private val getPlayersByPersonaNameUseCase: GetPlayersByPersonaNameUseCase
) : ViewModel() {

    private val _listOfPlayersByNameLiveData: MutableLiveData<List<PlayerByPersonaNameItem>> =
        MutableLiveData()
    val listOfPlayersByNameLiveData: LiveData<List<PlayerByPersonaNameItem>> = _listOfPlayersByNameLiveData

    private val _playerByIdLiveData: MutableLiveData<Profile> = MutableLiveData()
    val playerByIDLiveData: LiveData<Profile> = _playerByIdLiveData

    fun getPlayersList(playerName: String) = viewModelScope.launch {
        val playersList = getPlayersByPersonaNameUseCase.getPlayersByName(playerName)
        if (playersList != null) {
            _listOfPlayersByNameLiveData.postValue(playersList)
        }
    }

    fun getPlayerProfileByID(playerID: Long) = viewModelScope.launch {
        _playerByIdLiveData.postValue(getPlayerByIDUseCase.getPlayerById(playerID))
    }
}