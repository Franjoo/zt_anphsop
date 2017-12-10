package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
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
        setupClickListener()
    }

    private fun setupClickListener() {
        val saveButton = findViewById<Button>(R.id.btn_save_changes)
        saveButton.setOnClickListener { saveChanges() }
    }

    private fun saveChanges() {
        val progressbar = findViewById<ProgressBar>(R.id.toolbar_progress_bar)

    }


}