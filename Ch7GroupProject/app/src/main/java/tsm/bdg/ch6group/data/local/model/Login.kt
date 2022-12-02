package tsm.bdg.ch6group.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class Login(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "time") val time: String
)
