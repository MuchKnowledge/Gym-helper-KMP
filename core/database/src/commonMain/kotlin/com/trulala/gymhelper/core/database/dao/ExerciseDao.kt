package com.trulala.gymhelper.core.database.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.trulala.gymhelper.core.database.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

/**
 * В Room 3 функции DAO обязаны быть suspend либо возвращать реактивный тип —
 * блокирующих вариантов больше нет.
 */
@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises ORDER BY name")
    fun observeAll(): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE muscle_group = :muscleGroup ORDER BY name")
    fun observeByMuscleGroup(muscleGroup: String): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun findById(id: String): ExerciseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(exercises: List<ExerciseEntity>)

    @Query("DELETE FROM exercises WHERE id = :id")
    suspend fun deleteById(id: String)
}
