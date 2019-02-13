package iut.clermont.pm.ringcheck.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.persistence.RingCheckDatabase
import org.threeten.bp.ZonedDateTime
import java.util.*

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override fun doWork(): Result {
        val pairType = object : TypeToken<List<AlarmDTO>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            val inputStream = applicationContext.assets.open("alarms.json")
            var alarmList = ArrayList<Alarm>()
            jsonReader = JsonReader(inputStream.reader())
            var c: List<AlarmDTO> =  Gson().fromJson(jsonReader, pairType)
            for( d in c){
                alarmList.add(Alarm(d.alarmId, d.name, ZonedDateTime.now(), ZonedDateTime.now(),false,false))
            }
            val database = RingCheckDatabase.getInstance()
            database.alarmDao().insertAll(alarmList)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        } finally {
            jsonReader?.close()
        }
    }
}