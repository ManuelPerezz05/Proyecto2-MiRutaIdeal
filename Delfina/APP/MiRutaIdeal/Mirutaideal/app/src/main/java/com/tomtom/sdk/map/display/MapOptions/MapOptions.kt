package com.tomtom.sdk.map.display.MapOptions

import com.tomtom.sdk.map.display.camera.CameraOptions
import com.tomtom.sdk.map.display.common.screen.Padding
import com.tomtom.sdk.map.display.map.OnlineCachePolicy
import com.tomtom.sdk.map.display.style.StyleDescriptor
import com.tomtom.sdk.map.display.style.StyleMode

class MapOptions(
    mapKey: String,
    cameraOptions: CameraOptions? = null,
    padding: Padding = Padding(),
    mapStyle: StyleDescriptor? = null,
    styleMode: StyleMode = StyleMode.MAIN,
    onlineCachePolicy: OnlineCachePolicy = OnlineCachePolicy.Default,
    renderToTexture: Boolean = false
)