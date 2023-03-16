package com.example.project.data

object HabitsData{

    private var habits = mutableListOf<Habit>()

    fun addHabit(habit: Habit){
        habits.add(habit)
    }

    fun setHabitToPosition(habit: Habit, position: Int){
            habits[position] = habit
    }

    fun habitsCount() = habits.size
    fun getHabits() = habits

}