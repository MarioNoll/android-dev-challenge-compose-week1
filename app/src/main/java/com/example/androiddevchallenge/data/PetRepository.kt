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
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class PetRepository {

    private val favoritesState = MutableStateFlow<Set<PetID>>(
        emptySet()
    )

    fun pets(): Flow<List<Pet>> = favoritesState.map { favorites ->
        samplePets().map { pet ->
            pet.copy(isFavorite = favorites.contains(pet.id))
        }
    }

    fun toggleFavorite(petID: PetID) {
        favoritesState.value = favoritesState.value.toMutableSet().apply {
            if (!add(petID)) {
                remove(petID)
            }
        }
    }

    private fun samplePets(): List<Pet> = listOf(
        Pet(
            id = PetID(1),
            name = "Bella",
            age = "9 Months",
            breed = "Bengal",
            gender = Gender.Female,
            image = R.drawable.cat2,
        ),
        Pet(
            id = PetID(2),
            name = "Charlie",
            age = "2 Years",
            breed = "Boxer",
            gender = Gender.Male,
            image = R.drawable.dog1,
        ),
        Pet(
            id = PetID(3),
            name = "Molly",
            age = "1 Year",
            breed = "Beagle",
            gender = Gender.Female,
            image = R.drawable.dog2,
        ),
        Pet(
            id = PetID(4),
            name = "Oscar",
            age = "3 Months",
            breed = "Javanese",
            gender = Gender.Male,
            image = R.drawable.cat1,
        ),
        Pet(
            id = PetID(5),
            name = "Coco",
            age = "5 Weeks",
            breed = "Egyptian Mau",
            gender = Gender.Female,
            image = R.drawable.cat3
        ),
        Pet(
            id = PetID(6),
            name = "Jack",
            age = "10 Months",
            breed = "Dingo",
            gender = Gender.Male,
            image = R.drawable.dog3
        ),
        Pet(
            id = PetID(7),
            name = "Bello",
            age = "4 Months",
            breed = "Bulldog",
            gender = Gender.Male,
            image = R.drawable.dog4
        )
    )
}
