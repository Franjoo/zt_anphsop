package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import pu.zajhhaptaueuh.ztanphsop.Constants
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.Validator
import pu.zajhhaptaueuh.ztanphsop.models.BikeData
import pu.zajhhaptaueuh.ztanphsop.usecases.BaseActivity
import pu.zajhhaptaueuh.ztanphsop.utils.Persistator
import android.view.MenuItem
import pu.zajhhaptaueuh.ztanphsop.dialogs.DialogProvider
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class EditBikeActivity : BaseActivity() {

    private val tag = this::class.simpleName as String

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
    private lateinit var validator: Validator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bike_edit_bike)

        validator = Validator(this)

        setupActionBar(null)
        setupClickListener()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (isDirty) {
            val dialog = DialogProvider.createSaveChangesDialog(this,
                    {
                        // on yes
                        launch(UI) {
                            saveChanges()
                            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(text_name.windowToken, 0)
                            delay(100)
                            finish()
                        }

                    },
                    {
                        // on no
                    })
            dialog.show()

            return true
        }

        return super.onOptionsItemSelected(item)


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

//        val exceptionHandler: CoroutineContext = CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }
//        async(UI + exceptionHandler) {
//            val b: Deferred<BikeData> = async(CommonPool) { ApiClient().getBikeById(0) }
//            restoreValues(b.await())
//        }

//        val error = validator.checkBikeNameValid(text_name.text.toString())
//        error?.let { findViewById<TextInputLayout>(R.id.input_layout_bike_name).error = it }

        setupTextChangedListener()
    }

    private fun restoreValues(data: BikeData) {
//        text_name.setText(data.name)
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