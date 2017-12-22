package pu.zajhhaptaueuh.ztanphsop

/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

class Constants {



    companion object {

        val BUNDLE_BIKE_ID = "fj.bundle.bike.id"

        val EXTRA_SAVED_CHANGES = "fj.extras.saved_changes"
        val RESULT_SAVED_CHANGES = 2001

        val Matcher_No_Special_Characters = Regex("[a-zA-Z0-9]+")
        val Matcher_Less_Than_4 = Regex("^.{1,3}")
        val Matcher_More_Than_12 = Regex("^.{11,}$")

        val Undefined = -333

        val DUMMY_BIKE_ID = "BikeId_abc123"


       public enum class Bundles(val id:String){
            BIKE_ID("fj.bundle.bike.id")
        }

    }

}


