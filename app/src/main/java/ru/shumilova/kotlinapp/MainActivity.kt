package ru.shumilova.kotlinapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b_button.setOnClickListener {
            Toast.makeText(this, "Press the button!", Toast.LENGTH_LONG).show()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getViewState().observe(this, Observer { value ->
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
        })
    }
}