package pu.zajhhaptaueuh.ztanphsop.utils

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable

/* Copyright (C) million hunters GmbH - All Rights Reserved
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
    }

}
