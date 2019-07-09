package com.julianm.colorpickerlibrary.palette

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.julianm.colorpickerlibrary.R
import kotlinx.android.synthetic.main.item_patelle_parent.view.*
import java.text.FieldPosition

class PaletteAdapter(
    val context: Context?,
    val paletteList: MutableList<PaletteModel>,
    val callback: Callback
) :
    RecyclerView.Adapter<PaletteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_patelle_parent,p0,false))

    }

    override fun getItemCount(): Int = paletteList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val paletteParent = paletteList[p1]
        p0.itemView.titlePalette.text = paletteParent.title
        p0.itemView.rvPaletteChild.apply {
            layoutManager = GridLayoutManager(context,4)
            adapter = PaletteAdapterChild(context,paletteParent.listPalette,
                object : PaletteAdapterChild.Callback{
                    override fun onPaletteSelected(childPosition:Int) {

                        callback.onPaletteSelected(p1,childPosition)
                    }

                })
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }

    class ViewHolder(view : View):RecyclerView.ViewHolder(view) {

    }

    public interface Callback{
        fun onPaletteSelected(parentPosition: Int, childPosition:Int)
    }
}
