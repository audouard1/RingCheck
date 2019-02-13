package iut.clermont.pm.ringcheck.ui.ringcheck

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import iut.clermont.pm.ringcheck.databinding.AddRingCheckFragmentBinding
import iut.clermont.pm.ringcheck.ui.ringcheck.pickers.DatePickerFragment
import iut.clermont.pm.ringcheck.ui.ringcheck.pickers.TimePickerFragment
import iut.clermont.pm.ringcheck.utils.DateUtils
import iut.clermont.pm.ringcheck.utils.viewModelFactory
import iut.clermont.pm.ringcheck.viewmodel.AlarmVM
import kotlinx.android.synthetic.main.add_ring_check_fragment.*


class AddRingCheckFragment : Fragment() {

    companion object {
        fun newInstance() = AddRingCheckFragment()
        // val alarmId = AddRingCheckFragmentArgs.fromBundle(arguments!!).plantId
        private const val DATE_PICKER = 300
        private const val STARTTIME_PICKER = 301
        private const val ENDTIME_PICKER = 302
    }

    val args: AddRingCheckFragmentArgs by navArgs()

    private lateinit var viewModel: AlarmVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this,
            viewModelFactory { AlarmVM(args.alarmId) })
            .get(AlarmVM::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(iut.clermont.pm.ringcheck.R.menu.addringcheck_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            iut.clermont.pm.ringcheck.R.id.action_save -> {
                saveAlarm()
                true
            }
            iut.clermont.pm.ringcheck.R.id.action_delete -> {
                //deleteAlarm()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveAlarm() {
        if (inputDateAlarm.text.toString() != "" && inputStartAlarm.text.toString() != "") {
            viewModel.alarm.value?.let {
                it.name = inputNameAlarm.text.toString()
                it.startDate = DateUtils.convertDateFromView(inputDateAlarm.text.toString())
                    .plusHours(inputStartAlarm.text.split(":").first().toLong())
                    .plusMinutes(inputStartAlarm.text.split(":").last().toLong())
                if (inputEndAlarm.text.toString() != "") {
                    it.endDate = DateUtils.convertDateFromView(inputDateAlarm.text.toString())
                        .plusHours(inputEndAlarm.text.split(":").first().toLong())
                        .plusMinutes(inputEndAlarm.text.split(":").last().toLong())
                } else {
                    it.endDate = DateUtils.convertDateFromView(inputDateAlarm.text.toString())
                        .plusHours(inputStartAlarm.text.split(":").first().toLong())
                        .plusMinutes(inputStartAlarm.text.split(":").last().toLong().plus(-1))
                }

            }
            viewModel.insertOrUpdate()
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AddRingCheckFragmentBinding.inflate(inflater)
        binding.alarmVM = viewModel
        binding.lifecycleOwner = this
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialogFragments()
    }

    private fun setDialogFragments() {
        val frag = this
        val datePickerfragment = DatePickerFragment().apply {
            setTargetFragment(frag, DATE_PICKER)
        }
        val timeStartPickerfragment = TimePickerFragment().apply {
            setTargetFragment(frag, STARTTIME_PICKER)
        }
        val timeEndPickerfragment = TimePickerFragment().apply {
            setTargetFragment(frag, ENDTIME_PICKER)
        }
        inputDateAlarm.setOnClickListener {
            datePickerfragment.show(fragmentManager, "datePicker")
        }
        inputEndAlarm.setOnClickListener {
            timeEndPickerfragment.show(fragmentManager, "timeStartPickerButon")
        }
        inputStartAlarm.setOnClickListener {
            timeStartPickerfragment.show(fragmentManager, "timeEndPickerButon")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlarmVM::class.java)
        // TODO: Use the ViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DATE_PICKER) {
            updateInputDate(data)
        }
        if (requestCode == STARTTIME_PICKER) {
            updateTime(inputStartAlarm, data)
        }
        if (requestCode == ENDTIME_PICKER) {
            updateTime(inputEndAlarm, data)
        }
    }

    private fun updateTime(textView: TextView, data: Intent?) {
        textView.text = StringBuilder()
            .append(String.format("%02d", data?.getIntExtra("HOUR", 1)))
            .append(":").append(String.format("%02d", data?.getIntExtra("MIN", 1)))
    }

    private fun updateInputDate(data: Intent?) {
        inputDateAlarm.text = StringBuilder()
            .append(String.format("%02d", data?.getIntExtra("DAY", 1)))
            .append("/")
            .append(String.format("%02d", data?.getIntExtra("MONTH", 1)?.plus(1)))
            .append("/")
            .append(String.format("%02d", data?.getIntExtra("YEAR", 1)))
    }


}
