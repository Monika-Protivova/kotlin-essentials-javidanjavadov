package com.motycka.edu.lesson03

import java.time.LocalDate

class Assignment(val dueDate: LocalDate, val assignee: String) {
    private var finalGrade: Int? = null

    fun setFinalGrade(grade: Int) {
        require(grade in 0..100)
        this.finalGrade = grade
    }

    fun getFinalGrade(): Int? = finalGrade
}

fun main() {
    val assignment = Assignment(LocalDate.now(), "John Doe")
    assignment.setFinalGrade(90)
    println(assignment.getFinalGrade())
}
