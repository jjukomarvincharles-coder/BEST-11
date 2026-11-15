package com.best11.game.data
import java.io.File
class LocalSave(private val file:File) {
    fun write(text:String) { file.parentFile?.mkdirs(); file.writeText(text) }
    fun readOrNull():String? = file.takeIf { it.exists() }?.readText()
}
