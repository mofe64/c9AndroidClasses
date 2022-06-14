package com.nubari.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.nubari.myapplication.databinding.ActivityMainBinding
import com.nubari.myapplication.databinding.TestBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nameInput = binding.nameInput
        val passwordInput = binding.passwordInput
        val btn = binding.loginBtn

        btn.setOnClickListener {
            val providedName = nameInput.text
            val providedPassword = passwordInput.text
            val message = "logging in with name $providedName and password $providedPassword"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }


    }


}