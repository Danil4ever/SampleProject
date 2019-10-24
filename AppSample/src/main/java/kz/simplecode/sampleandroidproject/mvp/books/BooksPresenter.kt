package kz.simplecode.sampleandroidproject.mvp.books

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.launch
import kz.simplecode.sampleandroidproject.data.models.Book
import kz.simplecode.sampleandroidproject.mvp.sample.SampleFragmentPresenter


@InjectViewState
class BooksPresenter : SampleFragmentPresenter<BooksView>() {

    fun loadBooks() {
        uiScope.launch {
            val books = database.bookDao().getAll()
            viewState.showEmpty(books.isEmpty())
            viewState.showBooks(books)
        }
    }

    fun addBook() {
        viewState.showDialogBookAdd(null)
    }

    fun editBook(book: Book) {
        viewState.showDialogBookAdd(book)
    }

    fun deleteBook(book: Book) {
        uiScope.launch {
            database.bookDao().delete(book)
            viewState.deleteBook(book)
            viewState.showEmpty(database.bookDao().getAll().isEmpty())
        }
    }

    fun saveBook(book: Book) {
        uiScope.launch {
            database.bookDao().insert(book)
            viewState.addBook(book)
            viewState.showEmpty(database.bookDao().getAll().isEmpty())
        }
    }


}


