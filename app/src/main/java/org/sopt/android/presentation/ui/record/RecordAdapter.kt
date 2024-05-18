package org.sopt.android.presentation.ui.record

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.android.databinding.ItemRecordBinding

class RecordAdapter() :
    RecyclerView.Adapter<RecordViewHolder>() {
    private var recordList: List<Record> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecordBinding.inflate(inflater, parent, false)
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.onBind(recordList[position])
    }

    override fun getItemCount(): Int = recordList.size

    fun setRecordList(newRecords: List<Record>) {
        this.recordList = newRecords
        notifyDataSetChanged()
    }
}
