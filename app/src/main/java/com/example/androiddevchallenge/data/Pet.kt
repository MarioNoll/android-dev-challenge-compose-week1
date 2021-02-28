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

import androidx.annotation.DrawableRes

inline class PetID(val value: Long)

data class Pet(
    val id: PetID,
    val name: String,
    @DrawableRes
    val image: Int,
    val gender: Gender,
    val breed: String,
    val isFavorite: Boolean = false,
    val age: String,
    val info: String = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam",
    val distance: String = "2.6 km",
    val characteristics: List<PetCharacteristic> = listOf(
        PetCharacteristic.Friendly,
        PetCharacteristic.Neat,
        PetCharacteristic.Vocal
    )
)

enum class PetCharacteristic {
    Friendly,
    Neat,
    Vocal
}

enum class Gender {
    Male,
    Female
}
