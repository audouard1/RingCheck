package iut.clermont.pm.ringcheck.data.model

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(foreignKeys = [ForeignKey(entity = Alarm::class, parentColumns = ["id"], childColumns = ["alarm_id"])])
data class CheckElem(
    var name: String,
    var isCheked: Boolean,
    var alarmId: Int
)
