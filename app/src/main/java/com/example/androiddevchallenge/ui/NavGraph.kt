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
package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.PetID
import com.example.androiddevchallenge.ui.screen.detail.PetDetail
import com.example.androiddevchallenge.ui.screen.overview.PetsOverview
import com.example.androiddevchallenge.ui.screen.splash.Splash

object Destinations {
    const val SPLASH = "splash"
    const val PETS_ROUTE = "pets"
    const val PET_DETAIL_ROUTE = "pet"
    const val PET_DETAIL_ID_KEY = "petId"
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navigationActions = remember(navController) { NavigationActions(navController) }

    NavHost(navController, startDestination = Destinations.SPLASH) {
        composable(Destinations.SPLASH) {
            Splash(onStartClick = navigationActions.onStartClick)
        }

        composable(Destinations.PETS_ROUTE) {
            PetsOverview(onPetClick = navigationActions.onPetClick)
        }

        composable(
            "${Destinations.PET_DETAIL_ROUTE}/{${Destinations.PET_DETAIL_ID_KEY}}",
            arguments = listOf(
                navArgument(Destinations.PET_DETAIL_ID_KEY) { type = NavType.LongType }
            )
        ) {
            val arguments = requireNotNull(it.arguments)
            PetDetail(
                id = PetID(arguments.getLong(Destinations.PET_DETAIL_ID_KEY)),
                onBackPressed = navigationActions.onPetDetailClose
            )
        }
    }
}

class NavigationActions(navController: NavHostController) {
    val onStartClick: () -> Unit = {
        navController.navigate(Destinations.PETS_ROUTE) {
            popUpTo(Destinations.SPLASH) { inclusive = true }
        }
    }

    val onPetClick: (PetID) -> Unit = { petId ->
        navController.navigate("${Destinations.PET_DETAIL_ROUTE}/${petId.value}")
    }

    val onPetDetailClose: () -> Unit = {
        navController.popBackStack()
    }
}
