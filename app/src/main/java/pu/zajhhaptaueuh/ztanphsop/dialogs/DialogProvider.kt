package pu.zajhhaptaueuh.ztanphsop.dialogs

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.Log
import pu.zajhhaptaueuh.ztanphsop.R

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class DialogProvider {


    @Suppress("JAVA_CLASS_ON_COMPANION")
    companion object {

        val tag = DialogProvider@ this.javaClass.simpleName

        private fun createDialog(context: Context,
                                 title: String, message: String,
                                 textPositive: String, textNegative: String,
                                 onYes: (() -> Unit)?, onNo: (() -> Unit)?): AlertDialog {

            val builder = AlertDialog.Builder(context)
            with(builder) {
                setTitle(title)
                setMessage(message)

                onYes?.let {
                    setPositiveButton(textPositive) { dialog, id -> it() }
                }

                onNo?.let {
                    setNegativeButton(textNegative) { dialog, id -> it() }
                }

            }

            Log.i(tag, "create dialog with title: '$title'")

            val dialog = builder.create()
            dialog.window.attributes.windowAnimations = R.style.FJ_Dialog_Animation

            return dialog
        }


        fun createDeleteBikeDialog(context: Context, onYes: () -> Unit, onNo: () -> Unit):AlertDialog {
            return createDialog(context,
                    context.getString(R.string.delete_bike),
                    context.getString(R.string.really_delete_bike),
                    context.getString(R.string.delete),
                    context.getString(R.string.cancel),
                    onYes, onNo)
        }

        fun createSaveChangesDialog(context: Context, onYes: (() -> Unit)?, onNo: (() -> Unit)?): AlertDialog {
            return createDialog(context,
                    context.getString(R.string.save_changes_title),
                    context.getString(R.string.save_changes_text),
                    context.getString(R.string.yes),
                    context.getString(R.string.cancel),
                    onYes, onNo)
        }


    }
}