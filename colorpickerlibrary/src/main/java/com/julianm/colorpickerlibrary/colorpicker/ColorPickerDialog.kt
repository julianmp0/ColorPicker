package com.julianm.colorpickerlibrary.colorpicker

import android.content.Context
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v4.app.DialogFragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.julianm.colorpickerlibrary.R
import kotlinx.android.synthetic.main.dialog_color_picker.*


class ColorPickerDialog : DialogFragment() {
    companion object {
        private val KEY_SHOW_DEFAULT_COLORS = "show_default_colors"
        private var mListener: OnColorSelectedListener? = null
        private var colorsList = mutableListOf<ColorModel>()
    }


    class Builder(context: Context) {
        private var mListener: OnColorSelectedListener? = null
        private var colorsList = mutableListOf<ColorModel>()
        private var mArgs: Bundle? = null
        init {
            mArgs = Bundle()
        }

        fun setShowDefaultColors(defaultColors : Boolean): Builder {
            mArgs!!.putBoolean(KEY_SHOW_DEFAULT_COLORS, defaultColors)
            return this
        }

        fun setColors(colorsArrayRes: Array<String>): Builder {
            colorsArrayRes.forEach {
                colorsList.add(ColorModel(it, false))
            }
            return this
        }

        fun setOnColorSelectedListener(listener: OnColorSelectedListener): Builder {
            mListener = listener
            return this
        }

        fun build(): ColorPickerDialog {
            val dialog = ColorPickerDialog()
            dialog.arguments = mArgs
            dialog.setOnColorSelectedListener(mListener!!)
            dialog.setColorsList(colorsList)
            return dialog
        }
    }


    fun setOnColorSelectedListener(listener: OnColorSelectedListener) {
        mListener = listener
    }

    fun setColorsList(colorsArrayRes: MutableList<ColorModel>) {
        colorsList = colorsArrayRes
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_color_picker, container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        var colorSelected :Int?= null
        okButton.setOnClickListener {
            dismiss()
            mListener!!.onColorSelected(colorSelected)
        }
        cancelButton.setOnClickListener {
            dismiss()
            mListener!!.onColorSelected(null)
        }
        val adapter = ColorsAdapter(
            activity!!,
            colorsList,
            object : ColorsAdapter.Callback {
                override fun onColorSelected(color: Int) {
                    colorSelected = color
                }
            })
        rvColors.layoutManager = GridLayoutManager(activity,5)
        rvColors.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null && args.containsKey(KEY_SHOW_DEFAULT_COLORS)) {
            if (args.getBoolean(KEY_SHOW_DEFAULT_COLORS)){
                colorsList = loadDefaultColors()
            }
        }
    }

    private fun loadDefaultColors(): MutableList<ColorModel> {
        val colorsList = mutableListOf<ColorModel>()
        colorsList.add(ColorModel("#ffffff"))
        colorsList.add(ColorModel("#FF0000"))
        colorsList.add(ColorModel("#00FF00"))
        colorsList.add(ColorModel("#0000FF"))
        colorsList.add(ColorModel("#00FFFF"))
        colorsList.add(ColorModel("#FF00FF"))
        colorsList.add(ColorModel("#FFFF00"))
        colorsList.add(ColorModel("#000000"))
        colorsList.add(ColorModel("#808080"))
        colorsList.add(ColorModel("#FF8080"))
        colorsList.add(ColorModel("#800080"))
        colorsList.add(ColorModel("#80FFFF"))
        colorsList.add(ColorModel("#800000"))
        colorsList.add(ColorModel("#FF8000"))
        colorsList.add(ColorModel("#8000FF"))
        colorsList.add(ColorModel("#00FF80"))
        colorsList.add(ColorModel("#BBBB20"))
        return colorsList
    }

    interface OnColorSelectedListener {
        fun onColorSelected(@ColorInt color: Int? = null)
    }
}