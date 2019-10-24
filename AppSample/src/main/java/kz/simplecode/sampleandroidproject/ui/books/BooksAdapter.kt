package kz.simplecode.sampleandroidproject.ui.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.simplecode.sampleandroidproject.R
import kz.simplecode.sampleandroidproject.data.models.Book
import kz.simplecode.sampleandroidproject.mvp.books.BooksPresenter
import java.util.*


class BooksAdapter(private val presenter: BooksPresenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val data: ArrayList<Book> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LAYOUT_BOOK -> VHBook(inflate(parent, viewType), presenter)
            else -> throw incorrectOnCreateViewHolder()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            LAYOUT_BOOK -> (holder as VHBook).bind(data[position])
        }
    }

    private fun inflate(parent: ViewGroup, resource: Int): View {
        val mInflater = LayoutInflater.from(parent.context)
        return mInflater.inflate(resource, parent, false)
    }

    private fun incorrectOnCreateViewHolder(): IllegalStateException {
        return IllegalStateException("Incorrect ViewType found")
    }

    override fun getItemViewType(position: Int): Int {
        return LAYOUT_BOOK
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(items: List<Book>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }


    fun addAll(items: List<Book>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun add(item: Book) {
        data.add(item)
        notifyItemInserted(data.lastIndex)
    }

    fun addToBegin(item: Book) {
        data.add(0, item)
        notifyItemInserted(0)
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun remove(item: Book) {
        notifyItemRemoved(data.indexOf(item))
        data.remove(item)
    }

    fun remove(position: Int) {
        notifyItemRemoved(position)
        data.removeAt(position)
    }

    companion object {
        private const val LAYOUT_BOOK = R.layout.item_book
    }
}