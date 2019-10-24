package kz.simplecode.sampleandroidproject.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_books.*
import kz.simplecode.sampleandroidproject.R
import kz.simplecode.sampleandroidproject.data.models.Book
import kz.simplecode.sampleandroidproject.mvp.books.BooksPresenter
import kz.simplecode.sampleandroidproject.mvp.books.BooksView
import simple.code.base.ui.BaseMvpFragment

class BooksFragment : BaseMvpFragment<BooksPresenter>(), BooksView {

    private lateinit var adapter: BooksAdapter

    @InjectPresenter
    override lateinit var presenter: BooksPresenter

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_books
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener { presenter.addBook() }

        adapter = BooksAdapter(presenter)
        rvBooks.adapter = adapter

        presenter.loadBooks()

    }

    override fun onShow() {
        super.onShow()
        presenter.showBottomNavigation(true)
    }

    override fun showBooks(list: List<Book>) {
        adapter.setData(list)
    }

    override fun addBook(book: Book) {
        adapter.addToBegin(book)
    }

    override fun deleteBook(book: Book) {
        adapter.remove(book)
    }

    override fun showEmpty(empty: Boolean) {
        rvBooks.visibility = if (empty) View.GONE else View.VISIBLE
        llEmpty.visibility = if (empty) View.VISIBLE else View.GONE
    }

    override fun showDialogBookAdd(book: Book?) {
        val isEdit = book != null
        val currentBook: Book
        val li = LayoutInflater.from(context)

        val promptsView = li.inflate(R.layout.dialog_book, null)

        val btnFirst = promptsView.findViewById<Button>(R.id.btnFirst)
        val btnSecond = promptsView.findViewById<Button>(R.id.btnSecond)

        val tvDialogTitle = promptsView.findViewById<TextView>(R.id.tvDialogTitle)

        val etTitle = promptsView.findViewById<TextInputEditText>(R.id.etName)
        val etPages = promptsView.findViewById<TextInputEditText>(R.id.etPages)

        if (isEdit) {
            tvDialogTitle.text = context!!.getString(R.string.edit)
            etTitle.setText(book!!.name)
            etPages.setText(book.pages.toString())
            currentBook = book
        } else {
            currentBook = Book(name = "", pages = 0)
        }

        val mDialogBuilder = AlertDialog.Builder(context!!)
        mDialogBuilder.setView(promptsView)
        mDialogBuilder.setCancelable(true)

        val alertDialogPin = mDialogBuilder.create()
        alertDialogPin.window!!.setBackgroundDrawable(context!!.resources.getDrawable(android.R.color.transparent))

        alertDialogPin.show()

        btnFirst.setOnClickListener {
            currentBook.name = etTitle.text.toString()
            currentBook.pages = etPages.text.toString().toInt()
            presenter.saveBook(currentBook)
            alertDialogPin.dismiss()
        }

        btnSecond.setOnClickListener { alertDialogPin.dismiss() }
    }
}
