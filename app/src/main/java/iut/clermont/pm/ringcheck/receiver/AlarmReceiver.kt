package iut.clermont.pm.ringcheck.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import iut.clermont.pm.ringcheck.activity.AlarmActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(context, uri)
        ringtone.play()
        val ringer = Intent(context, AlarmActivity::class.java)
        ringer.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(ringer)
    }
}
