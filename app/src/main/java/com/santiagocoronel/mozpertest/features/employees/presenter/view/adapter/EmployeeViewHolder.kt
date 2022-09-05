package com.santiagocoronel.mozpertest.features.employees.presenter.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiagocoronel.mozpertest.databinding.EmployeeItemBinding
import com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.tables.EmployeeEntity

class EmployeeViewHolder(
    private val binding: EmployeeItemBinding,
    private val onClickItem: (item: EmployeeEntity) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EmployeeEntity) {
        with(binding) {

            textViewFirstName.text = item.firstName
            textViewLastName.text = item.lastName

            textViewRating.text = "Rating: ${item.rating}"

            Glide.with(imageView.context)
                .load(item.image)
                .into(binding.imageView)

            root.setOnClickListener {
                onClickItem(item)
            }
        }
    }
}