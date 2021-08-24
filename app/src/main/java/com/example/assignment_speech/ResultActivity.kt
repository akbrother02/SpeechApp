package com.example.assignment_speech

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val data=intent.getStringExtra("text")
        count_freq(data!!)


    }


    fun count_freq(str: String) {
        val resultText = findViewById<TextView>(R.id.text)
        var result=""
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
    }
}