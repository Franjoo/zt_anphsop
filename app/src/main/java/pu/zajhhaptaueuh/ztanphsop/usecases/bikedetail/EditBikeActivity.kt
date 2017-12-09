package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.os.Bundle
import android.widget.NumberPicker
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