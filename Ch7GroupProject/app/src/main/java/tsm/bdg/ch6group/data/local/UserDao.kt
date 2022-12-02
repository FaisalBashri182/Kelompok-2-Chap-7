package tsm.bdg.ch6group.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import tsm.bdg.ch6group.data.local.model.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun insert(user: User): Long

    @Delete
    fun delete(user: User): Int

    @Update
    fun update(user: User): Int

    @Query("select * from user")
    fun getUsers(): MutableList<User>

    @Query("select * from user where id = :id")
    fun getUser(id: Int) : User

    @Query("select * from user where name = :name")
    fun getName(name: String) : User

    @Query("SELECT * FROM user WHERE name = :name and password = :password")
    fun login(name : String, password: String ) : User



}