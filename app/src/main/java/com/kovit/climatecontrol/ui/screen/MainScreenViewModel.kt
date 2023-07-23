package com.kovit.climatecontrol.ui.screen

import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.aoe.fytcanbusmonitor.ModuleCodes
import com.aoe.fytcanbusmonitor.MsToolkitConnection
import com.aoe.fytcanbusmonitor.RemoteModuleProxy
import com.kovit.climatecontrol.ui.data.AirTemp

class MainScreenViewModel() : ViewModel() {
    private val remoteProxy = RemoteModuleProxy()

    var windowOn: Boolean = false
    var faceOn: Boolean = false
    var feetOn: Boolean = false

    var random: String = ""

    //temp info
    var leftTemp: String = "00"
    var rightTemp: String = "00"

    fun canbusCMD(adj: Int) {
        var rm = MsToolkitConnection.instance.remoteToolkit?.getRemoteModule(ModuleCodes.MODULE_CODE_CANBUS)
        rm?.cmd(0, intArrayOf(adj, 1), null, null)
    }

    fun canBusNotify(systemName: String, updateCode: Int, intArray: IntArray?, floatArray: FloatArray?, strArray: Array<String?>?) {
        if (systemName.lowercase().equals("canbus")) {
            if (updateCode in 1..16 || updateCode in 69..81 && updateCode != 77) {
               random += (
                    "updateCode: " + updateCode + " value: " + intArray?.get(
                        0
                    ) + "\n"
                )
            }
            when (updateCode) {
                // update left temp
                11 -> {
                    val newTemp = intArray?.get(0)
                    if (newTemp == -2) {
                        leftTemp = "MIN"
                    } else if (newTemp == -3) {
                        leftTemp = "MAX"
                    } else {
                        val inF: Int? = newTemp?.plus(64)
                        if (inF != null) {
                            leftTemp = inF.toString()
                        }
                    }
                }
                // update right temp
                12 -> {
                    val newTemp = intArray?.get(0)
                    if (newTemp == -2) {
                        rightTemp = "MIN"
                    } else if (newTemp == -3) {
                        rightTemp = "MAX"
                    } else {
                        val inF: Int? = newTemp?.plus(64)
                        if (inF != null) {
                            rightTemp = inF.toString()
                        }
                    }
                }
                /*
                4 -> {
                    val autoOn = intArray?.get(0)
                    findViewById<TextView>(R.id.lblAuto).visibility =
                        if (autoOn == 0) View.INVISIBLE else View.VISIBLE
                }
                2 -> {
                    val acOn = intArray?.get(0)
                    findViewById<ImageView>(R.id.imgAC).setImageResource(if (acOn == 1) R.drawable.img_ac_on else R.drawable.img_ac_off)
                }
                3 -> {
                    val recircOn = intArray?.get(0)
                    findViewById<ImageView>(R.id.imgRecirc).setImageResource(if (recircOn == 1) R.drawable.img_recirc_on else R.drawable.img_recirc_off)
                }
                15 -> {
                    val recircAutoOn = intArray?.get(0)
                    findViewById<TextView>(R.id.lblAutoRecirc).visibility =
                        if (recircAutoOn == 0) View.INVISIBLE else View.VISIBLE
                }
                10 -> {
                    val fanSpeed = intArray?.get(0)
                    findViewById<TextView>(R.id.txtFanSpeed).text = fanSpeed.toString()
                }
                6 -> {
                    val defrostOn = intArray?.get(0)
                    findViewById<ImageView>(R.id.imgDefrost).setImageResource(if (defrostOn == 1) R.drawable.img_defrost_on else R.drawable.img_defrost_off)
                }
                7 -> {
                    windowOn = intArray?.get(0) == 1
                    handleVentStatus()
                }
                8 -> {
                    faceOn = intArray?.get(0) == 1
                    handleVentStatus()
                }
                9 -> {
                    feetOn = intArray?.get(0) == 1
                    handleVentStatus()
                }
*/
            }
        }

    }

}