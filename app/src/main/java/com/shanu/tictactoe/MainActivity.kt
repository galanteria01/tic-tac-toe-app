package com.shanu.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View) {
        var selectedButton = view as Button
        Log.d("buclick:", selectedButton.id.toString())
        var cellId = 0
        when(selectedButton.id){
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




    }
    var activePlayer = 1
    var playerOne = ArrayList<Int>()
    var playerTwo = ArrayList<Int>()
    fun playGame(cellId:Int, selectedbutton:Button){

        if(activePlayer == 1){
            selectedbutton.text = "X"
            selectedbutton.setBackgroundResource(R.color.cyan)
            playerOne.add(cellId)



        }else{
            selectedbutton.text = "O"
            selectedbutton.setBackgroundResource(R.color.white)
            playerTwo.add(cellId)

        }

    }
}