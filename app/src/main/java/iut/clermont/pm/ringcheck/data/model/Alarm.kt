package iut.clermont.pm.ringcheck.data.model

import androidx.room.*
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


@Entity(tableName = "alarms")
data class Alarm (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "alarm_id") val alarmId: Int,
    var name: String,
    var startDate: Date,
    var endData: Date

) {
    @Ignore
    lateinit var checkElems: List<CheckElem>
}