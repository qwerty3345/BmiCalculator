package com.example.bmicalculator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        resultButton.setOnClickListener {
            saveData(
                heightEditText.text.toString().toInt(),
                weightEditText.text.toString().toInt()
            )
            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }

    private fun saveData(height: Int, weight: Int) {
        val sharedPref =
            getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("KEY_WEIGHT", weight)
            .putInt("KEY_HEIGHT", height)
            .apply()
    }

    private fun loadData() {
        val sharedPref =
            getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val height = sharedPref.getInt("KEY_HEIGHT", 0)
        val weight = sharedPref.getInt("KEY_WEIGHT", 0)

        if (height != 0 && weight != 0) {
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}