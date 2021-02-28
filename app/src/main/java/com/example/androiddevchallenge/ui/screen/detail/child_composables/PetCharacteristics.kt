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
package com.example.androiddevchallenge.ui.screen.detail.child_composables

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.data.PetCharacteristic

@Composable
fun PetCharacteristics(pet: Pet) {
    Row {
        pet.characteristics.forEachIndexed { index, characteristic ->
            Box(
                modifier = Modifier
                    .weight(1F)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(characteristic.cardColor))

            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Icon(
                        painter = painterResource(characteristic.icon),
                        tint = Color(characteristic.accentColor),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = characteristic.toString(),
                        color = Color(characteristic.accentColor)
                    )
                }
            }

            if (index != pet.characteristics.indices.last) {
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

private val PetCharacteristic.icon: Int
    @DrawableRes get() = when (this) {
        PetCharacteristic.Friendly -> R.drawable.ic_paw
        PetCharacteristic.Neat -> R.drawable.ic_star_plus
        PetCharacteristic.Vocal -> R.drawable.ic_music
    }

private val PetCharacteristic.cardColor: Int
    @ColorInt get() = when (this) {
        PetCharacteristic.Friendly -> "#98D4BB".toColorInt()
        PetCharacteristic.Neat -> "#E5B3BB".toColorInt()
        PetCharacteristic.Vocal -> "#DDF2F4".toColorInt()
    }

private val PetCharacteristic.accentColor: Int
    @ColorInt get() = when (this) {
        PetCharacteristic.Friendly -> "#218B22".toColorInt()
        PetCharacteristic.Neat -> "#C54B6C".toColorInt()
        PetCharacteristic.Vocal -> "#4382BB".toColorInt()
    }
