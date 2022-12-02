package tsm.bdg.ch6group.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tsm.bdg.ch6group.data.local.model.Login


@Database(
    entities = [Login::class],
    version = 1
)

abstract class Dblogin : RoomDatabase() {
    abstract fun loginDao(): LoginDao

    companion object {
        private var INSTANCE: Dblogin? = null

        fun getInstance(context: Context): Dblogin? {
            if (INSTANCE == null) {
                synchronized(Dblogin::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        Dblogin::class.java,
                        "Login.db",
                    ).build()
                }
            }
            return INSTANCE
        }
    }


}

