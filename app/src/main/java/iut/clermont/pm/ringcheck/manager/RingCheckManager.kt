package iut.clermont.pm.ringcheck.manager

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Build
import android.os.SystemClock
import iut.clermont.pm.ringcheck.activity.AlarmActivity
import iut.clermont.pm.ringcheck.activity.MainActivity
import iut.clermont.pm.ringcheck.receiver.AlarmReceiver
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.temporal.TemporalField
import java.util.*

class RingCheckManager {

    fun setAlarm(activity : Activity){

        val alarmType = AlarmManager.RTC_WAKEUP
        val TWO_SEC_MILLIS = System.currentTimeMillis() + 10000

        // The AlarmManager, like most system services, isn't created by application code, but
        // requested from the system.
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // setRepeating takes a start delay and period between alarms as arguments.
        // The below code fires after 15 seconds, and repeats every 15 seconds.  This is very
        // useful for demonstration purposes, but horrendous for production.  Don't be that dev.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setAlarmClock(
                AlarmManager.AlarmClockInfo(
                    TWO_SEC_MILLIS,
                    PendingIntent.getActivity(activity, 0, Intent(activity, MainActivity::class.java), 0)
                ),
                getIntent(activity)
            )
        }

        // END_INCLUDE (configure_alarm_manager);

    }

    private fun getIntent(context: Context): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}