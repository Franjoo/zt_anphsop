package pu.zajhhaptaueuh.ztanphsop.utils

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.View
import android.view.Window
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Spinner
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch


/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

@Suppress("unused", "MemberVisibilityCanPrivate")
class Utils {

    companion object {

        fun snack(view: View, text: String) {
            Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
        }

        fun snack(activity: Activity, text: String) {
            snack(activity.findViewById<View>(android.R.id.content), text)
        }

        fun snackDelayed(activity: Activity, text: String) {
            launch(UI) {
                delay(500)
                snack(activity, text)
            }
        }

        fun snackLongDelayed(activity: Activity, text: String) {
            launch(UI) {
                delay(750)
                snack(activity, text)
            }
        }

        fun tintDrawable(drawable: Drawable, color: Int): Drawable {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            return drawable
        }

        fun tintMenu(menu: Menu, color: Int) {
            for (i in 0 until menu.size()) {
                menu.getItem(i).icon?.let {
                    Utils.tintDrawable(it, color)
                }
            }
        }

        fun startProgressBarOptimistic(progressBar: ProgressBar) {
            launch(UI) {
                progressBar.visibility = View.VISIBLE

                val animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
                animation.duration = 380
                animation.interpolator = AccelerateInterpolator()
                animation.start()

                delay(420)
                progressBar.visibility = View.GONE
            }
        }

        fun getActivity(view: View): Activity? {
            var context = view.context
            while (context is ContextWrapper) {
                if (context is Activity) {
                    return context
                }
                context = (context).baseContext
            }
            return null
        }

        fun hideKeyboard(context: Context, view: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun clearFocus(window: Window) {
            window.decorView.clearFocus()
        }

        fun getItemIndexWithValue(spinner: Spinner, value: String): Int? {
            return (0 until spinner.count).firstOrNull { spinner.getItemAtPosition(it).toString().equals(value, true) }
        }

    }


}
