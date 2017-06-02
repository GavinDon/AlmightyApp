package com.ln.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start_kotlin.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class StartKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_kotlin)
        initViews(2)
    }

    fun initViews(a: Int) {
        alert("hi you are zhao si or liu neng?"){
            yesButton {alert { "我是赵皿" }  }
            noButton {if (a is Int) { alert("String " + "concatenation") } }
        }.show()
        //show
        mkotlin.setOnClickListener { v ->toast(mkotlin.text)
        }
    }
}
