package pu.zajhhaptaueuh.ztanphsop.utils

import android.animation.ObjectAnimator
import android.app.Activity
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.design.widget.Snackbar
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch


/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

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

        fun tintDrawable(drawable: Drawable, color: Int): Drawable {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            return drawable
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


    }


}
