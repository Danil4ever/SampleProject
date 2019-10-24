package kz.simplecode.sampleandroidproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Book(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    var name: String,
    var pages: Int
)