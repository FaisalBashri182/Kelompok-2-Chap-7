package tsm.bdg.ch6group.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "password") val password : String,
    @ColumnInfo(name = "avatar") val avatar : String
)


