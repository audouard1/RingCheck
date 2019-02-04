package iut.clermont.pm.ringcheck.ui.mainringchek

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iut.clermont.pm.ringcheck.R
import iut.clermont.pm.ringcheck.adaptator.AlarmAdaptator
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.viewModel.AlarmViewModel
import java.util.*

class MainRingChekFragment : Fragment() {

    companion object {
        fun newInstance() = AlarmViewModel()
    }

    private lateinit var viewModel: AlarmViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_ring_chek_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = this.view?.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = AlarmAdaptator(context)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)


        viewModel = ViewModelProviders.of(this).get(AlarmViewModel::class.java)
        //viewModel.insert(Alarm(8, "coucouc", Date(), Date()))

        viewModel.allAlarms.observe(this, Observer { alarm ->

            alarm?.let { adapter.setAlarm(it) }
        })
    }
}
