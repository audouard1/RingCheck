package iut.clermont.pm.ringcheck.utils

import android.content.Context
import android.text.format.DateFormat
import org.threeten.bp.ZonedDateTime
import java.util.*

object Converters {
    @JvmStatic
    fun dateToString(context: Context, value: ZonedDateTime?) = value?.let { DateFormat.getDateFormat(context).format(it) }

    @JvmStatic
    fun TimeToString(context: Context, value: ZonedDateTime?) = value?.let { DateFormat.getTimeFormat(context).format(it) }
}