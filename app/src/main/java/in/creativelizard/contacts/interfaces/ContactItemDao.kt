package `in`.creativelizard.contacts.interfaces

import `in`.creativelizard.contacts.beans.ContactItem
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.concurrent.Future

@Dao
interface ContactItemDao {
    @Insert
    fun insertContact(contactItem: ContactItem)

    @Delete
    fun deleteContact(contactItem: ContactItem)

    @Query("SELECT * FROM ContactItem")
    fun getAllContactDataData(): List<ContactItem>
}