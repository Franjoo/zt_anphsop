package pu.zajhhaptaueuh.ztanphsop.utils

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable


/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

class Utils {

    companion object {

        fun tintDrawable(drawable: Drawable, color: Int): Drawable {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            return drawable
        }
//
//        fun startProgressBar(p: ProgressBar) {
//
//            doAsync {
//                val timer = Timer("schedule", false)
//                var currentProgress = 0
//                val steps = 10
//                val max = 100
//                timer.scheduleAtFixedRate(0, 300) {
//                    println("hello world!")
//                    uiThread {
//                        currentProgress += steps
//                        p.progress = currentProgress
//
//                        if (currentProgress >= 0) {
//                            p.visibility = View.VISIBLE
//                        } else if (currentProgress >= 110) {
//                            p.visibility = View.GONE
//                            p.progress = 0
//                            currentProgress = 0
//                            this.cancel()
//                        }
//                    }
//
//
//                }
//            }
//        }


    }


}
