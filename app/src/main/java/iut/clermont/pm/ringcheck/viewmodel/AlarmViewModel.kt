package iut.clermont.pm.ringcheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.persistence.AlarmRepository
import iut.clermont.pm.ringcheck.data.persistence.RingCheckDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AlarmViewModel : ViewModel() {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private val repository : AlarmRepository
    val allAlarms : LiveData<List<Alarm>>



    init {
        val alarmDao = RingCheckDatabase.getInstance().alarmDao()
        repository = AlarmRepository(alarmDao)
        allAlarms = repository.allArarm
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }


}