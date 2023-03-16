package com.example.project.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.data.Habit
import com.example.project.databinding.ItemHabitBinding

class HabitAdapter : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>(){

    private var habits: List<Habit> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val data = habits[position]
        with(holder){
            nameTv.text = data.name
            descriptionTv.text = data.description
            priorityTv.text = data.priority
            typeTv.text = data.type
            repeatsTv.text = data.repeats
            periodTv.text = data.period
        }
    }

    override fun getItemCount(): Int = habits.size

    fun update(data: List<Habit>){
        habits = data
        notifyDataSetChanged()
    }

    inner class HabitViewHolder(
        binding: ItemHabitBinding
    ) : RecyclerView.ViewHolder(binding.root){

        val nameTv = binding.habitNameTv
        val descriptionTv = binding.habitDescriptionTv
        val priorityTv = binding.habitPriorityTv
        val typeTv = binding.habitTypeTv
        val repeatsTv = binding.habitRepeatsTv
        val periodTv = binding.habitPeriodTv

    }
}