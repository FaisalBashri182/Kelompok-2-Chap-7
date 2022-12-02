package tsm.bdg.ch6group

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tsm.bdg.ch6group.data.local.GameDao
import tsm.bdg.ch6group.data.local.model.Game


@Database(
    entities = [Game::class],
    version = 1
)

abstract class Database1 : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private var INSTANCE1: Database1? = null

        fun getInstance(context: Context): Database1? {
            if (INSTANCE1 == null) {
                synchronized(Database1::class.java) {
                    INSTANCE1 = Room.databaseBuilder(
                        context,
                        Database1::class.java,
                        "Game.db",
                    ).build()
                }
            }
            return INSTANCE1
        }
    }


}

