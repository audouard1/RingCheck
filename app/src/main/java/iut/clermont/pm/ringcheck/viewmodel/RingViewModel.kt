package iut.clermont.pm.ringcheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import iut.clermont.pm.ringcheck.data.model.Cat
import iut.clermont.pm.ringcheck.data.persistence.AlarmRepository
import iut.clermont.pm.ringcheck.data.persistence.CatRepository
import iut.clermont.pm.ringcheck.data.persistence.RingCheckDatabase
import iut.clermont.pm.ringcheck.ui.ringcheck.AddRingCheckFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RingViewModel : ViewModel() {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private val repository : CatRepository = CatRepository()
    private val AlarmRepository = AlarmRepository(RingCheckDatabase.getInstance().alarmDao())
    var cat : LiveData<List<Cat>>

    init {
        cat = repository.getCat()
    }

    fun removeAlarm(alarmId : Int){
        AlarmRepository.delete(alarmId)
    }


}