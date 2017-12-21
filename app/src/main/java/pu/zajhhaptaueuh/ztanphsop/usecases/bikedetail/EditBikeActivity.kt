package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import pu.zajhhaptaueuh.ztanphsop.Bikes
import pu.zajhhaptaueuh.ztanphsop.Constants
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.Validator
import pu.zajhhaptaueuh.ztanphsop.dialogs.DialogProvider
import pu.zajhhaptaueuh.ztanphsop.models.BikeData
import pu.zajhhaptaueuh.ztanphsop.navigation.Navigator
import pu.zajhhaptaueuh.ztanphsop.usecases.BaseActivity
import pu.zajhhaptaueuh.ztanphsop.utils.Utils


/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class EditBikeActivity : BaseActivity() {

    //    public val tag = this::class.simpleName as String
    val tag = EditBikeActivity@ this.javaClass.simpleName

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

    private var isDirty: Boolean = false
        private set(value) {
            field = value

            // 'reactive' behaviour
            btn_save.isEnabled = value
            btn_save.invalidate()
        }

    private  var validator: Validator = Validator(this)
    private lateinit var bikeId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setContentView(R.layout.bike_edit_bike)

        // restore saved bike data
        savedInstanceState?.let {
            bikeId = it.getString(Constants.BUNDLE_BIKE_ID)
            restoreValues(bikeId)
        }

        isDirty = false
//        validator = Validator(this)

        setupActionBar(null)
        setupClickListener()
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
        text_name.addTextChangedListener(object : TextWatcher {
            val errorHolderLayout = findViewById<TextInputLayout>(R.id.input_layout_bike_name)
            private var job: Job? = null
            override fun afterTextChanged(e: Editable?) {
                job = launch(UI) {
                    delay(700)
                    val text = e.toString()
                    val error = validator.checkBikeNameValid(text)
                    errorHolderLayout.error = error
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isDirty = true
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                job?.cancel()
            }
        })
    }

    override fun onResume() {
        super.onResume()

//        val error = validator.checkBikeNameValid(text_name.text.toString())
//        error?.let { findViewById<TextInputLayout>(R.id.input_layout_bike_name).error = it }

        setupTextChangedListener()
    }

    private fun restoreValues(bikeId: String) {
        val bikeData = Bikes[bikeId]!!
        restoreValues(bikeData)
    }

    private fun restoreValues(data: BikeData) {
        text_name.setText(data.name)
        spinner_type.setText(data.type)
        text_manufacturer.setText(data.manufacturer)
        checkbox_registered.isChecked = data.registered
        text_size.setText(data.size)

        Utils.snackDelayed(this, getString(R.string.restore_changes_text))
    }

    private fun setupClickListener() {
        btn_save.setOnClickListener { saveChanges() }
    }

    private fun saveChanges() {
        println("start progress bar")
        Utils.startProgressBarOptimistic(progressbar)

        val bikeData = BikeData(
                id = "axj6shsad",
                name = text_name.text.toString(),
                type = spinner_type.text.toString(),
                manufacturer = text_manufacturer.text.toString(),
                primary_color = "keks",
                secondary_color = "bla",
                registered = checkbox_registered.isChecked,
                size = "27,5"
        )



        Bikes.put(bikeData.id, bikeData)
        Utils.snackDelayed(this, getString(R.string.save_changes_title))
//        Persistator().saveBikeData(this, bikeData)
    }

    private fun leave() {
        if (isDirty) {

            // hide keyboard before showing dialog
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(text_name.windowToken, 0)


            val dialog = DialogProvider.createSaveChangesDialog(this, {
                launch(UI) {
                    saveChanges()
                    Navigator.gotoBikeDetailActivity(this@EditBikeActivity, true)
                }
            }, null)
            dialog.show()
        } else {
            Navigator.gotoBikeDetailActivity(this)
        }
    }


    /**
     * EditText Extender (usage like inner class)
     */
//    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
//        this.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun afterTextChanged(editable: Editable?) {
//                afterTextChanged.invoke(editable.toString())
//            }
//        })
//    }


}
