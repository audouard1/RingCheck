package iut.clermont.pm.ringcheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.model.CheckElem
import iut.clermont.pm.ringcheck.data.persistence.AlarmRepository
import iut.clermont.pm.ringcheck.data.persistence.RingCheckDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime
import kotlin.coroutines.CoroutineContext

class AddAlarmViewModel(alarmId: Int = 0) : ViewModel() {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private val repository: AlarmRepository
    var alarm: LiveData<Alarm>
    val checkElems: LiveData<List<CheckElem>>


    init {
        val alarmDao = RingCheckDatabase.getInstance().alarmDao()
        repository = AlarmRepository(alarmDao)
        if (alarmId == 0) {
            val defaultAlarm = MutableLiveData<Alarm>()
            val defaultElems = MutableLiveData<List<CheckElem>>()
            defaultAlarm.value = Alarm(0, "alarm", ZonedDateTime.now(), ZonedDateTime.now(), false, false)
            defaultElems.value = ArrayList<CheckElem>()
            alarm = defaultAlarm
            checkElems = defaultElems
        } else {
            alarm = repository.getAlarm(alarmId)
            checkElems = repository.getCheckElems(alarmId)
        }
    }

    fun insertOrUpdate() = scope.launch(Dispatchers.IO) {
        alarm.value?.let {
            if (it.alarmId == 0) {
                var id = repository.insert(it)
                alarm = repository.getAlarm(id.toInt())
            } else {
                repository.update(it)
            }
        }
    }

    fun deleteAlarm() = scope.launch(Dispatchers.IO) {
        alarm.value?.let {
            if (it.alarmId != 0) {
                repository.delete(it.alarmId)
            }
        }
    }

    fun deleteCheckElem(checkElemId: Int) = scope.launch(Dispatchers.IO) {
        alarm.value?.let {
            if (it.alarmId != 0) {
                repository.deleteCheckElem(checkElemId)
            }
        }
    }

    fun addCheckElem(checkElem: CheckElem) = scope.launch(Dispatchers.IO) {
        alarm.value?.let {
            if (checkElem.checkElemId == 0) {
                repository.insert(checkElem)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
