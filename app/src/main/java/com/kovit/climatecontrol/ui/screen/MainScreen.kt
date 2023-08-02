package com.kovit.climatecontrol.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kovit.climatecontrol.ui.data.AirPower
import com.kovit.climatecontrol.ui.data.AirTemp

@Composable
fun MainScreen(mainViewModel: MainScreenViewModel
) {
    Row(modifier = Modifier.fillMaxSize()) {
        // left temp control
        Column() {
            // Box holding left temp control
            IconButton(onClick = { mainViewModel.canbusCMD(AirTemp.LeftUp.getCanCmd()) }) {
                Icon(Icons.Outlined.Add, null)
            }
            Text(text = mainViewModel.leftTemp)
            IconButton(onClick = { mainViewModel.canbusCMD(AirTemp.LeftDown.getCanCmd()) }) {
                Icon(Icons.Outlined.Add, null)
            }
        }
        Column() {
            Row() {
                IconButton(onClick = { mainViewModel.canbusCMD(AirPower.Off.getCanCmd()) }) {
                    Icon(Icons.Outlined.ExitToApp, null)
                }
                IconButton(onClick = { mainViewModel.canbusCMD(AirPower.Auto.getCanCmd()) }) {
                    Icon(Icons.Outlined.ExitToApp, null)
                }
                IconButton(onClick = { mainViewModel.canbusCMD(AirTemp.Dual.getCanCmd()) }) {
                    Icon(Icons.Outlined.ExitToApp, null)
                }
            }

            Row() {

            }
        }
        Column() {
            IconButton(onClick = { mainViewModel.canbusCMD(AirTemp.RightUp.getCanCmd()) }) {
                Icon(Icons.Outlined.Add, null)
            }
            Text(text = mainViewModel.rightTemp)
            IconButton(onClick = { mainViewModel.canbusCMD(AirTemp.RightDown.getCanCmd()) }) {
                Icon(Icons.Outlined.Add, null)
            }
        }
    }
}