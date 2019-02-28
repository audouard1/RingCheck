package iut.clermont.pm.ringcheck.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import iut.clermont.pm.ringcheck.activity.AlarmActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val ring = Intent(context, AlarmActivity::class.java)
        //ring.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(ring)
    }
}
