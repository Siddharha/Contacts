package `in`.creativelizard.contacts.beans

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactItem (
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "number") val number: String?

){
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}