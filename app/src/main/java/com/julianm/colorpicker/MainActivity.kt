package com.julianm.colorpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorInt
import com.julianm.colorpickerlibrary.ColorPickerDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),ColorPickerDialog.OnColorSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ColorPickerDialog.Builder(this)
            .setShowDefaultColors(true)
            //.setColors(resources.getStringArray(R.array.colors))
            .setOnColorSelectedListener(this)
            .build()
            .show(supportFragmentManager,"tag")

    }

    override fun onColorSelected(@ColorInt color: Int?) {

        if(color != null){
            textView.setBackgroundColor(color)
        }

    }

}
