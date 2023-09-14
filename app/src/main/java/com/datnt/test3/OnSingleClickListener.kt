package com.datnt.test3

import android.os.SystemClock
import android.view.View

abstract class OnSingleClickListener : View.OnClickListener {

    var mLastClickTime = 0L

    abstract fun onSingleClick(view: View)

    override fun onClick(v: View?) {
        mLastClickTime = SystemClock.elapsedRealtime()
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return
        }
        onSingleClick(v!!)
    }
}