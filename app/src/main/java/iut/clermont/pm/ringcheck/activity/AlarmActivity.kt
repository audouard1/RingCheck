package iut.clermont.pm.ringcheck.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import iut.clermont.pm.ringcheck.R
import iut.clermont.pm.ringcheck.viewmodel.RingViewModel

class AlarmActivity : AppCompatActivity() {

    companion object {
        fun newInstance() = RingViewModel()
    }

    private lateinit var viewModel: RingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        viewModel = ViewModelProviders.of(this).get(RingViewModel::class.java)
        val a = viewModel.cat.observe(this, Observer { cat ->
            val res = cat
            //val url = cat.url
        })

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
