package com.julianm.colorpicker


import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.julianm.colorpickerlibrary.colorpicker.ColorPickerDialog
import com.julianm.colorpickerlibrary.palette.ItemPalette
import com.julianm.colorpickerlibrary.palette.PaletteModel
import com.julianm.colorpickerlibrary.palette.PaletteDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ColorPickerDialog.OnColorSelectedListener,PaletteDialog.OnPaletteSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        ColorPickerDialog.Builder(this)
            .setShowDefaultColors(true)
            //.setColors(resources.getStringArray(R.array.colors))
            .setOnColorSelectedListener(this)
            .build()
            .show(supportFragmentManager,"tag")
            */

        PaletteDialog.Builder()
            .setListPalette(generateDefaultPalette())
            .setOnPaletteSelectedListener(this)
            .build()
            .show(supportFragmentManager,"tag")

    }

    private fun generateDefaultPalette(): MutableList<PaletteModel> {
        val parentPalette = mutableListOf<PaletteModel>()

        val childPalette1 = mutableListOf<ItemPalette>()
        childPalette1.add(ItemPalette(R.drawable.ic_launcher_background,true))
        childPalette1.add(ItemPalette(R.drawable.ic_launcher_background,false))
        childPalette1.add(ItemPalette(R.drawable.ic_launcher_background,false))
        childPalette1.add(ItemPalette(R.drawable.ic_launcher_background,false))
        parentPalette.add(PaletteModel("Ancho de trazo",childPalette1))
        parentPalette.add(PaletteModel("Tipo de trazo",childPalette1))

        return parentPalette

    }

    override fun onColorSelected(@ColorInt color: Int?) {

        if(color != null){
            textView.setBackgroundColor(color)
        }

    }

    override fun onPaletteSelected(mapSelections: HashMap<Int, Int>) {

        Log.d("onPaletteSelected", mapSelections.toString())

    }

}
