package pu.zajhhaptaueuh.ztanphsop.utils

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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
//class WatchValidator private constructor(
//        private val view: View,
//        private val errorHolderLayout: TextInputLayout,
//        private val validator: (text: String) -> String?,
//        private val callerActivity: Activity?) {

class WatchValidator private constructor() {

    private var wasTouchedObserver: WasTouchedObserver? = null
    private var enabled : Boolean = false

    companion object {
        fun attach(editText: EditText, formValidator: FormValidator): WatchValidator {
            val instance = WatchValidator()
            val validator = Validator(Utils.getActivity(editText)!!)
            val watcher = object : TextWatcher {
                private var job: Job? = null
                override fun afterTextChanged(e: Editable?) {
                    if(!instance.enabled) return

                    job = launch(UI) {
                        delay(700)
                        val text = e.toString()
                        val errorText = validator.checkFormValid(text, formValidator)
                        val errorHolderLayout = editText.parentForAccessibility as TextInputLayout
                        errorHolderLayout.error = errorText
                    }
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(!instance.enabled) return

                    instance.wasTouchedObserver?.wasTouched(editText)
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(!instance.enabled) return

                    job?.cancel()
                }

            }

            editText.addTextChangedListener(watcher)

            return instance
        }
    }


    fun setOnWasTouchedObserver(wasTouchedObserver: WasTouchedObserver) {
        this.wasTouchedObserver = wasTouchedObserver
    }

    fun enable():WatchValidator{
        enabled = true
        return this
    }

    fun disable():WatchValidator{
        enabled = false
        return this
    }

}

interface WasTouchedObserver {
    fun wasTouched(view: View)
}