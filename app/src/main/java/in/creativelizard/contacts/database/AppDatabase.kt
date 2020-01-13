package `in`.creativelizard.contacts.database

import `in`.creativelizard.contacts.beans.ContactItem
import `in`.creativelizard.contacts.interfaces.ContactItemDao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactItem::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactItemDao
}