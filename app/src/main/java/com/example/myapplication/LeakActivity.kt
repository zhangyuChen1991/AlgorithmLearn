package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.Constants

class LeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Constants.activities.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Constants.TAG,"LeakActivity.onDestroy");
    }
}