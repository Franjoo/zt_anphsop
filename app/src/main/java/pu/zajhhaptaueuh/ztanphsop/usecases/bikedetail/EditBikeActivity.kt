package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import pu.zajhhaptaueuh.ztanphsop.Constants
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.models.BikeData
import pu.zajhhaptaueuh.ztanphsop.network.ApiClient
import pu.zajhhaptaueuh.ztanphsop.usecases.BaseActivity
import pu.zajhhaptaueuh.ztanphsop.utils.Persistator
import pu.zajhhaptaueuh.ztanphsop.utils.Utils
import kotlin.coroutines.experimental.CoroutineContext


/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class EditBikeActivity : BaseActivity() {

    private val root by bind<View>(R.id.root)
    private val progressbar by bind<ProgressBar>(R.id.progressbar)
    private val btn_save by bind<Button>(R.id.btn_save_changes)
    private val text_name by bind<EditText>(R.id.input_bike_name)
    private val spinner_type by bind<EditText>(R.id.input_bike_type)
    private val text_manufacturer by bind<EditText>(R.id.input_bike_manufacturer)
    private val text_primary_color by bind<Spinner>(R.id.input_picker_primary_color)
    private val text_secondary_color by bind<Spinner>(R.id.input_picker_secondary_color)
    private val checkbox_registered by bind<CheckBox>(R.id.input_checkbox_registered)
    private val text_size by bind<EditText>(R.id.input_bike_size)

    private val primary_color: Int = Constants.Undefined
    private val secondary_color: Int = Constants.Undefined
    private val size: Int = Constants.Undefined

    var bike: BikeData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bike_edit_bike)
        setupActionBar(null)
        setupClickListener()
    }

    override fun onResume() {
        super.onResume()

        val exceptionHandler: CoroutineContext = CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }
        async(UI + exceptionHandler) {
            val b : Deferred<BikeData> = async(CommonPool) { ApiClient().getBikeById(0) }
            restoreValues(b.await())
        }

        bike?.let {  }
    }

    private fun restoreValues(data: BikeData) {
        text_name.setText(data.name)
        spinner_type.setText(data.type)
        text_manufacturer.setText(data.manufacturer)
        checkbox_registered.isChecked = data.registered
        text_size.setText(data.size)

        Snackbar.make(root, "Änderungen gespeichert", Snackbar.LENGTH_SHORT).show()

    }

    private fun setupClickListener() {
        btn_save.setOnClickListener { saveChanges() }
    }

    private fun saveChanges() {
        println("start progress bar")
//        Utils.startProgressBar(progressbar)

        val bikeData = BikeData(
                id = -1,
                name = text_name.text.toString(),
                type = spinner_type.text.toString(),
                manufacturer = text_manufacturer.text.toString(),
                primary_color = primary_color,
                secondary_color = secondary_color,
                registered = checkbox_registered.isChecked,
                size = size
        )

        Persistator().saveBikeData(this, bikeData)

        Snackbar.make(root, "Änderungen gespeichert", Snackbar.LENGTH_SHORT).show()
    }


}