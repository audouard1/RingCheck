package iut.clermont.pm.ringcheck.data.persistence

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.model.CheckElem

class AlarmRepository(private val alarmDao : AlarmDao) {

    val allArarm: LiveData<List<Alarm>> = alarmDao.getAllAlarms()

    @WorkerThread
    suspend fun insert(alarm: Alarm) : Long{
        return alarmDao.insert(alarm)
    }

    @WorkerThread
    suspend fun insert(checkElem: CheckElem) {
        alarmDao.insert(checkElem)
    }

    @WorkerThread
    suspend fun update(alarm: Alarm) {
        alarmDao.update(alarm)
    }

    @WorkerThread
    fun getAlarm(alarmId : Int)= alarmDao.getAlarm(alarmId)

    @WorkerThread
    fun getCheckElems(alarmId : Int)= alarmDao.getCheckElems(alarmId)

    @WorkerThread
    fun delete(alarmId : Int) {
        alarmDao.deleteAllAlarmeCheckElem(alarmId)
        alarmDao.delete(alarmId)
    }

    @WorkerThread
    fun deleteCheckElem(checkElemId : Int) = alarmDao.deleteCheckElem(checkElemId)
}