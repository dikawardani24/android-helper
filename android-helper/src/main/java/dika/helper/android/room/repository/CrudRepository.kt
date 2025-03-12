package dika.helper.android.room.repository

import dika.helper.android.Result
import io.reactivex.Single

interface CrudRepository<T> {
    fun save(entity: T): Single<Result<Long>>
    fun delete(entity: T): Single<Result<Unit>>
    fun update(entity: T): Single<Result<Unit>>
}