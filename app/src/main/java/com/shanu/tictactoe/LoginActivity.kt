package com.shanu.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference
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
                var currentUser = mAuth!!.currentUser
                // Save details to database
                if (currentUser != null) {
                    myRef.child("Users")
                        .child(splitString(currentUser.email.toString()))
                        .child("Request").setValue(currentUser.uid)
                }
                loadMain()
            }else{
                Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_SHORT).show()

            }
        }



    }

    override fun onStart() {
        super.onStart()
        loadMain()
        }
    
    fun loadMain(){
        var currentUser = mAuth!!.currentUser
        if(currentUser!=null) {

            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", currentUser.email)
            intent.putExtra("uid", currentUser.uid)
            startActivity(intent)
        }


    }

    fun splitString(str:String):String{
        var split = str.split("@")
        return split[0]
    }
}