package com.devkey.cupones.common.adapters

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.devkey.cupones.R

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean){
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("isCreated")
fun bindIsCreated(tv: TextView, isCreated: Boolean){
    if (isCreated){
        tv.setText(R.string.tv_message)
        tv.setTextColor(Color.BLACK)
    }else{
        tv.setText(R.string.main_avaliable_coupon)
        tv.setTextColor(Color.BLUE)
    }
}
