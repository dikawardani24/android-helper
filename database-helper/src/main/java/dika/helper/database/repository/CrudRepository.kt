package dika.helper.database.repository

import dika.helper.core.Result
import io.reactivex.rxjava3.core.Single

interface CrudRepository<T> {
    fun save(entity: T): Single<Result<Long>>
    fun delete(entity: T): Single<Result<Unit>>
    fun update(entity: T): Single<Result<Unit>>
}