package iut.clermont.pm.ringcheck.data.model

import androidx.room.*


@Entity(foreignKeys = [ForeignKey(entity = Alarm::class, parentColumns = ["alarm_id"], childColumns = ["alarm_id"])], tableName = "check_elems", indices = [Index("alarm_id")])
data class CheckElem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "check_elem_id") val checkElemId: Int,
    var name: String,
    var isCheked: Boolean,
    @ColumnInfo(name = "alarm_id") val alarmId: Int
)
