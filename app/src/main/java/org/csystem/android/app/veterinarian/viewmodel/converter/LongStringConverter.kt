package org.csystem.android.app.veterinarian.viewmodel.converter

import androidx.databinding.InverseMethod

object LongStringConverter {
    @InverseMethod(value = "toStr")
    fun toLong(str: String) : Long
    {
        var result = 0L

        try {
            result = str.toLong()
        }
        catch (ignore: NumberFormatException) {

        }
        return result
    }

    fun toStr(value: Long) = value.toString()
}