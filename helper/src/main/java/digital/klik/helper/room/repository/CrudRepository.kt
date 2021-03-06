package digital.klik.helper.room.repository

import io.reactivex.Single
import digital.klik.helper.Result

interface CrudRepository<T> {
    fun save(entity: T): Single<Result<Long>>
    fun delete(entity: T): Single<Result<Unit>>
    fun update(entity: T): Single<Result<Unit>>
}