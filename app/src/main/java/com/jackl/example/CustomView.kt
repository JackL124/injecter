package com.jackl.example

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.jackl.injecter.annotation.BindClick
import com.jackl.injecter.annotation.BindView
import com.jackl.injecter.inject.Injecter


/**
 * @description:
 * @author: jackl
 * @date:  2021/12/6
 **/
class CustomView : ConstraintLayout {

    var inflate:View?=null

    @BindView(id = R.id.custom)
    val customView:TextView?=null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        inflate = inflate(context, R.layout.view_custom,this)
        Injecter().inject(this)
    }

    @BindClick(ids = [R.id.custom])
    fun onClick(view: View){
        Toast.makeText(context,(view as TextView).text.toString(), Toast.LENGTH_SHORT).show()
    }

}