package com.julianm.colorpickerlibrary

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devs.vectorchildfinder.VectorChildFinder
import kotlinx.android.synthetic.main.item_color.view.*

class ColorsAdapter(
    val context: Context,
    val mColors: MutableList<ColorModel>,val onColorSelectedListener: Callback):
    RecyclerView.Adapter<ColorsAdapter.BaseViewHolder>() {
    override fun getItemCount(): Int {
        return mColors.size
    }

    var lastColorPositionSelected = -1
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        val vector = VectorChildFinder(context,R.drawable.ic_circulo,holder.itemView.itemColor)
        val fillColorPath = vector.findPathByName("color")
        fillColorPath.fillColor = Color.parseColor(mColors[position].color)

        fillColorPath.strokeColor = if ( mColors[position].selected){Color.BLACK}else{Color.parseColor(mColors[position].color)}



        holder.itemView.itemColor.invalidate()
        holder.itemView.setOnClickListener {
            mColors[position].selected = true
            if (lastColorPositionSelected != -1 && lastColorPositionSelected != position){
                mColors[lastColorPositionSelected].selected = false
            }
            lastColorPositionSelected = position
            notifyDataSetChanged()
            fillColorPath.strokeColor = Color.BLACK
            holder.itemView.itemColor.invalidate()
            onColorSelectedListener.onColorSelected(Color.parseColor(mColors[position].color))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_color,parent,false))

    }

    class BaseViewHolder(view: View): RecyclerView.ViewHolder(view)

    interface Callback {
        fun onColorSelected(color : Int)
    }
}
