package com.example.assignment_speech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.loginText
import kotlinx.android.synthetic.main.activity_signup.password
import kotlinx.android.synthetic.main.activity_signup.username

class SignupActivity : AppCompatActivity() {

    lateinit var rootNode:FirebaseDatabase
    lateinit var reference:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        loginText.setOnClickListener{
            var intent= Intent(this,LoginActivity::class.java);
            startActivity(intent)
        }

        btn_Register.setOnClickListener {
            rootNode= FirebaseDatabase.getInstance()
            reference=rootNode.getReference("Users")
            var username=username.text.toString()
            var password=password.text.toString()
            var userData=User(username,"",password)
            reference.child(username).setValue(userData)
            Toast.makeText(this,"User is created , Now login ",Toast.LENGTH_LONG).show()
            var intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}