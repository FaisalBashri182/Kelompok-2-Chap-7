package tsm.bdg.ch6group.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tsm.bdg.ch6group.data.model.User


@Database(
    entities = [User::class],
    version = 1
)

abstract class Db : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: Db? = null

        fun getInstance(context: Context): Db? {
            if (INSTANCE == null) {
                synchronized(Db::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        Db::class.java,
                        "User.db",
                    ).build()
                }
            }
            return INSTANCE
        }
    }


}

