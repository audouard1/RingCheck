package iut.clermont.pm.ringcheck.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*


@Entity(tableName = "alarms")
data class Alarm (
    @PrimaryKey @ColumnInfo(name = "id") val alarmId: Int,
    var name: String,
    var dateStart: Date,
    var endData: Date
)

