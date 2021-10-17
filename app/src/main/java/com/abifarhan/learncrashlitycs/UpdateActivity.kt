package com.abifarhan.learncrashlitycs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.abifarhan.learncrashlitycs.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private var _binding:ActivityUpdateBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnUpload.setOnClickListener {
            val tweet = binding.edtTweetText.text.toString().trim()


        }

    }


}