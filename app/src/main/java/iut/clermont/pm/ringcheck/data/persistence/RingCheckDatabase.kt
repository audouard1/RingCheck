package iut.clermont.pm.ringcheck.data.persistence

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

abstract class RingCheckDatabase : RoomDatabase() {

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
            return Room.databaseBuilder(appContext!!.applicationContext, RingCheckDatabase::class.java, "")//DATABASE_NAME
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        //WorkManager.getInstance().enqueue(request)
                    }
                })
                .build()
        }

        @Synchronized public fun setAppContext(appContext: Application){
            if(this.appContext == null){
                this.appContext = appContext
            }
            else
                RuntimeException("not init two time appContext db")
        }
    }
}