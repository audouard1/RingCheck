package iut.clermont.pm.ringcheck.data.persistence

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.model.CheckElem
import iut.clermont.pm.ringcheck.worker.SeedDatabaseWorker
import java.lang.IllegalArgumentException
import java.lang.RuntimeException


@Database(entities = [Alarm::class, CheckElem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RingCheckDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    companion object {
        @Volatile private var instance: RingCheckDatabase? = null
        @Volatile private var appContext: Application? = null

        fun getInstance(): RingCheckDatabase{
            if(appContext == null) throw RuntimeException("not init two time appContext db")
            return instance ?: synchronized(this) {
                instance ?: buildDatabase().also { instance = it }
            }
        }

        private fun buildDatabase(): RingCheckDatabase {
            return Room.databaseBuilder(appContext!!.applicationContext, RingCheckDatabase::class.java, "RingCheckDB")//DATABASE_NAME
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        //WorkManager.getInstance().enqueue(request)
                    }
                })
                .allowMainThreadQueries()
                .build()
        }

        @Synchronized fun setAppContext(appContext: Application){
            if(this.appContext == null){
                this.appContext = appContext
            }
            else
                RuntimeException("not init two time appContext db")
        }
    }
}