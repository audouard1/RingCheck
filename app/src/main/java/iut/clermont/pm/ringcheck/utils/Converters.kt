package iut.clermont.pm.ringcheck.utils

import android.content.Context
import android.icu.text.TimeZoneNames
import android.text.format.DateFormat
import androidx.databinding.InverseMethod
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

object Converters {
    @JvmStatic
    fun dateToString(context: Context?, value: ZonedDateTime?) = value?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    @JvmStatic
    @InverseMethod("dateToString")
    fun stringToDate(context: Context?, value: String?) = value?.let {
        ZonedDateTime.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    @JvmStatic
    fun timeToString(context: Context?, value: ZonedDateTime?) = value?.format(DateTimeFormatter.ofPattern("HH:mm"))

    @JvmStatic
    @InverseMethod("timeToString")
    fun stringToTime(context: Context?, value: String?) = value?.let {  ZonedDateTime.parse(value, DateTimeFormatter.ofPattern("HH:mm"))}

    @JvmStatic
    fun stringToDateTime(context: Context?, value: String?) = value?.let {
        ZonedDateTime.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault()))
    }

}