package kz.simplecode.sampleandroidproject.ui.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import kz.simplecode.sampleandroidproject.data.models.MovieShort
import kz.simplecode.sampleandroidproject.mvp.movies.MoviesPresenter
import kz.simplecode.sampleandroidproject.utils.SampleConstants
import java.util.*

class VHMovie(override val containerView: View, private val presenter: MoviesPresenter) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(details: MovieShort, position: Int) {
        tvDialogTitle.text = details.title

        Picasso.get().load(SampleConstants.IMAGE_URL_W500 + details.posterPath).into(ivPoster)

        tvDescription.text = details.overview

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = details.releaseDate.time
        tvReleaseYear.text = calendar.get(Calendar.YEAR).toString()

        containerView.setOnClickListener {
            presenter.goToMovieDetails(details)
        }

    }
}