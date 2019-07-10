package com.julianm.colorpickerlibrary.palette

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devs.vectorchildfinder.VectorChildFinder
import com.devs.vectorchildfinder.VectorDrawableCompat
import com.julianm.colorpickerlibrary.R
import kotlinx.android.synthetic.main.item_patelle_child.view.*

class PaletteAdapterChild(
    var context: Context,
    var listPalette: MutableList<ItemPalette>,
    val callback: Callback
) : RecyclerView.Adapter<PaletteAdapterChild.ViewHolder>() {

    class ViewHolder(view : View):RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PaletteAdapterChild.ViewHolder {

        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_patelle_child,p0,false))

    }


    var lastPalettePositionSelected = -1

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        val vector: VectorChildFinder?
        var backgroundColorPath : VectorDrawableCompat.VFullPath? = null


        if (listPalette[p1].isVector){
            vector = VectorChildFinder(context, listPalette[p1].imageDrawable,p0.itemView.ivPaletteChild)
            backgroundColorPath = vector.findPathByName("background")


            if (listPalette[p1].selected){
                lastPalettePositionSelected = p1
                backgroundColorPath.fillColor = Color.LTGRAY
            }else{
                backgroundColorPath.fillColor = Color.WHITE
            }


            p0.itemView.ivPaletteChild.invalidate()
        }else{
            p0.itemView.ivPaletteChild.setImageResource(listPalette[p1].imageDrawable)
        }




        p0.itemView.setOnClickListener {
            listPalette[p1].selected = true
            if (lastPalettePositionSelected != -1 && lastPalettePositionSelected != p1){
                listPalette[lastPalettePositionSelected].selected = false
            }

            notifyItemChanged(lastPalettePositionSelected)
            lastPalettePositionSelected = p1
            if (listPalette[p1].selected){
                backgroundColorPath?.fillColor = Color.LTGRAY
            }else{
                backgroundColorPath?.fillColor = Color.WHITE
            }
            p0.itemView.ivPaletteChild.invalidate()


            notifyItemChanged(lastPalettePositionSelected)


            callback.onPaletteSelected(p1)
        }

    }

    override fun getItemCount(): Int = listPalette.size


    public interface Callback{
        fun onPaletteSelected(childPosition:Int)
    }
}
