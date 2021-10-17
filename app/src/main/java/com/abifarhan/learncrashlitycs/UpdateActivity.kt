package com.abifarhan.learncrashlitycs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.abifarhan.learncrashlitycs.databinding.ActivityUpdateBinding
import com.google.firebase.Timestamp
import com.google.firebase.Timestamp.now
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UpdateActivity : AppCompatActivity() {
    private var _binding:ActivityUpdateBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnUpload.setOnClickListener {
            val tweet = binding.edtTweetText.text.toString().trim()
            val currentUser = FirebaseAuth.getInstance().currentUser?.email
            val date = Timestamp.now()

            val dataMap = hashMapOf<String, Any>()
            dataMap["tweet"] = tweet
            dataMap["user"] = currentUser!!
            FirebaseFirestore.getInstance().collection("Posts")
                .add(dataMap)
                .addOnSuccessListener {
                    finish()
                }

                .addOnFailureListener {
                    Log.d("this","your error is ${it.localizedMessage }")
                }
        }

    }


}