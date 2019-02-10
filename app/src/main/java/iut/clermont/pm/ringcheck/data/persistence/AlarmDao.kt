package iut.clermont.pm.ringcheck.data.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.model.CheckElem

@Dao
interface AlarmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(alarms: List<Alarm>)

    @Insert
    fun insert(alarm : Alarm)

    @Query("SELECT * from alarms")
    fun getAllAlarms(): LiveData<List<Alarm>>

    @Query("DELETE FROM alarms")
    fun deleteAll()

    @Query("SELECT * FROM check_elems WHERE alarm_id IS :alarmId")
    fun getCheckElems(alarmId : Int): LiveData<List<CheckElem>>

    @Query("SELECT * FROM alarms WHERE alarm_id IS :alarmId")
    fun getAlarm(alarmId : Int): LiveData<Alarm>
}