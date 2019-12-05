package com.example.colorquiz2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var scoreVal: TextView
    lateinit var btnLeft: Button
    lateinit var btnRight: Button
    lateinit var colorName: TextView
    lateinit var btnRetry: Button

    var score: Int =  0
    var correctBtnNum: Int = 0
    lateinit var colorNameArray: ArrayList<String>
    lateinit var colorCodeArray: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        score =  0
        colorNameArray = arrayListOf<String>("BLUE", "GREEN", "YELLOW", "CYAN","MAGENTA", "RED", "DARKGRAY", "GRAY", "WHITE", "BLACK")
        colorCodeArray = arrayListOf<Int>(Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.RED, Color.DKGRAY, Color.GRAY, Color.WHITE, Color.BLACK)
        btnRetry = findViewById(R.id.retryBtn)
        btnRetry.setVisibility(View.INVISIBLE)
        showQuestion()
        showScore()
    }

    fun initialize(view: View){
        init()
    }

    fun showQuestion(){

        correctBtnNum = Random().nextInt(2)
        val correctColorIndex = Random().nextInt(colorNameArray.size)

        colorName = findViewById(R.id.colorName)
        colorName.text = colorNameArray.get(correctColorIndex)

        btnLeft = findViewById(R.id.btnLeft)
        btnRight = findViewById(R.id.btnRight)

        if(correctBtnNum == 0){
            btnLeft.setBackgroundColor(colorCodeArray.get(correctColorIndex));
        }else{
            btnRight.setBackgroundColor(colorCodeArray.get(correctColorIndex));
        }

        colorNameArray.removeAt(correctColorIndex)
        colorCodeArray.removeAt(correctColorIndex)

        val wrongColorIndex = Random().nextInt(colorNameArray.size )

        if(correctBtnNum == 0){
            btnRight.setBackgroundColor(colorCodeArray.get(wrongColorIndex));
        }else{
            btnLeft.setBackgroundColor(colorCodeArray.get(wrongColorIndex));
        }

        colorNameArray.removeAt(wrongColorIndex)
        colorCodeArray.removeAt(wrongColorIndex)

    }

    fun answerColor(view: View){
        if(view.id == R.id.btnLeft && correctBtnNum == 0 || view.id == R.id.btnRight && correctBtnNum == 1){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT ).show()
            score++
        }else{
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT ).show()
            score--
        }

        if(colorNameArray.size == 0){
            Toast.makeText(this, "Your score is $score", Toast.LENGTH_LONG ).show()
            btnRetry = findViewById(R.id.retryBtn)
            btnRetry.setVisibility(View.VISIBLE)
        }else {
            showQuestion()
        }

        showScore()
    }

    fun showScore(){
        scoreVal = findViewById(R.id.scoreVal)
        scoreVal.text = score.toString()
    }
}
