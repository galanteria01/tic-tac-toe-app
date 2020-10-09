package com.shanu.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        mAuth = FirebaseAuth.getInstance();
    }

    fun buClick(view: View){
        var email = emailSpace.text.toString()
        var password = passSpace.text.toString()
        loginToFirebase(email,password)

    }

    fun loginToFirebase(email:String,password:String){
        mAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->

            if(task.isSuccessful){
                Toast.makeText(applicationContext,"Login Success",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_SHORT).show()

            }
        }

    }
}