package com.example.project

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.controller.HabitAdapter
import com.example.project.data.Habit
import com.example.project.data.HabitsData.addHabit
import com.example.project.data.HabitsData.getHabits
import com.example.project.data.HabitsData.habitsCount
import com.example.project.data.HabitsData.setHabitToPosition
import com.example.project.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    companion object {
        const val HABIT = "habit"
        const val POSITION = "position"
    }
    private lateinit var binding: ActivityMainBinding
    private val habitAdapter = HabitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        checkUpdate()

        binding.fab.setOnClickListener {
            addHabit()
        }
    }

    private fun initViews(){
        with(binding.habitListRv){
            layoutManager = LinearLayoutManager(context)
            adapter = habitAdapter
        }
    }

    private fun updateRecyclerView(){
        habitAdapter.update(getHabits())
    }

    private fun checkUpdate(){
        var habit = getSerializable(this, HABIT, Habit::class.java) ?: return
        habit = habit as Habit
        val position = intent.getIntExtra(POSITION,0)
        if (position >= habitsCount())
            addHabit(habit)
        else setHabitToPosition(habit, position)
        updateRecyclerView()
    }

    private fun changeHabit(habit: Habit, position: Int){
        val newIntent = Intent(this, SecondActivity::class.java)
        newIntent.putExtra(HABIT, habit)
        newIntent.putExtra(POSITION, position)
        startActivity(newIntent)
    }

    private fun addHabit(){
        val sendIntent = Intent(this, SecondActivity::class.java)
        sendIntent.putExtra(POSITION, habitsCount())
        startActivity(sendIntent)
    }

    fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T
    {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
    }
}