package com.julianm.colorpickerlibrary.palette

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.julianm.colorpickerlibrary.R
import kotlinx.android.synthetic.main.dialog_color_picker.cancelButton
import kotlinx.android.synthetic.main.dialog_color_picker.okButton
import kotlinx.android.synthetic.main.dialog_palette_picker.*

class PaletteDialog : DialogFragment() {

    private var paletteList = mutableListOf<PaletteModel>()
    private var mListener : OnPaletteSelectedListener?=null

    class Builder(){

        private var paletteList = mutableListOf<PaletteModel>()
        private var mListener : OnPaletteSelectedListener?=null
        private var mArgs: Bundle? = null
        init {
            mArgs = Bundle()
        }

        fun setListPalette(paletteList : MutableList<PaletteModel>):Builder{
            this.paletteList = paletteList
            return this
        }

        fun setOnPaletteSelectedListener(mListener : OnPaletteSelectedListener):Builder{
            this.mListener = mListener
            return this
        }


        fun build(): PaletteDialog {
            val dialog = PaletteDialog()
            dialog.arguments = mArgs
            dialog.setOnPaletteSelectedListener(mListener!!)
            dialog.setListPalette(paletteList)
            return dialog
        }

    }

    fun setListPalette(paletteList : MutableList<PaletteModel>){
        this.paletteList = paletteList
    }

    fun setOnPaletteSelectedListener(mListener : OnPaletteSelectedListener){
        this.mListener = mListener
    }

    interface OnPaletteSelectedListener {
        fun onPaletteSelected(mapSelections: HashMap<Int, Int>)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_palette_picker, container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val map = hashMapOf<Int,Int>()

        okButton.setOnClickListener {
            dismiss()
            mListener!!.onPaletteSelected(map)
        }
        cancelButton.setOnClickListener {
            dismiss()
            //mListener!!.onPaletteSelected(null)
        }
        rvPalette.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = PaletteAdapter(context,paletteList,
                object : PaletteAdapter.Callback{
                    override fun onPaletteSelected(parentPosition: Int, childPosition:Int) {
                        map[parentPosition] = childPosition
                    }
                })
        }
        
    }

}