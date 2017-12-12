package pu.zajhhaptaueuh.ztanphsop.models

import java.util.*

/**
 * This file contains data which should be persisted
 */

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
data class BikeData(

        val id: Long,
        val name: String,
        val type: String,
        val manufacturer: String,
        val primary_color: Int,
        val secondary_color: Int,
        val registered: Boolean,
        val size: Int

)

data class User(

        val first_name:String,
        val sur_name:String,
        val email:String
//        val bikes:Array<BikeData>

)