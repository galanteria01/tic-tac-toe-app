package com.shanu.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    var myEmail:String?=null



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        var bundle:Bundle = intent.extras!!
        myEmail = bundle.getString("email")
    }

    fun buttonClick(view: View) {
        val selectedButton = view as Button
        Log.d("buclick:", selectedButton.id.toString())
        var cellId = 0
        when (selectedButton.id) {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9

        }
        playGame(cellId, selectedButton)


    }

    var activePlayer = 1
    var playerOne = ArrayList<Int>()
    var playerTwo = ArrayList<Int>()
    @SuppressLint("ResourceAsColor")
    fun playGame(cellId: Int, selectedbutton: Button) {

        if (activePlayer == 1) {
            selectedbutton.text = "X"
            selectedbutton.setBackgroundResource(R.color.cyan)
            selectedbutton.setTextColor(R.color.black)
            playerOne.add(cellId)
            activePlayer = 2
            autoPlay()


        } else {
            selectedbutton.text = "O"
            selectedbutton.setBackgroundResource(R.color.white)
            selectedbutton.setTextColor(R.color.black)
            playerTwo.add(cellId)
            activePlayer = 1

        }
        checkWinner()

    }

    fun checkWinner() {
        var winner = -1

        // row 1
        if (playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3)) {
            winner = 1

        }
        else if (playerTwo.contains(1) && playerTwo.contains(2) && playerTwo.contains(3)) {
            winner = 2
        }

        //row 2
        if (playerOne.contains(4) && playerOne.contains(5) && playerOne.contains(6)) {
            winner = 1
        }
        else if (playerTwo.contains(4) && playerTwo.contains(5) && playerTwo.contains(6)) {
            winner = 2
        }

        //row 3
        if (playerOne.contains(7) && playerOne.contains(8) && playerOne.contains(9)) {
            winner = 1
        }
        else if (playerTwo.contains(7) && playerTwo.contains(8) && playerTwo.contains(9)) {
            winner = 2
        }

        // col 1
        if (playerOne.contains(1) && playerOne.contains(4) && playerOne.contains(7)) {
            winner = 1
        }
        else if (playerTwo.contains(1) && playerTwo.contains(4) && playerTwo.contains(7)) {
            winner = 2
        }

        //col 2
        if (playerOne.contains(2) && playerOne.contains(5) && playerOne.contains(8)) {
            winner = 1
        }
        else if (playerTwo.contains(2) && playerTwo.contains(5) && playerTwo.contains(8)) {
            winner = 2
        }

        //col 3
        if (playerOne.contains(3) && playerOne.contains(6) && playerOne.contains(9)) {
            winner = 1
        }
        else if (playerTwo.contains(3) && playerTwo.contains(6) && playerTwo.contains(9)) {
            winner = 2
        }

        // Final check
        if(winner == 1){
            Toast.makeText(this, "Player one wins ", Toast.LENGTH_LONG).show()

            resetGame()


        }else if(winner ==2){
            Toast.makeText(this, "Player two wins ", Toast.LENGTH_LONG).show()
            resetGame()

        }
    }

    fun autoPlay(){
        val emptyCell = arrayListOf<Int>()
        for(cellId in 1..9){
            if(!(playerOne.contains(cellId) || playerTwo.contains(cellId))){
                emptyCell.add(cellId)

            }
        }
        val r = Random()
        val randIndex = r.nextInt(emptyCell.size - 0) + 0
        val cellId = emptyCell[randIndex]
        val selectedbutton:Button?
        selectedbutton = when(cellId){
            1 -> button1
            2 -> button2
            3 -> button3
            4 -> button4
            5 -> button5
            6 -> button6
            7 -> button7
            8 -> button8
            9 -> button9
            else -> {button1}
        }
        playGame(cellId, selectedbutton)


    }

    fun resetGame(){
        activePlayer = 1
        playerOne.clear()
        playerTwo.clear()
        for(cellId in 1..9) {
            var selectedbutton: Button?
            selectedbutton = when (cellId) {
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {
                    button1
                }
            }
            selectedbutton.text = ""
            selectedbutton.setBackgroundResource(R.color.colorPrimaryDark)

        }

    }
    protected fun buRequestActivity(view:View){

        var userEmail = etEmail.text.toString()
        myRef.child("Users").child(userEmail)
            .child("Request").push().setValue(myEmail)

    }

    protected fun buAcceptActivity(view:View){
        var userEmail = etEmail.text.toString()




    }
}