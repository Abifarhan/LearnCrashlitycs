package com.abifarhan.learncrashlitycs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    var tweetList = ArrayList<Tweet>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_tweet -> startActivity(Intent(this, UpdateActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    fun getData() {
        FirebaseFirestore.getInstance().collection("Posts")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Toast.makeText(this, "this error ${error.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (value != null) {
                        if (value.isEmpty) {
                            Toast.makeText(this, "Your data is Empty", Toast.LENGTH_SHORT).show()
                        }else {
                            val documents = value.documents

                            for (document in documents) {
                                val tweetText = document.get("tweet") as String
                                val email = document.get("user") as String

                                val tweet = Tweet(tweetText, email)

                                tweetList.add(tweet)


                            }
                        }
                    }
                }
            }
    }
}