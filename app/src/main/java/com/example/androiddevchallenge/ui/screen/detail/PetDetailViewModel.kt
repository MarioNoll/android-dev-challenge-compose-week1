/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.data.PetID
import com.example.androiddevchallenge.data.PetRepository
import com.example.androiddevchallenge.di.Dependencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PetDetailViewModel(
    private val petID: PetID,
    private val petRepository: PetRepository = Dependencies.petRepository
) : ViewModel() {

    private val _state = MutableStateFlow<PetDetailViewState?>(null)

    val state: StateFlow<PetDetailViewState?>
        get() = _state

    init {
        viewModelScope.launch {
            petRepository.pets().collect { pets ->
                _state.value = pets
                    .first { it.id == petID }
                    .let { PetDetailViewState(it) }
            }
        }
    }

    fun toggleFavorite(id: PetID): Unit = petRepository.toggleFavorite(id)
}

data class PetDetailViewState(
    val pet: Pet
)

class PetDetailViewModelFactory(private val petID: PetID) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PetDetailViewModel(petID) as T
}
