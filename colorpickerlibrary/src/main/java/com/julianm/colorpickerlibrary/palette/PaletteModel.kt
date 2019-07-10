package com.julianm.colorpickerlibrary.palette

import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.Drawable

class PaletteModel (var title : String , var listPalette : MutableList<ItemPalette>){

}

class ItemPalette (var imageDrawable: Int, var selected : Boolean, var isVector:Boolean = true){

}
