package pu.zajhhaptaueuh.ztanphsop

/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

class Constants {

    object Bundles {
        const val BIKE_ID = "fj.bundle.bike.id"
        const val SAVED_CHANGES = "fj.bundle.saved_changes"

    }

    object Prefs {
        const val PREFS_FILENAME = "de.zajhhaptaueuh.prefs"
    }

    object Results{
        const val RESULT_SAVED_CHANGES = 2001
    }

    companion object {

        val Undefined = -333

        val DUMMY_BIKE_ID = "BikeId_abc123"

        // @Deprecated
        val Matcher_No_Special_Characters = Regex("[a-zA-Z0-9]+")
        val Matcher_Less_Than_4 = Regex("^.{1,3}")
        val Matcher_More_Than_12 = Regex("^.{11,}$")


    }


}


