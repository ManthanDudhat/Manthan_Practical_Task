package com.android.manthan.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.manthan.R
import com.android.manthan.databinding.ActivityMainBinding
import com.android.manthan.databinding.ActivityTherapyBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners()= with(binding){
        btnTaskOne.setOnClickListener {
           startActivity(Intent(this@MainActivity,TherapyActivity::class.java))
        }

        btnTaskTwo.setOnClickListener {
            startActivity(Intent(this@MainActivity,MachineTestActivity::class.java))
        }
    }
}