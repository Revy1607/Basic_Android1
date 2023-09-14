package com.datnt.test3

import android.view.View

fun View.setOnSingleClick(onClick: ((View?) -> Unit)){
    setOnClickListener(object : OnSingleClickListener(){
        override fun onSingleClick(view: View) {
            onClick.invoke(view)
        }

    })
}

fun View.toast(){
}