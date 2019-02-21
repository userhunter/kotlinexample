package com.example.kt.test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import example.pacewear.com.appname.MainActivity
import example.pacewear.com.appname.R

import kotlinx.android.synthetic.main.kt_main.*

/**
 * autour: bobbylu
 * date: 2019/2/20 on 20:04
 */

class KtActivity:Activity(){

    val TAG = "KtActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kt_main)

        initView()
        print(TAG,"onCreate start.")

    }

    fun initView(){
        kt_button.setOnClickListener{
//            toast("hello")
//            transferToActivity()
            jump()
        }
    }

    fun print(tag:String,message:String){
        Log.d(tag,message)
    }

    fun toast(mess:String,length:Int = Toast.LENGTH_SHORT){
        Toast.makeText(this,mess,length).show()
    }

    fun transferToActivity(){
        val  intent = Intent()
        intent.setClass(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun jump(){
        val  intent = Intent()
        intent.setClass(this,KtSecondActivity::class.java)
        startActivity(intent)

    }
}