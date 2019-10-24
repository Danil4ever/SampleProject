package kz.simplecode.sampleandroidproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.simplecode.sampleandroidproject.data.models.Book


@Database(entities = [Book::class], version = 1)
abstract class SampleDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: SampleDatabase? = null

        fun getInstance(context: Context): SampleDatabase? {
            if (INSTANCE == null) {
                synchronized(SampleDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, SampleDatabase::class.java, "sample.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}