package ru.shumilova.kotlinapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b_button.setOnClickListener {
            viewModel.clickCounter()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getViewState().observe(this, { value ->
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
        })
    }
}