package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.usecases.BaseActivity


/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class EditBikeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.bike_edit_bike)
        setupActionBar(null)
//        setupPickerValues()
        setupSpinnerEntries()
    }

    private fun setupSpinnerEntries() {
        val spinner = findViewById<Spinner>(R.id.input_picker_primary_color2)!!
        val mArrayBindStages: MutableList<String> = resources.getStringArray(R.array.choices_bike_color).toMutableList()

        val spinnerArrayAdapter = object : ArrayAdapter<String>(this, R.layout.spinner_item, mArrayBindStages) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

//                val c = layoutInflater.inflate(R.layout.spinner_item, null)
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinner.adapter = spinnerArrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItemText = parent.getItemAtPosition(position) as String
                if (position > 0) {
                    // Notify the selected item text
                    Toast.makeText(applicationContext, "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

    private fun setupPickerValues() {
//        val pickerColorPrimary = findViewById<NumberPicker>(R.id.input_picker_primary_color)
//        val pickerColorSecondary = findViewById<NumberPicker>(R.id.input_picker_secondary_color)
//
//        val values = arrayOf("Red", "Green", "Blue", "Yellow", "Magenta")
//
//        pickerColorPrimary.displayedValues = values
//        pickerColorSecondary.displayedValues = values
//
//        pickerColorPrimary.minValue = 0
//        pickerColorSecondary.minValue = 0
//
//        pickerColorPrimary.maxValue = values.size - 1
//        pickerColorSecondary.minValue = values.size -1
//
//        pickerColorPrimary.wrapSelectorWheel = true
//        pickerColorSecondary.wrapSelectorWheel = true

    }

}