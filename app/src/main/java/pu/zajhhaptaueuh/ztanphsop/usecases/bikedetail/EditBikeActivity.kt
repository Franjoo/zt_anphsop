package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import pu.zajhhaptaueuh.ztanphsop.*
import pu.zajhhaptaueuh.ztanphsop.dialogs.DialogProvider
import pu.zajhhaptaueuh.ztanphsop.models.BikeData
import pu.zajhhaptaueuh.ztanphsop.navigation.Navigator
import pu.zajhhaptaueuh.ztanphsop.usecases.BaseActivity
import pu.zajhhaptaueuh.ztanphsop.utils.Utils
import pu.zajhhaptaueuh.ztanphsop.utils.WasTouchedObserver
import pu.zajhhaptaueuh.ztanphsop.utils.WatchValidator


/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

@Suppress("PrivatePropertyName")
class EditBikeActivity : BaseActivity(), WasTouchedObserver {

    //    public val tag = this::class.simpleName as String
    val tag = EditBikeActivity@ this.javaClass.simpleName

    private val root by bind<View>(R.id.root)
    private val progressbar by bind<ProgressBar>(R.id.progressbar)
    private val btn_save by bind<Button>(R.id.btn_save_changes)

    private val text_name by bind<TextInputEditText>(R.id.input_bike_name)
    private val text_type by bind<EditText>(R.id.input_bike_type)
    private val text_manufacturer by bind<TextInputEditText>(R.id.input_bike_manufacturer)
    private val text_size by bind<TextInputEditText>(R.id.input_bike_size)

    private val spinner_primary_color by bind<Spinner>(R.id.input_picker_primary_color)
    private val spinner_secondary_color by bind<Spinner>(R.id.input_picker_secondary_color)

    private val checkbox_registered by bind<CheckBox>(R.id.input_checkbox_registered)

    private val primary_color: Int = Constants.Undefined
    private val secondary_color: Int = Constants.Undefined
    private val size: Int = Constants.Undefined

    override fun wasTouched(view: View) {
        isDirty = true
    }

    private var isDirty: Boolean = false
        private set(value) {
            field = value

            // 'reactive' behaviour
            btn_save.isEnabled = value
            btn_save.invalidate()
        }

    private lateinit var validator: Validator
    private var bikeData: BikeData? = null
    private lateinit var bikeId: String
    private lateinit var watcher: List<WatchValidator>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bike_edit_bike)

        // restore saved bike data
        intent.extras?.let {
            if (it.containsKey(Constants.BUNDLE_BIKE_ID)) {
                bikeId = it.getString(Constants.BUNDLE_BIKE_ID)
                bikeData = Bikes[bikeId]!!
                restoreValues(bikeData!!)
            }
        }

        isDirty = false
        validator = Validator(this)

        setupActionBar()
        setupClickListener()
        setupTextChangedListener()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                leave()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        leave()
    }

    private fun setupTextChangedListener() {

        // hide keyboard when view touched
        val onTouchListener = View.OnTouchListener { view, event ->
            Utils.hideKeyboard(this, root)
            Utils.clearFocus(window)
            return@OnTouchListener false
        }

        // hide keyboard when spinners or checkboxes were touched
        listOf(checkbox_registered, spinner_primary_color, spinner_secondary_color)
                .forEach { it.setOnTouchListener(onTouchListener) }


        // watch and validate form elements
        watcher = listOf(
                WatchValidator.attach(text_name, FormValidator.BikeName),
                WatchValidator.attach(text_manufacturer, FormValidator.BikeManufacturer),
                WatchValidator.attach(text_size, FormValidator.BikeSize)
        )

        // observe value changes
        watcher.forEach {
            it.setOnWasTouchedObserver(this)
            it.enable()
        }
    }

    private fun restoreValues(data: BikeData) {

        text_name.setText(data.name)
        text_type.setText(data.type)
        text_manufacturer.setText(data.manufacturer)
        text_size.setText(data.size)

        Utils.getItemIndexWithValue(spinner_primary_color, data.primary_color)?.let {
            spinner_primary_color.setSelection(it)
        }

        Utils.getItemIndexWithValue(spinner_secondary_color, data.secondary_color)?.let {
            spinner_secondary_color.setSelection(it)
        }

        checkbox_registered.isChecked = data.registered

//        Utils.snackDelayed(this, getString(R.string.restore_changes_text))
    }

    private fun setupClickListener() {
        btn_save.setOnClickListener { saveChanges() }
    }

    private fun saveChanges() {
        Utils.startProgressBarOptimistic(progressbar)

        val bikeData = BikeData(
                id = Constants.DUMMY_BIKE_ID,
                name = text_name.text.toString(),
                type = text_type.text.toString(),
                manufacturer = text_manufacturer.text.toString(),
                primary_color = spinner_primary_color.selectedItem.toString(),
                secondary_color = spinner_secondary_color.selectedItem.toString(),
                registered = checkbox_registered.isChecked,
                size = text_size.text.toString()
        )



        Bikes.put(bikeData.id, bikeData)

//        Persistator().saveBikeData(this, bikeData)
    }

    private fun leave() {
        if (isDirty) {
            Utils.hideKeyboard(this, root)
            val dialog = DialogProvider.createSaveChangesDialog(this, {
                launch(UI) {
                    saveChanges()
                    val bundle = Bundle()
                    bundle.putBoolean(Constants.EXTRA_SAVED_CHANGES, true)
                    Navigator.withBundle(bundle)
                            .gotoBikeDetailActivity(this@EditBikeActivity, true)
                }
            }, null)
            dialog.show()
        } else {
            Navigator.gotoBikeDetailActivity(this)
        }
    }
}
