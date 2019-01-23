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
}