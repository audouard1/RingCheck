package iut.clermont.pm.ringcheck.adaptator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iut.clermont.pm.ringcheck.R
import iut.clermont.pm.ringcheck.data.model.CheckElem
import iut.clermont.pm.ringcheck.data.persistence.AlarmRepository
import iut.clermont.pm.ringcheck.databinding.CheckelemRecyclItemBinding
import iut.clermont.pm.ringcheck.viewmodel.AlarmVM

class CheckElemAdaptator(private val alarmVM: AlarmVM) : ListAdapter<CheckElem, CheckElemAdaptator.CheckElemViewHolder>(CheckElemDiffCallback()) {


    inner class CheckElemViewHolder(private val binding : CheckelemRecyclItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: CheckElem) {
            binding.apply {
                checkElem = item
                clickListener = listener
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckElemViewHolder {
        return CheckElemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.checkelem_recycl_item, parent, false))
    }

    override fun onBindViewHolder(holder: CheckElemViewHolder, position: Int) {
        val checkElem = getItem(position)
        holder.apply {
            bind(createOnClickListener(checkElem.checkElemId), checkElem)
            itemView.tag = checkElem
        }
    }

    private fun createOnClickListener(checkElemId: Int): View.OnClickListener {
        return View.OnClickListener {
            alarmVM.deleteCheckElem(checkElemId)
        }
    }
}

private class CheckElemDiffCallback : DiffUtil.ItemCallback<CheckElem>() {

    override fun areItemsTheSame(oldItem: CheckElem, newItem: CheckElem): Boolean {
        return oldItem.checkElemId == newItem.checkElemId
    }

    override fun areContentsTheSame(oldItem: CheckElem, newItem: CheckElem): Boolean {
        return oldItem.alarmId == newItem.alarmId && oldItem.name == newItem.name
    }
}

