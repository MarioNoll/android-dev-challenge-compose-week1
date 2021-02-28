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
package com.example.androiddevchallenge.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.data.PetRepository
import com.example.androiddevchallenge.di.Dependencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SplashViewModel(
    private val petRepository: PetRepository = Dependencies.petRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SplashState?>(null)

    val state: StateFlow<SplashState?>
        get() = _state

    init {
        viewModelScope.launch {
            petRepository.pets().map { pets ->
                SplashState(
                    pet = pets.random()
                )
            }.collect {
                _state.value = it
            }
        }
    }
}

data class SplashState(
    val pet: Pet
)
