package dika.helper.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

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