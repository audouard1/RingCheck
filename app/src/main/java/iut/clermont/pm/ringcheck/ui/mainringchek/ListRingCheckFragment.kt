package iut.clermont.pm.ringcheck.ui.mainringchek

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import iut.clermont.pm.ringcheck.R
import iut.clermont.pm.ringcheck.adaptator.AlarmAdaptator
import iut.clermont.pm.ringcheck.databinding.ListRingChekFragmentBinding
import iut.clermont.pm.ringcheck.viewmodel.AlarmViewModel
import kotlinx.android.synthetic.main.list_ring_chek_fragment.*

class ListRingCheckFragment : Fragment() {

    companion object {
        fun newInstance() = AlarmViewModel()
    }

    private lateinit var viewModel: AlarmViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(AlarmViewModel::class.java)
        val binding: ListRingChekFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.list_ring_chek_fragment, container, false)
        val adapter = AlarmAdaptator(context)
        binding.recyclerview.adapter = adapter


        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: AlarmAdaptator) {
        viewModel.allAlarms.observe(viewLifecycleOwner, Observer { alarms ->
            if (alarms != null) adapter.setAlarm(alarms)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener { view ->
            val direction =
                ListRingCheckFragmentDirections.actionMainRingChekFragmentToAddRingCheckFragment()
            view.findNavController().navigate(direction)
        }
    }
}
