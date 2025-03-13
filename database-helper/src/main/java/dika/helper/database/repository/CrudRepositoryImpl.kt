package dika.helper.database.repository

import dika.helper.database.CrudDao
import dika.helper.database.exception.RoomException
import dika.helper.core.Result
import io.reactivex.rxjava3.core.Single

class CrudRepositoryImpl<T>(
    private val crudDao: CrudDao<T>
) : CrudRepository<T> {

    override fun save(entity: T): Single<Result<Long>> {
        return Single.create {
            try {
                val id = crudDao.insert(entity)
                it.onSuccess(Result.Success(id))
            } catch (e: Exception) {
                val error = RoomException(e.message, e)
                it.onSuccess(Result.Failure(error))
            }
        }
    }

    override fun delete(entity: T): Single<Result<Unit>> {
        return Single.create {
            try {
                val result = crudDao.delete(entity)
                it.onSuccess(Result.Success(result))
            } catch (e: Exception) {
                val error = RoomException(e.message, e)
                it.onSuccess(Result.Failure(error))
            }
        }
    }

    override fun update(entity: T): Single<Result<Unit>> {
        return Single.create {
            try {
                val result = crudDao.update(entity)
                it.onSuccess(Result.Success(result))
            } catch (e: Exception) {
                val error = RoomException(e.message, e)
                it.onSuccess(Result.Failure(error))
            }
        }
    }
}