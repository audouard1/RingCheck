package iut.clermont.pm.ringcheck.data.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.model.CheckElem

@Dao
interface AlarmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(alarms: List<Alarm>)

    @Insert
    fun insert(alarm : Alarm) : Long

    @Insert
    fun insert(checkElem : CheckElem)

    @Update
    fun update(alarm: Alarm)

    @Query("SELECT * from alarms")
    fun getAllAlarms(): LiveData<List<Alarm>>

    @Query("DELETE FROM alarms")
    fun deleteAll()

    @Query("SELECT * FROM check_elems WHERE alarm_id = :alarmId")
    fun getCheckElems(alarmId : Int): LiveData<List<CheckElem>>

    @Query("SELECT * FROM alarms WHERE alarm_id =:alarmId")
    fun getAlarm(alarmId : Int): LiveData<Alarm>

    @Query("DELETE FROM alarms WHERE alarm_id =:alarmId")
    fun delete(alarmId : Int)

    @Query("DELETE FROM check_elems WHERE check_elem_id =:checkElemId")
    fun deleteCheckElem(checkElemId : Int)

    @Query("DELETE FROM check_elems WHERE alarm_id =:alarmId")
    fun deleteAllAlarmeCheckElem(alarmId: Int)
}