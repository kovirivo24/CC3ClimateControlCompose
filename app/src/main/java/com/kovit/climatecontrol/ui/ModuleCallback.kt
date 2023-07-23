package com.ryansteckler.lx470climate

import android.os.Build
import android.os.RemoteException
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.aoe.fytcanbusmonitor.IModuleCallback
import com.kovit.climatecontrol.MainActivity
import com.kovit.climatecontrol.ui.screen.MainScreenViewModel

class ModuleCallback(name: String, private val view: TextView?) : IModuleCallback.Stub() {
    private val systemName = name

    @Throws(RemoteException::class)
    override fun update(
        updateCode: Int,
        intArray: IntArray?,
        floatArray: FloatArray?,
        strArray: Array<String?>?
    ) {
        logMsg(systemName, updateCode, intArray, floatArray, strArray)
    }

    companion object {
        private lateinit var act: MainActivity
        private lateinit var viewModel: MainScreenViewModel

        @RequiresApi(Build.VERSION_CODES.O)
        fun init(mainAct: MainActivity) {
            act = mainAct
        }

        @Synchronized
        private fun logMsg(
            systemName: String,
            updateCode: Int,
            intArray: IntArray?,
            floatArray: FloatArray?,
            strArray: Array<String?>?
        ) {
            act.runOnUiThread(Runnable {
                viewModel.canBusNotify(systemName, updateCode, intArray, floatArray, strArray)
            })
        }
    }
}