package com.kovit.climatecontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aoe.fytcanbusmonitor.ModuleCodes
import com.aoe.fytcanbusmonitor.MsToolkitConnection
import com.kovit.climatecontrol.ui.screen.MainScreen
import com.kovit.climatecontrol.ui.screen.MainScreenViewModel
import com.kovit.climatecontrol.ui.theme.ClimateControlTheme
import com.ryansteckler.lx470climate.IPCConnection
import com.ryansteckler.lx470climate.ModuleCallback

class MainActivity : ComponentActivity() {

    private var mainViewModel = MainScreenViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClimateControlTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen(mainViewModel)
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        ModuleCallback.init(this, mainViewModel)
        connectMain()
        connectCanbus()
        connectSound()
        connectCanUp()
        MsToolkitConnection.instance.connect(this)

    }

    private fun connectMain() {
        val callback = ModuleCallback("Main")
        val connection = IPCConnection(ModuleCodes.MODULE_CODE_MAIN)
        for (i in 0..119) {
            connection.addCallback(callback, i)
        }
        MsToolkitConnection.instance.addObserver(connection)
    }

    private fun connectCanbus() {
        val callback = ModuleCallback("Canbus")
        val connection = IPCConnection(ModuleCodes.MODULE_CODE_CANBUS)
        for (i in 0..50) {
            connection.addCallback(callback, i)
        }
        for (i in 1000..1036) {
            connection.addCallback(callback, i)
        }
        MsToolkitConnection.instance.addObserver(connection)
    }

    private fun connectSound() {
        val callback = ModuleCallback("Sound")
        val connection = IPCConnection(ModuleCodes.MODULE_CODE_SOUND)
        for (i in 0..49) {
            connection.addCallback(callback, i)
        }
        MsToolkitConnection.instance.addObserver(connection)
    }

    private fun connectCanUp() {
        val callback = ModuleCallback("CanUp")
        val connection = IPCConnection(ModuleCodes.MODULE_CODE_CAN_UP)
        connection.addCallback(callback, 100)
        MsToolkitConnection.instance.addObserver(connection)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClimateControlTheme {
        Greeting("Android")
    }
}