package digital.klik.helper.dataStore.room

import androidx.room.*

interface CrudDao<T> {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg entities: T): List<Long>

    @Delete
    fun delete(entity: T)

    @Update
    fun update(entity: T)
}