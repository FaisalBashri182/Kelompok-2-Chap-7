package tsm.bdg.ch6group.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "win") val win: Int,
    @ColumnInfo(name = "lose") val lose: Int,
    @ColumnInfo(name = "draw") val draw: Int,
)
