package com.trulala.gymhelper.core.database.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

/**
 * Глобальный каталог упражнений — единственная таблица, не зависящая ни от программы,
 * ни от сессии, поэтому она же служит проверкой, что Room собирается на всех таргетах.
 */
@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @ColumnInfo(name = "muscle_group")
    val muscleGroup: String,
    val accent: String?,
    val equipment: String?,
)
