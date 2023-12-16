package com.example.easyshare
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aBc = "" + "abc"
        Log.d("MainActivity", "onCreate: $aBc")

        if (true) {
            return
        }
    }
}

