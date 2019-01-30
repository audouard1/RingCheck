package iut.clermont.pm.ringcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import iut.clermont.pm.ringcheck.adaptator.AlarmAdaptator
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.data.persistence.AlarmDao
import iut.clermont.pm.ringcheck.data.persistence.AlarmRepository
import iut.clermont.pm.ringcheck.data.persistence.RingCheckDatabase
import iut.clermont.pm.ringcheck.ui.mainringchek.MainRingChekFragment
import iut.clermont.pm.ringcheck.viewModel.AlarmViewModel
import kotlinx.android.synthetic.main.main_ring_chek_activity.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    //private lateinit var alarmViewModel: AlarmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_ring_chek_activity)
        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        navController = Navigation.findNavController(this, R.id.mainFrag)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }





}
