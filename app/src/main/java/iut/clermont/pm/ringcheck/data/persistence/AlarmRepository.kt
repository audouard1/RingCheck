package iut.clermont.pm.ringcheck.data.persistence

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import iut.clermont.pm.ringcheck.data.model.Alarm

class AlarmRepository(private val alarmDao : AlarmDao) {

    val allArarm: LiveData<List<Alarm>> = alarmDao.getAllAlarms()

    @WorkerThread
    suspend fun insert(alarm: Alarm) {
        alarmDao.insert(alarm)
    }

    @WorkerThread
    suspend fun update(alarm: Alarm) {
        alarmDao.update(alarm)
    }

    @WorkerThread
    fun getAlarm(alarmId : Int)= alarmDao.getAlarm(alarmId)

    @WorkerThread
    fun delete(alarmId : Int) = alarmDao.delete(alarmId)
}