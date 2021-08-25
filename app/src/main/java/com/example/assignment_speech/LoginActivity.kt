package com.example.assignment_speech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.password
import kotlinx.android.synthetic.main.activity_signup.username

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginText.setOnClickListener{
            var intent=Intent(this,SignupActivity::class.java);
            startActivity(intent)

        }
        btn_login.setOnClickListener {
            var editname=username.text.toString().trim()
            var editpassword=password.text.toString().trim()
            val databaseReference= FirebaseDatabase.getInstance().getReference("Users")
            var checkUser:Query=databaseReference.orderByChild("name").equalTo(editname)

            checkUser.addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        var passDB=snapshot.child(editname).child("password").getValue(String::class.java)
                        if(passDB.equals(editpassword)){
                            var name=snapshot.child(editname).child("name").getValue(String::class.java)
                            var stringCount=snapshot.child(editname).child("stringCount").getValue(String::class.java)
                            var intent= Intent(this@LoginActivity,MainActivity::class.java)
                            intent.putExtra("dataDB",stringCount)
                            intent.putExtra("username",name)
                            startActivity(intent)
                            finish()
                        }
                    }else{
                        Toast.makeText(this@LoginActivity,"User not Exist",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}