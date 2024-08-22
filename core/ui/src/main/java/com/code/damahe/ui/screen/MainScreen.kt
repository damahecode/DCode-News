/*
 * Copyright (c) 2024 damahecode.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.code.damahe.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.code.damahe.res.R
import com.code.damahe.res.icon.DCodeResource.VectorResource
import com.code.damahe.res.icon.MyIcons
import com.code.damahe.material.theme.DCodeBackground
import com.code.damahe.material.theme.DCodeGradientBackground
import com.code.damahe.material.dialogs.ThemeDialog
import com.code.damahe.material.theme.LocalGradientColors
import com.code.damahe.res.icon.DrawIcon
import com.code.damahe.system.utils.PreferenceUtil
import com.code.damahe.ui.screen.main.navigation.MainHost
import com.code.damahe.ui.screen.main.navigation.rememberMainAppState
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun MainScreen() {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val showThemeSettingsDialog = remember { mutableStateOf(false) }
    val preferenceUtil = PreferenceUtil(context)
    val checkOnBoardingStatus = preferenceUtil.checkOnBoardingStatus.collectAsState(initial = true)

    if (showThemeSettingsDialog.value) {
        ThemeDialog(
            onDismiss = {showThemeSettingsDialog.value = false},
        )
    }

    DCodeBackground {
        DCodeGradientBackground(gradientColors = LocalGradientColors.current) {
            if (checkOnBoardingStatus.value) {
                OnBoardingScreen(onBack = {
                    coroutineScope.launch {
                        preferenceUtil.saveOnBoardingStatus(false)
                    }
                })
            } else {
                Scaffold(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    contentWindowInsets = WindowInsets(0),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.app_name)) },
                            navigationIcon = {
                                IconButton(onClick = { showThemeSettingsDialog.value = true }
                                ) {
                                    DrawIcon(icon = VectorResource(MyIcons.Settings), contentDescription = stringResource(id = R.string.txt_preferences))
                                }
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.Transparent,
                            ),
                        )
                    },
                ) { padding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .consumeWindowInsets(padding)
                            .windowInsetsPadding(
                                WindowInsets.safeDrawing.only(
                                    WindowInsetsSides.Horizontal,
                                ),
                            )
                            .navigationBarsPadding(),
                    ) {
                        MainHost(mainAppState = rememberMainAppState())
                    }
                }
            }
        }
    }
}