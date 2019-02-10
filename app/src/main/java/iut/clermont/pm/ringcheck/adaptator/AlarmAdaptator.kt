package iut.clermont.pm.ringcheck.adaptator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import iut.clermont.pm.ringcheck.R
import iut.clermont.pm.ringcheck.data.model.Alarm
import iut.clermont.pm.ringcheck.databinding.RecyclerviewItemBinding

class AlarmAdaptator internal constructor(
    context: Context?
) : RecyclerView.Adapter<AlarmAdaptator.AlarmViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var alarms = emptyList<Alarm>() // Cached copy of words

    inner class AlarmViewHolder(private val binding : RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Alarm) {
            binding.apply {
                alarm = item

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
        val alarm = alarms[position]
        holder.apply {
            bind(alarm)
            itemView.tag = alarm
        }
    }

    internal fun setAlarm(alarms: List<Alarm>) {
        this.alarms = alarms
        notifyDataSetChanged()
    }

    override fun getItemCount() = alarms.size
}

