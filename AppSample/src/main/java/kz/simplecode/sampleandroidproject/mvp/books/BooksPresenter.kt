package kz.simplecode.sampleandroidproject.mvp.books

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.launch
import kz.simplecode.sampleandroidproject.data.models.Book
import kz.simplecode.sampleandroidproject.mvp.sample.SampleFragmentPresenter


@InjectViewState
class BooksPresenter : SampleFragmentPresenter<BooksView>() {

    fun loadBooks() {
        uiScope.launch {
            try {
                val books = database.bookDao().getAll()
                viewState.showEmpty(books.isEmpty())
                viewState.showBooks(books)
            } catch (error: Exception) {
                showError(error)
            }
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
            try {
                database.bookDao().delete(book)
                viewState.deleteBook(book)
                viewState.showEmpty(database.bookDao().getAll().isEmpty())
            } catch (error: Exception) {
                showError(error)
            }

        }
    }

    fun saveBook(book: Book) {
        uiScope.launch {
            try {
                database.bookDao().insert(book)
                viewState.addBook(book)
                viewState.showEmpty(database.bookDao().getAll().isEmpty())
            } catch (error: Exception) {
                showError(error)
            }
        }
    }


}


