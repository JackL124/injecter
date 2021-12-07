package com.jackl.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jackl.injecter.annotation.BindClick
import com.jackl.injecter.annotation.BindView
import com.jackl.injecter.inject.Injecter

/**
 * @description:
 * @author: jackl
 * @date:  2021/12/6
 **/
class TestFragment : Fragment() {

    @BindView(id = R.id.id1)
    val view1: TextView?=null
    var v:View?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_test, container, false)
        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Injecter().inject(this)
        v?.isClickable=true
        view1?.text="fragment修改的文案"
    }

    @BindClick(ids = [R.id.id1])
    fun onClick(view: View){
        Toast.makeText(context,(view as TextView).text.toString(), Toast.LENGTH_SHORT).show()
    }

}