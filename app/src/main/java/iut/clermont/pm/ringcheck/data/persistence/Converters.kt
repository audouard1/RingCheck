package iut.clermont.pm.ringcheck.data.persistence

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class Converters {
    @TypeConverter
    fun dateToTimestamp(date: ZonedDateTime): String {
        return date.format(DateTimeFormatter.ISO_DATE_TIME)
    }

    @TypeConverter
    fun timestampToDate(date: String): ZonedDateTime {
        return ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
    }
}