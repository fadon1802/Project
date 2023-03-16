package com.example.project

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.project.data.Habit
import com.example.project.databinding.ActivityTwoBinding
import java.io.Serializable


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTwoBinding
    private val types = arrayOf(
        "Полезная", "Вредная"
    )
    private val priorities = arrayOf(
        "Нет приоритета", "!", "!!", "!!!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoBinding.inflate(layoutInflater)
        val typesAutoComplete = binding.pickHabitType
        typesAutoComplete.setAdapter(
            ArrayAdapter<Any?>(this, R.layout.simple_dropdown_item_1line, types)
        )

        val priorityAutoComplete = binding.pickHabitPriority
        priorityAutoComplete.setAdapter(
            ArrayAdapter<Any?>(this, R.layout.simple_dropdown_item_1line, priorities)
        )

        val view = binding.root
        setContentView(view)
        val position = intent.getIntExtra(MainActivity.POSITION, 0)
        binding.saveButton.setOnClickListener{
            val habit = Habit(
                name = binding.editHabitName.text.toString(),
                description = binding.editHabitDescription.text.toString(),
                priority = binding.pickHabitPriority.text.toString(),
                type = binding.pickHabitType.text.toString(),
                repeats = binding.editHabitRepeats.text.toString(),
                period = binding.editHabitPeriodicity.text.toString()
            )

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.HABIT, habit)
            intent.putExtra(MainActivity.POSITION, position)
            startActivity(intent)
        }
    }
}