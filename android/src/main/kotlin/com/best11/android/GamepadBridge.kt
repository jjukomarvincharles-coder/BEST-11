package com.best11.android

/**
 * Android gamepad events are surfaced through libGDX input/controller APIs.
 * Keep platform-specific device discovery here; gameplay consumes normalized
 * actions through the core InputRouter.
 */
object GamepadBridge {
    const val ACTION_CONFIRM = "confirm"
    const val ACTION_PASS = "pass"
    const val ACTION_SHOOT = "shoot"
    const val ACTION_SWITCH = "switch"
}
