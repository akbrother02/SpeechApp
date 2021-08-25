package com.example.assignment_speech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val data=intent.getStringExtra("text")
        val dbCount=intent.getStringExtra("DbString")
        val username=intent.getStringExtra("name")
        count_freq(data!!,dbCount,username)

        logout.setOnClickListener{
            var intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    fun count_freq(str: String, dbCount: String?, username: String?) {
        val resultText = findViewById<TextView>(R.id.text)
        var result=dbCount
        val mp: MutableMap<String, Int> = TreeMap()
        // Splitting to find the word
        val arr = str.split(" ").toTypedArray()
        // Loop to iterate over the words
        for (i in arr.indices) {
            // Condition to check if the array element is present the hash-map
            if (mp.containsKey(arr[i])) {
                mp[arr[i]] = mp[arr[i]]!! + 1
            } else {
                mp[arr[i]] = 1
            }
        }
        for ((key, value) in mp) {
            result=result+"\n"+key + " - " + value
        }
        resultText.setText(result)
        saveDatatoDatabase(result,username)
    }

    private fun saveDatatoDatabase(result: String?, username: String?) {
         var rootNode= FirebaseDatabase.getInstance()
        var  reference=rootNode.getReference("Users")
        reference.child(username!!).child("stringCount").setValue(result)
    }
}