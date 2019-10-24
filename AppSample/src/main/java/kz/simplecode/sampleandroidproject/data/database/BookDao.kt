package kz.simplecode.sampleandroidproject.data.database

import androidx.room.*
import kz.simplecode.sampleandroidproject.data.models.Book

@Dao
interface BookDao {


    @Query("SELECT * FROM book")
    suspend fun getAll(): List<Book>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getBookById(id: String): Book

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

}