package com.kovit.climatecontrol.ui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.ui.graphics.vector.ImageVector

enum class AirTemp{
    LeftUp, LeftDown, RightUp, RightDown, Dual;

    fun getCanCmd(): Int {
        var cmd: Int = -1

        if (this == LeftUp) {
            cmd = 3
        } else if (this == LeftDown) {
            cmd = 2
        } else if (this == RightUp) {
            cmd = 5
        } else if (this == RightDown) {
            cmd = 4
        } else if (this == Dual) {
            cmd = 16
        }

        return cmd
    }

     fun getIcon(): ImageVector {
         var icon = Icons.Outlined.Refresh

         return icon
     }
}

enum class AirPower{
    Auto, Off, AC, Plus, Minus;

    fun getCanCmd(): Int {
        var cmd: Int = -1

        if (this == Auto) {
            cmd = 21
        } else if (this == Off) {
            cmd = 1
        } else if (this == AC) {
            cmd = 23
        } else if (this == Plus) {
            cmd = 10
        } else if (this == Minus) {
            cmd = 9
        }

        return cmd
    }

    fun getIcon(): ImageVector {
        var icon = Icons.Outlined.Refresh

        return icon
    }
}

enum class AirMode{
    Mode, Recirc, Defrost;

    fun getCanCmd(): Int {
        var cmd: Int = -1

        if (this == Mode) {
            cmd = 36
        } else if (this == Recirc) {
            cmd = 25
        } else if (this == Defrost) {
            cmd = 18
        }

        return cmd
    }
    fun getIcon(): ImageVector {
        var icon = Icons.Outlined.Refresh

        return icon
    }
}



