package com.best11.game.input
enum class ControlMode { TOUCH, GAMEPAD }
class InputRouter(var mode:ControlMode=ControlMode.TOUCH) { val showTouchControls get()=mode==ControlMode.TOUCH }
