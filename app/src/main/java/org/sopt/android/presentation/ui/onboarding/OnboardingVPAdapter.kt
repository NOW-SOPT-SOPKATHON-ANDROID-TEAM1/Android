package org.sopt.android.presentation.ui.onboarding

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.android.databinding.FragmentOnboadingBinding

class OnboardingVPAdapter(
    private val strings: ArrayList<String>) : RecyclerView.Adapter<OnboardingVPAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(val binding: FragmentOnboadingBinding):RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bind(photo: String, position: Int) {
            binding.tvTemporaryText.text = position.toString() + ": "+photo
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PagerViewHolder {
        val binding = FragmentOnboadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(strings[position], position)
    }

    override fun getItemCount(): Int = strings.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
