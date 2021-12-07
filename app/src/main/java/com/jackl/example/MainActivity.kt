package com.jackl.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.jackl.injecter.annotation.BindClick
import com.jackl.injecter.annotation.BindInflate
import com.jackl.injecter.annotation.BindView
import com.jackl.injecter.inject.Injecter

@BindInflate(id = R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    @BindView(id = R.id.test1)
    val view1:TextView?=null

    @BindView(id = R.id.fragment)
    val view2:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injecter().inject(this)
        view1?.text="activity修改的文案"
    }


    @BindClick(ids = [R.id.test1,R.id.test2,R.id.fragment])
    fun onClick(view: View){
        Toast.makeText(this,(view as TextView).text.toString(),Toast.LENGTH_SHORT).show()
        when(view.id){
            R.id.fragment->{
                val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.container, TestFragment()).commitAllowingStateLoss()
            }
        }
    }

}