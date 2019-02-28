package iut.clermont.pm.ringcheck.activity

import android.content.Context
import android.media.AudioAttributes
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.*
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import iut.clermont.pm.ringcheck.R
import iut.clermont.pm.ringcheck.viewmodel.RingViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_alarm.*


class AlarmActivity : AppCompatActivity() {

    companion object {
        fun newInstance() = RingViewModel()
    }

    private lateinit var viewModel: RingViewModel

    private lateinit var ringtone : Ringtone

    private val handler = Handler()

    private lateinit var runnable : Runnable

    private lateinit var vibrator : Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        setContentView(R.layout.activity_alarm)
        val imageView = findViewById<ImageView>(R.id.catImageView)
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, uri)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ringtone.audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
        }

        runnable = object : Runnable {
            override fun run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                    else
                        vibrator.vibrate(500)
                ringtone.play()
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)

        viewModel = ViewModelProviders.of(this).get(RingViewModel::class.java)
        viewModel.cat.observe(this, Observer { cat ->
            val res = cat
            if (res.first().url != ""){
                Glide.with(this).load(res.first().url).into(imageView)
            }
        })
        stopAlarm.setOnClickListener { finish() }

    }

    override fun onDestroy() {
        ringtone.stop()
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        }
        else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}
