package tsm.bdg.ch6group.data.local

import androidx.room.*
import tsm.bdg.ch6group.data.local.model.Login


@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(login: Login): Long

    @Delete
    fun delete(login: Login): Int

    @Update
    fun update(login: Login): Int

    @Query("SELECT * FROM login")
    fun getAllDataLogin(): MutableList<Login>

}