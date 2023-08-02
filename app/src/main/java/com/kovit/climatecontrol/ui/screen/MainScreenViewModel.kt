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

    var callback = ""

    var faceOn: Boolean = false
    var feetOn: Boolean = false

    var random: String = ""

    //temp info
    var leftTemp: String = "00"
    var rightTemp: String = "00"
    var sync: Boolean = true

    //power info
    var auto = true
    var off = false
    var airCon = false

    // air info
    var fanSpeed = "0"
    var recircMode = 0
    var defrostOn = false
    var windshieldDef = false

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

                4 -> {
                    val autoOn = intArray?.get(0)
                    auto = (autoOn != 0)
                }
                2 -> {
                    val acOn = intArray?.get(0)
                    airCon = (acOn == 1)
                }
                3 -> {
                    val recircOn = intArray?.get(0)
                    if (recircOn == 1) {
                        recircMode = 0
                    }
                }
                15 -> {
                    val recircAutoOn = intArray?.get(0)
                    if (recircAutoOn == 0) {
                        recircMode = 1
                    }
                }
                10 -> {
                    val canFanSpeed = intArray?.get(0)
                    fanSpeed = canFanSpeed.toString()
                }
                6 -> {
                    defrostOn = intArray?.get(0) == 1
                }
                7 -> {
                    windshieldDef = intArray?.get(0) == 1
//                    handleVentStatus()
                }
                8 -> {
                    faceOn = intArray?.get(0) == 1
//                    handleVentStatus()
                }
                9 -> {
                    feetOn = intArray?.get(0) == 1
//                    handleVentStatus()
                }
            }
        }

    }
//    fun handleVentStatus() {
//        if (faceOn && feetOn) {
//            findViewById<ImageView>(R.id.imgVents).setImageResource(R.drawable.img_vents_foot_face)
//        } else if (feetOn && windowOn) {
//            findViewById<ImageView>(R.id.imgVents).setImageResource(R.drawable.img_vents_foot_defrost)
//        } else if (feetOn) {
//            findViewById<ImageView>(R.id.imgVents).setImageResource(R.drawable.img_vents_foot)
//        } else if (faceOn) {
//            findViewById<ImageView>(R.id.imgVents).setImageResource(R.drawable.img_vents_face)
//        } else {
//            findViewById<ImageView>(R.id.imgVents).setImageResource(R.drawable.img_vents_off)
//        }
//    }
}
