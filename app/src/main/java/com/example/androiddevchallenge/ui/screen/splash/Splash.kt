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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun Splash(onStartClick: () -> Unit) {
    val viewModel = viewModel(SplashViewModel::class.java)
    val viewState by viewModel.state.collectAsState()
    val pet = viewState?.pet ?: return

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (image, infoContainer) = createRefs()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .constrainAs(infoContainer) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                modifier = Modifier.padding(top = 48.dp),
                text = stringResource(R.string.splash_title),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4
            )

            CompositionLocalProvider(LocalContentAlpha provides 0.4F) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(R.string.splash_subtitle),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1
                )
            }
            Button(
                modifier = Modifier.padding(vertical = 32.dp),
                onClick = onStartClick,
                shape = RoundedCornerShape(50)
            ) {
                Text(text = stringResource(R.string.splash_start))
            }
        }

        Image(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 64.dp)
                    bottom.linkTo(infoContainer.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                },
            contentScale = ContentScale.Fit,
            painter = painterResource(pet.image),
            contentDescription = null
        )
    }
}

@Preview("Splash", widthDp = 360, heightDp = 640)
@Composable
private fun Preview() {
    MyTheme {
        Splash(onStartClick = {})
    }
}
