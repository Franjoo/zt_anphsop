package pu.zajhhaptaueuh.ztanphsop.dialogs

import android.content.Context
import android.support.v7.app.AlertDialog
import pu.zajhhaptaueuh.ztanphsop.R

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class DialogProvider {


    companion object {

        private val tag = this::class.simpleName as String

        private fun createDialog(context: Context,
                                 title: String, message: String,
                                 textPositive: String, textNegative: String,
                                 onYes: () -> Unit, onNo: () -> Unit): AlertDialog {

            val builder = AlertDialog.Builder(context)
            with(builder) {
                setTitle(title)
                setMessage(message)
                setPositiveButton(textPositive) { dialog, id -> onYes() }
                setNegativeButton(textNegative) { dialog, id -> onNo() }
            }
            return builder.create()
        }


        fun createDeleteBikeDialog(context: Context, onYes: () -> Unit, onNo: () -> Unit) {
            createDialog(context,
                    context.getString(R.string.delete_bike),
                    context.getString(R.string.really_delete_bike),
                    context.getString(R.string.delete),
                    context.getString(R.string.cancel),
                    onYes, onNo)
        }

        fun createSaveChangesDialog(context: Context, onYes: () -> Unit, onNo: () -> Unit): AlertDialog {
            return createDialog(context,
                    context.getString(R.string.save_changes_title),
                    context.getString(R.string.save_changes_text),
                    context.getString(R.string.yes),
                    context.getString(R.string.cancel),
                    onYes, onNo)
        }


    }
}