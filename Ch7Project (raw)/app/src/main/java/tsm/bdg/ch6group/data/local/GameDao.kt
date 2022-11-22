package tsm.bdg.ch6group.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import tsm.bdg.ch6group.data.model.Game


@Dao
interface GameDao {

    @Insert(onConflict = REPLACE)
    fun insert(game: Game): Long

    @Delete
    fun delete(game: Game): Int

    @Update
    fun update(game: Game): Int

    @Query("SELECT * FROM game")
    fun getAllDataGame(): MutableList<Game>

    @Query("SELECT * FROM game WHERE id = :id")
    fun getGame(id: Int): MutableList<Game>

    @Query("SELECT * FROM game WHERE name = :name AND win=1")
    fun getWin(name : String): MutableList<Game>

    @Query("SELECT * FROM game WHERE name = :name AND lose=1")
    fun getLose(name : String): MutableList<Game>

    @Query("SELECT * FROM game GROUP BY date")
    fun getHistory(): MutableList<Game>



}