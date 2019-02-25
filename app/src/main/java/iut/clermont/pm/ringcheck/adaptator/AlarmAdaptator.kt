package iut.clermont.pm.ringcheck.adaptator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iut.clermont.pm.ringcheck.R
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.databinding.RecyclerviewItemBinding
import iut.clermont.pm.ringcheck.manager.RingCheckManager
import iut.clermont.pm.ringcheck.ui.ringcheck.ListRingCheckFragmentDirections

class AlarmAdaptator : ListAdapter<Alarm, AlarmAdaptator.AlarmViewHolder>(AlarmDiffCallback()) {

    private val ringCheckManager : RingCheckManager = RingCheckManager()

    inner class AlarmViewHolder(private val binding : RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, checkedListener : CompoundButton.OnCheckedChangeListener, item: Alarm) {
            binding.apply {
                alarm = item
                clickListener = listener
                changeListener = checkedListener
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_item, parent, false))
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarm = getItem(position)
        holder.apply {
            bind(createOnClickListener(alarm.alarmId), createOnCheckedChangeListener(alarm), alarm)
            itemView.tag = alarm
        }
    }

    private fun createOnClickListener(alarmId: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction = ListRingCheckFragmentDirections.actionMainRingChekFragmentToAddRingCheckFragment(alarmId)
            it.findNavController().navigate(direction)
        }
    }
    private fun createOnCheckedChangeListener(alarm: Alarm): CompoundButton.OnCheckedChangeListener{
        return CompoundButton.OnCheckedChangeListener { buttonView, isChecked -> if(isChecked) ringCheckManager.setAlarm(buttonView.context, alarm) }
        }
    }

private class AlarmDiffCallback : DiffUtil.ItemCallback<Alarm>() {

    override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        return oldItem.alarmId == newItem.alarmId
    }

    override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        return oldItem == newItem
    }
}

