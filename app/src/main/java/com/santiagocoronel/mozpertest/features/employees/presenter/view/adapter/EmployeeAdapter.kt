package com.santiagocoronel.mozpertest.features.employees.presenter.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.santiagocoronel.mozpertest.R
import com.santiagocoronel.mozpertest.databinding.EmployeeItemBinding
import com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.tables.EmployeeEntity

class EmployeeAdapter(
    private val context: Context,
    private val list: MutableList<EmployeeEntity> = mutableListOf(),
    private val onClickItem: (item: EmployeeEntity) -> Unit
) : RecyclerView.Adapter<EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            EmployeeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickItem
        )
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.animation_one)
        holder.bind(list[position])
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int = list.size

    fun clear() {
        this.list.clear()
    }

    fun addAll(list: List<EmployeeEntity>) {
        this.list.addAll(list)
    }

}