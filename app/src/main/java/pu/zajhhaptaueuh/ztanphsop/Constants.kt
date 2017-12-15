package pu.zajhhaptaueuh.ztanphsop

/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

class Constants {

    companion object {

        val Matcher_No_Special_Characters = Regex("[a-zA-Z0-9]+")
        val Matcher_Less_Than_4 = Regex("^.{1,3}")
        val Matcher_More_Than_12 = Regex("^.{11,}$")

        val Undefined = -333
    }

}


