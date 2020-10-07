package digital.klik.helper.dataStore.room.repository

import digital.klik.helper.dataStore.room.CrudDao
import digital.klik.helper.dataStore.room.exception.RoomException
import io.reactivex.Single

class CrudRepositoryImpl<T>(
    private val crudDao: CrudDao<T>
) : CrudRepository<T> {

    override fun save(entity: T): Single<Result<Long>> {
        return Single.create {
            try {
                val id = crudDao.insert(entity)
                it.onSuccess(Result.success(id))
            } catch (e: Exception) {
                val error = RoomException(e.message, e)
                it.onSuccess(Result.failure(error))
            }
        }
    }

    override fun delete(entity: T): Single<Result<Unit>> {
        return Single.create {
            try {
                val result = crudDao.delete(entity)
                it.onSuccess(Result.success(result))
            } catch (e: Exception) {
                val error = RoomException(e.message, e)
                it.onSuccess(Result.failure(error))
            }
        }
    }

    override fun update(entity: T): Single<Result<Unit>> {
        return Single.create {
            try {
                val result = crudDao.update(entity)
                it.onSuccess(Result.success(result))
            } catch (e: Exception) {
                val error = RoomException(e.message, e)
                it.onSuccess(Result.failure(error))
            }
        }
    }
}