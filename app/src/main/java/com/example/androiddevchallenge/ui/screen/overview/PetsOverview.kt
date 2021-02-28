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
package com.example.androiddevchallenge.ui.screen.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PetID
import com.example.androiddevchallenge.ui.screen.overview.child_composables.PetsList
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PetsOverview(onPetClick: (PetID) -> Unit) {
    val viewModel = viewModel(PetsOverviewViewModel::class.java)
    val viewState by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = stringResource(R.string.pet_overview_title),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.ic_paw),
                tint = MaterialTheme.colors.primary,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = stringResource(R.string.pet_overview_favorite_filter),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1,
            )

            Spacer(modifier = Modifier.size(16.dp))

            Switch(
                checked = viewState.favoritesFilterEnabled,
                onCheckedChange = { viewModel.toggleFavoriteFilter() }
            )
        }

        PetsList(
            pets = viewState.pets,
            onPetClick = onPetClick,
            toggleFavorite = { viewModel.toggleFavorite(it) }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview("Pets Overview", widthDp = 360, heightDp = 640)
@Composable
private fun Preview() {
    MyTheme {
        PetsOverview(
            onPetClick = {}
        )
    }
}
