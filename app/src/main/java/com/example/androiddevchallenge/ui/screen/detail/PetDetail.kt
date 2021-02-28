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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PetID
import com.example.androiddevchallenge.ui.screen.detail.child_composables.PetCTA
import com.example.androiddevchallenge.ui.screen.detail.child_composables.PetCharacteristics
import com.example.androiddevchallenge.ui.screen.detail.child_composables.PetDescription
import com.example.androiddevchallenge.ui.screen.detail.child_composables.PetInfo
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.favorite

@Composable
fun PetDetail(id: PetID, onBackPressed: () -> Unit) {
    val viewModel = viewModel(
        key = "pet_$id",
        modelClass = PetDetailViewModel::class.java,
        factory = PetDetailViewModelFactory(id)
    )

    val viewState by viewModel.state.collectAsState()
    val pet = viewState?.pet ?: return

    Box {
        TopAppBar(
            title = {},
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = null
                    )
                }
            },
            actions = {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(50))
                        .clickable { viewModel.toggleFavorite(pet.id) }
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        tint = if (pet.isFavorite) favorite else LocalContentColor.current,
                        painter = painterResource(R.drawable.ic_heart),
                        contentDescription = null
                    )
                }
            }
        )

        Image(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopCenter,
            painter = painterResource(pet.image),
            contentDescription = null
        )
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier.fillMaxHeight(0.6F)
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))

                        PetInfo(pet)
                        Spacer(modifier = Modifier.height(24.dp))

                        PetCharacteristics(pet)
                        Spacer(modifier = Modifier.height(24.dp))

                        PetDescription(pet)
                        Spacer(modifier = Modifier.height(24.dp))

                        PetCTA()
                    }
                }
            }
        }
    }
}

@Preview("Pet Detail", widthDp = 360, heightDp = 640)
@Composable
private fun Preview() {
    MyTheme {
        PetDetail(
            id = PetID(1),
            onBackPressed = {}
        )
    }
}
