package kz.simplecode.sampleandroidproject.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.simplecode.sampleandroidproject.R
import kz.simplecode.sampleandroidproject.data.models.MovieShort
import kz.simplecode.sampleandroidproject.mvp.movies.MoviesPresenter
import java.util.*


class MoviesAdapter(private val presenter: MoviesPresenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val data: ArrayList<MovieShort> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LAYOUT_RATING -> VHMovie(inflate(parent, viewType), presenter)
            else -> throw incorrectOnCreateViewHolder()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            LAYOUT_RATING -> (holder as VHMovie).bind(data[position], position)
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
        return LAYOUT_RATING
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(items: List<MovieShort>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }


    fun addAll(items: List<MovieShort>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun add(item: MovieShort) {
        data.add(item)
        notifyItemInserted(data.lastIndex)
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun remove(item: MovieShort) {
        notifyItemRemoved(data.indexOf(item))
        data.remove(item)
    }

    fun remove(position: Int) {
        notifyItemRemoved(position)
        data.removeAt(position)
    }

    companion object {
        private const val LAYOUT_RATING = R.layout.item_movie
    }
}