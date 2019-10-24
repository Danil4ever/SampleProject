package simple.code.base.utils

import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import simple.code.base.R

object SnackbarUtils {
    private val DEFAULT_MAX_LINES = 5

    @JvmOverloads
    fun multilineSnackbar(view: View, @StringRes resId: Int, duration: Int, maxLines: Int = -1): Snackbar {
        return makeMultiline(Snackbar.make(view, resId, duration), maxLines)
    }

    @JvmOverloads
    fun multilineSnackbar(view: View, text: CharSequence, duration: Int, maxLines: Int = -1): Snackbar {
        return makeMultiline(Snackbar.make(view, text, duration), maxLines)
    }

    private fun makeMultiline(snackbar: Snackbar, maxLines: Int): Snackbar {
        val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
        textView.maxLines = if (maxLines > 0) maxLines else DEFAULT_MAX_LINES
        return snackbar
    }

    fun showSnackbar(parent: View, stringId: Int) {
        val snackbar = multilineSnackbar(parent, stringId, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(android.R.string.ok) { snackbar.dismiss() }
        snackbar.show()
    }

    fun showSnackbar(parent: View, s: String) {
        val snackbar = multilineSnackbar(parent, Html.fromHtml("<font color=\"#ffffff\">$s</font>"), Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(android.R.string.ok) { snackbar.dismiss() }
        snackbar.show()
    }
}