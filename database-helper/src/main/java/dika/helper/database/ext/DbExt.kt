package dika.helper.database.ext

import androidx.room.RoomDatabase

fun <T: RoomDatabase> T.executeDb(action: T.() -> Unit) {
    action()
    close()
}