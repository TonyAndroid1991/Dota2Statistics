package com.example.dota2statistics.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dota2statistics.domain.useCases.GetPlayerByIDUseCase
import com.example.dota2statistics.domain.useCases.GetPlayersByPersonaNameUseCase

class EmptyHomeViewModelFactory(
    private val getPlayerByIDUseCase: GetPlayerByIDUseCase,
    private val getPlayersByPersonaNameUseCase: GetPlayersByPersonaNameUseCase
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return EmptyHomeViewModel(getPlayerByIDUseCase, getPlayersByPersonaNameUseCase) as T
    }
}