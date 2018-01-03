package pu.zajhhaptaueuh.ztanphsop

import android.content.Context
import pu.zajhhaptaueuh.ztanphsop.utils.Matcher

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

class Error(val matcher: Matcher, val isNegated: Boolean, val errorStringResource: Int?)

typealias C = Constants; typealias M = Matcher
enum class FormValidator(var list: List<Error>) {

    BikeName(listOf(
            Error(M.Empty, false, null),
            Error(M.NoSpecialCharacters, true, R.string.err_special_characters),
            Error(M.LessThan4, false, R.string.err_too_short),
            Error(M.MoreThan12, false, R.string.err_too_long))),

    BikeManufacturer(listOf(
            Error(M.LessThan4, false, R.string.err_too_short),
            Error(M.MoreThan12, false, R.string.err_too_long))),

    BikeSize(listOf(
            Error(M.NumberLessThan4, false, R.string.err_too_small),
            Error(M.NumberGreaterThan99, false, R.string.err_too_big)))

}

@Suppress("unused")
class Validator(private val context: Context) {
    /**
     * maps regex to error resource strings
     */
    private val map: Map<Regex, String> = mapOf(
            C.Matcher_More_Than_12 to s(R.string.err_too_long),
            C.Matcher_Less_Than_4 to s(R.string.err_too_short),
            C.Matcher_No_Special_Characters to s(R.string.err_special_characters)
    )

    /* ####### form validation functions ####### */

    fun checkBikeNameValid(name: String?): String? {
        if (name == null) return "name is null"

        return checkMultiValid(name,
                Pair(C.Matcher_No_Special_Characters, true),
                Pair(C.Matcher_Less_Than_4, false),
                Pair(C.Matcher_More_Than_12, false)
        )
    }

    fun checkFormValid(text: String, formValidator: FormValidator): String? {
        val errors = formValidator.list
        errors.forEach({

            // workaround - stop validation if empty text is allowed
            if (it.matcher === M.Empty && !it.isNegated && text.isEmpty()) return null

            checkValid(text, it.matcher.regex, it.isNegated, it.errorStringResource?.let { s(it) })?.let {
                return it
            }
        })

        return null
    }

    fun checkSelectionValid(text: String, validator: Any) {
        TODO()
    }


    /* ####### utility functions ####### */

    /**
     * checks regex
     * @return an error matches otherwise null
     */
    private fun checkValid(s: String, regex: Regex): String? {
        if (regex.matches(s)) {
            return map[regex]!!
        }
        return null
    }

    /**
     * checks regex with negation option
     * @return an error if matches otherwise null
     */
    private fun checkValid(s: String, regex: Regex, isNegated: Boolean, errorString: String?): String? {
        if (!regex.matches(s) && isNegated || regex.matches(s) && !isNegated) {
            return errorString
        }
        return null
    }

    /**
     * checks regex with negation option
     * @return an error if matches otherwise null
     */
    private fun checkValid(s: String, regex: Regex, isNegated: Boolean): String? {
        if (!regex.matches(s) && isNegated || regex.matches(s) && !isNegated) {
            return map[regex]!!
        }
        return null
    }

    /**
     * checks multiple regex
     * @return an error if matches otherwise null
     */
    private fun checkMultiValid(s: String, vararg regex: Regex): String? {
        regex.forEach {
            if (it.matches(s)) {
                return map[it]!!
            }
        }

        return null
    }

    /**
     * checks multiple regex with negation option
     */
    private fun checkMultiValid(s: String, vararg regexList: Pair<Regex, Boolean>): String? {
        regexList.forEach {
            checkValid(s, it.first, it.second)?.let {
                return it
            }
        }
        return null
    }

    /**
     * convenience method to resolve a string resource
     */
    private fun s(resId: Int): String {
        return context.getString(resId)
    }

}
