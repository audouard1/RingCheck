package iut.clermont.pm.ringcheck.data.model

import androidx.room.*
import org.threeten.bp.ZonedDateTime
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


@Entity(tableName = "alarms")
data class Alarm (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "alarm_id") val alarmId: Int,
    var name: String,
    var startDate: ZonedDateTime,
    var endDate: ZonedDateTime,
    var isActive: Boolean,
    var isRepeat: Boolean

) {
    @Ignore
    lateinit var checkElems: List<CheckElem>
}