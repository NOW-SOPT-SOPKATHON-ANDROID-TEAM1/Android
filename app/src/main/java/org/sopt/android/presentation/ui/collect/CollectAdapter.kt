package org.sopt.android.presentation.ui.collect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.android.data.model.response.ResponseRememberAllDto
import org.sopt.android.databinding.ItemCollectBinding

class CollectAdapter(
    private val showDialogFragment: (ResponseRememberAllDto) -> Unit
) :
    RecyclerView.Adapter<CollectViewHolder>() {
    private var recordList: List<ResponseRememberAllDto> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCollectBinding.inflate(inflater, parent, false)
        return CollectViewHolder(binding, showDialogFragment)
    }

    override fun onBindViewHolder(holder: CollectViewHolder, position: Int) {
        holder.onBind(recordList[position])
    }

    override fun getItemCount(): Int = recordList.size

    fun setRecordList(newCollect: List<ResponseRememberAllDto>) {
        this.recordList = newCollect
        notifyDataSetChanged()
    }
}