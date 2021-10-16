package com.abifarhan.learncrashlitycs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abifarhan.learncrashlitycs.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    var password = ""
    var email = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    fun SignInClick(view: android.view.View) {
        email = binding.edtInputEmail.text.toString().trim()
        password = binding.edtInputPassword.text.toString().trim()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, HomeActivity::class.java))
            }

            .addOnFailureListener {exception ->
                Log.d("Login User Error", "Create User Error ${exception.localizedMessage}")
            }
    }


    fun SignUpClick(view: android.view.View) {
        email = binding.edtInputEmail.text.toString().trim()
        password = binding.edtInputPassword.text.toString().trim()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                startActivity(Intent(this, HomeActivity::class.java))
            }

            .addOnFailureListener { exception ->
                Log.d("Create User Error", "Create User Error ${exception.localizedMessage}")
            }
    }
}