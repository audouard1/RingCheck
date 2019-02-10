package iut.clermont.pm.ringcheck.adaptator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import iut.clermont.pm.ringcheck.R
import iut.clermont.pm.ringcheck.data.model.Alarm

class AlarmAdaptator internal constructor(
    context: Context?
) : RecyclerView.Adapter<AlarmAdaptator.AlarmViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var alarms = emptyList<Alarm>() // Cached copy of words

    inner class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val AlarmItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return AlarmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val current = alarms[position]
        //holder.AlarmItemView.text = current.name
    }

    internal fun setAlarm(alarms: List<Alarm>) {
        this.alarms = alarms
        notifyDataSetChanged()
    }

    override fun getItemCount() = alarms.size
}

