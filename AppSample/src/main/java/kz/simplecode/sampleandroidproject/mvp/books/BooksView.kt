package kz.simplecode.sampleandroidproject.mvp.books

import kz.simplecode.sampleandroidproject.data.models.Book
import simple.code.base.mvp.BaseMvpFragmentView


interface BooksView : BaseMvpFragmentView {
    fun showBooks(list: List<Book>)
    fun showEmpty(empty: Boolean)
    fun showDialogBookAdd(book: Book?)
    fun deleteBook(book: Book)
    fun addBook(book: Book)
}
