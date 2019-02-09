package iut.clermont.pm.ringcheck.ui.mainringchek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import iut.clermont.pm.ringcheck.viewmodel.AddRingCheckViewModel
import kotlinx.android.synthetic.main.add_ring_check_fragment.*


class AddRingCheckFragment : Fragment() {

    companion object {
        fun newInstance() = AddRingCheckFragment()
    }

    private lateinit var viewModel: AddRingCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(iut.clermont.pm.ringcheck.R.layout.add_ring_check_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        datePickerButon.setOnClickListener { DatePickerFragment().show(fragmentManager, "datePicker") }
        timeStartPickerButon.setOnClickListener{ TimePickerFragment().show(fragmentManager, "timeStartPickerButon")}
        timeEndPickerButon.setOnClickListener{ TimePickerFragment().show(fragmentManager, "timeEndPickerButon")}
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddRingCheckViewModel::class.java)
        // TODO: Use the ViewModel
    }



}
