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
package com.example.androiddevchallenge.ui.screen.overview.child_composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Gender
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.data.PetID
import com.example.androiddevchallenge.ui.theme.favorite

@Composable
fun PetsList(
    pets: List<Pet>,
    onPetClick: (PetID) -> Unit,
    toggleFavorite: (PetID) -> Unit
) {
    val cellCount = 2
    LazyVerticalGrid(
        cells = GridCells.Fixed(cellCount),
        contentPadding = PaddingValues(bottom = 24.dp, top = 8.dp)
    ) {
        itemsIndexed(pets) { index, pet ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(
                        start = if (index % cellCount == 0) 16.dp else 8.dp,
                        end = if (index % cellCount == 0) 8.dp else 16.dp,
                        bottom = 8.dp,
                        top = 8.dp
                    )
                    .clickable {
                        onPetClick(pet.id)
                    }
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        modifier = Modifier.height(150.dp),
                        contentScale = ContentScale.Inside,
                        painter = painterResource(pet.image),
                        contentDescription = null
                    )

                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        val (title, subTitle, gender) = createRefs()

                        Text(
                            text = pet.name,
                            style = MaterialTheme.typography.h6,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .constrainAs(title) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(gender.start)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        Text(
                            modifier = Modifier
                                .constrainAs(subTitle) {
                                    top.linkTo(title.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(gender.start)
                                    width = Dimension.fillToConstraints
                                },
                            text = pet.breed,
                            style = MaterialTheme.typography.body1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Icon(
                            modifier = Modifier
                                .size(32.dp)
                                .constrainAs(gender) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                },
                            painter = painterResource(pet.gender.drawableRes),
                            contentDescription = null
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(8.dp)
                ) {
                    FloatingActionButton(
                        onClick = { toggleFavorite(pet.id) },
                        modifier = Modifier.requiredSize(32.dp),
                        shape = CircleShape
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_heart),
                            tint = if (pet.isFavorite) favorite else Color.White,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

private val Gender.drawableRes: Int
    @DrawableRes get() = when (this) {
        Gender.Male -> R.drawable.ic_gender_male
        Gender.Female -> R.drawable.ic_gender_female
    }
