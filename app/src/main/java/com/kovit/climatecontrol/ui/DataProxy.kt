package com.ryansteckler.lx470climate

import com.aoe.fytcanbusmonitor.RemoteModuleProxy

object DataProxy {
    val canbusProxy = RemoteModuleProxy()
    val canUpProxy = RemoteModuleProxy()
    val mainProxy = RemoteModuleProxy()
}