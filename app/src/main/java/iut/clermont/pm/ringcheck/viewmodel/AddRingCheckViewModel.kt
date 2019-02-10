package iut.clermont.pm.ringcheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.persistence.AlarmRepository
import iut.clermont.pm.ringcheck.data.persistence.RingCheckDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime
import kotlin.coroutines.CoroutineContext

class AddRingCheckViewModel(alarmId : Int = 0) : ViewModel() {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private val repository : AlarmRepository
    val alarm : LiveData<Alarm>



    init {
        val alarmDao = RingCheckDatabase.getInstance().alarmDao()
        repository = AlarmRepository(alarmDao)
        if(alarmId == 0){
            val defaultAlarm = MutableLiveData<Alarm>()
            defaultAlarm.value = Alarm(0,"alarm", ZonedDateTime.now(), ZonedDateTime.now())
            alarm = defaultAlarm
        }
        else{
            alarm = alarmDao.getAlarm(alarmId)
        }
    }
    fun insertOrUpdate() = scope.launch(Dispatchers.IO) {
        alarm.value?.let{
            if(it.alarmId == 0) {
                repository.insert(it)
            }else{
                repository.update(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
