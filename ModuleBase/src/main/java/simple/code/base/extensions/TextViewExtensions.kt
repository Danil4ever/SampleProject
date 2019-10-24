package simple.code.base.extensions

import android.os.Handler
import android.widget.TextView


private var mText: CharSequence? = null
private var mIndex: Int = 0
private var mDelay: Long = 150 // in ms

private val mHandler = Handler()

fun TextView.animateText(txt: CharSequence) {
    mText = txt
    mIndex = 0
    text = ""

    val characterAdder = object : Runnable {
        override fun run() {
            text = mText!!.subSequence(0, mIndex++)
            if (mIndex <= mText!!.length) {
                mHandler.postDelayed(this, mDelay)
            }
        }
    }

    mHandler.removeCallbacks(characterAdder)
    mHandler.postDelayed(characterAdder, mDelay)
}

fun TextView.setCharacterDelay(m: Long) {
    mDelay = m
}