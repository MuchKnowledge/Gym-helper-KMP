package com.trulala.gymhelper.core.database

import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import app.cash.turbine.test
import com.trulala.gymhelper.core.database.entity.ExerciseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * DAO-тесты на чистой JVM: in-memory база на BundledSQLiteDriver,
 * без Context, Robolectric и эмулятора. Заодно это проверка того,
 * что KSP-генерация Room работает для jvm-таргета.
 */
class ExerciseDaoTest {
    private val database = Room
        .inMemoryDatabaseBuilder<GymHelperDatabase>()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.Default)
        .build()

    private val dao = database.exerciseDao()

    @AfterTest
    fun tearDown() {
        database.close()
    }

    @Test
    fun `upsert then findById returns entity`() = runTest {
        dao.upsertAll(listOf(BENCH_PRESS))

        assertEquals(BENCH_PRESS, dao.findById(BENCH_PRESS.id))
    }

    @Test
    fun `upsert with same id replaces entity`() = runTest {
        dao.upsertAll(listOf(BENCH_PRESS))
        val renamed = BENCH_PRESS.copy(name = "Жим лёжа узким хватом")

        dao.upsertAll(listOf(renamed))

        assertEquals(renamed, dao.findById(BENCH_PRESS.id))
    }

    @Test
    fun `observeAll is sorted by name and reacts to changes`() = runTest {
        dao.observeAll().test {
            assertEquals(emptyList(), awaitItem())

            dao.upsertAll(listOf(SQUAT, BENCH_PRESS))
            assertEquals(listOf(BENCH_PRESS, SQUAT), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `observeByMuscleGroup filters other groups out`() = runTest {
        dao.upsertAll(listOf(BENCH_PRESS, SQUAT))

        dao.observeByMuscleGroup("legs").test {
            assertEquals(listOf(SQUAT), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `deleteById removes entity`() = runTest {
        dao.upsertAll(listOf(BENCH_PRESS))

        dao.deleteById(BENCH_PRESS.id)

        assertNull(dao.findById(BENCH_PRESS.id))
    }

    private companion object {
        val BENCH_PRESS = ExerciseEntity(
            id = "bench-press",
            name = "Жим лёжа",
            muscleGroup = "chest",
            accent = "middle",
            equipment = "barbell",
        )
        val SQUAT = ExerciseEntity(
            id = "squat",
            name = "Приседания",
            muscleGroup = "legs",
            accent = null,
            equipment = "barbell",
        )
    }
}
