package pu.zajhhaptaueuh.ztanphsop.utils

import android.app.Activity
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.EditText
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import pu.zajhhaptaueuh.ztanphsop.FormValidator
import pu.zajhhaptaueuh.ztanphsop.Validator

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class InputWatcher private constructor(
        private val view: View,
        private val errorHolderLayout: TextInputLayout,
        private val validator: (text: String) -> String?,
        private val callerActivity: Activity?
) : TextWatcher, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {


    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {

    }

    private var isEnabled: Boolean = false

    companion object {
//        fun attach(editText: EditText, errorHolderLayout: TextInputLayout, s: (text: String) -> String?): InputWatcher {
//            val instance = InputWatcher(errorHolderLayout, s, Utils.getActivity(editText))
//            editText.addTextChangedListener(instance)
//            return instance
//        }
//
//        fun attach(editText: EditText, s: (text: String) -> String?): InputWatcher {
//            return attach(editText, editText.parent as TextInputLayout, s)
//        }

//        fun attach(editText: EditText, formValidator: FormValidator): InputWatcher {
//            return attach(editText, editText.parent as TextInputLayout, { Validator().checkFormValid()})
//        }
    }

    private var job: Job? = null
    override fun afterTextChanged(e: Editable?) {
        job = launch(UI) {
            delay(700)
            val text = e.toString()
            val errorText = validator(text)
            errorHolderLayout.error = errorText
        }
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (callerActivity is WasTouchedObserver) { // don't know whether this is good practice
            callerActivity.wasTouched(view)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        job?.cancel()
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun enable() {
        isEnabled = true
    }

    fun disable() {
        isEnabled = false
    }

    interface WasTouchedObserver {
        fun wasTouched(view: View)
    }

}