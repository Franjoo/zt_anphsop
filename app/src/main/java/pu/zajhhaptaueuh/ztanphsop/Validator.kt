package pu.zajhhaptaueuh.ztanphsop

import android.content.Context

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class Validator(private val context: Context) {

    fun checkBikeNameValid(name: String?): String? {
        if (name == null) return "name is null"

        with(name) {
            when {
                isBlank() -> return null
                length < 4 -> return "too short"
                length > 30 -> return "too long"
                !matches(Constants.Matcher_No_Special_Characters) -> return context.getString(R.string.err_special_characters)
                else -> {
                } // everything is fine
            }
        }

        return null
    }

}