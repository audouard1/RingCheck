package iut.clermont.pm.ringcheck.data.persistence

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class Converters {
    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun timestampToDate(long: Long): Date {
        return Date(long)
    }
}