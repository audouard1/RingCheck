package iut.clermont.pm.ringcheck.manager

import android.R
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import iut.clermont.pm.ringcheck.activity.MainActivity
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.persistence.AlarmRepository
import iut.clermont.pm.ringcheck.data.persistence.RingCheckDatabase
import iut.clermont.pm.ringcheck.receiver.AlarmReceiver

class RingCheckManager {

    private var repository: AlarmRepository

    init {
        val alarmDao = RingCheckDatabase.getInstance().alarmDao()
        repository = AlarmRepository(alarmDao)
    }

    fun setAlarm(context : Context, alarm : Alarm){
        val TWO_SEC_MILLIS = alarm.startDate.toEpochSecond()*1000
        val a = System.currentTimeMillis()
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setAlarmClock(
                AlarmManager.AlarmClockInfo(
                    TWO_SEC_MILLIS,
                    PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0)
                ),
                getIntent(context)
            )
        }
    }

    private fun getIntent(context: Context): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}