package iut.clermont.pm.ringcheck.ui.mainringchek

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import iut.clermont.pm.ringcheck.ui.mainringchek.pickers.DatePickerFragment
import iut.clermont.pm.ringcheck.ui.mainringchek.pickers.TimePickerFragment
import iut.clermont.pm.ringcheck.viewmodel.AddRingCheckViewModel
import kotlinx.android.synthetic.main.add_ring_check_fragment.*
import java.lang.StringBuilder


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
        val datePickerfragment = DatePickerFragment()
        val timeStartPickerfragment = TimePickerFragment()
        val timeEndPickerfragment = TimePickerFragment()
        timeStartPickerfragment.setTargetFragment(this, 301)
        timeEndPickerfragment.setTargetFragment(this, 302)
        datePickerfragment.setTargetFragment(this, 300)

        inputDateAlarm.setOnClickListener { datePickerfragment
            .show(fragmentManager, "datePicker") }
        inputEndAlarm.setOnClickListener{ timeEndPickerfragment
            .show(fragmentManager, "timeStartPickerButon")}
        inputStartAlarm.setOnClickListener{ timeStartPickerfragment
            .show(fragmentManager, "timeEndPickerButon")}
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddRingCheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 300){
            updateInputDate(data)
        }
        if(requestCode == 301){
            inputStartAlarm.text = StringBuilder()
                .append(data?.getIntExtra("HOUR", 1))
                .append(":").append(data?.getIntExtra("MIN", 1))
        }
        if(requestCode == 302){
            inputEndAlarm.text = StringBuilder()
                .append(data?.getIntExtra("HOUR", 1))
                .append(":").append(data?.getIntExtra("MIN", 1))
        }
    }

    private fun updateInputDate(data: Intent?) {
        inputDateAlarm.text = StringBuilder()
            .append(data?.getIntExtra("DAY", 1))
            .append("/")
            .append(data?.getIntExtra("MONTH", 1)?.plus(1))
            .append("/")
            .append(data?.getIntExtra("YEAR", 1))
    }


}
