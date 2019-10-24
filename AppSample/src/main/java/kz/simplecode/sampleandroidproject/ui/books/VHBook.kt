package kz.simplecode.sampleandroidproject.ui.books

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_book.*
import kz.simplecode.sampleandroidproject.data.models.Book
import kz.simplecode.sampleandroidproject.mvp.books.BooksPresenter

class VHBook(override val containerView: View, private val presenter: BooksPresenter) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: Book) {
        tvTitle.text = item.name
        tvPages.text = item.pages.toString()

        containerView.setOnClickListener { presenter.editBook(item) }

        ivTrash.setOnClickListener { presenter.deleteBook(item) }

    }
}