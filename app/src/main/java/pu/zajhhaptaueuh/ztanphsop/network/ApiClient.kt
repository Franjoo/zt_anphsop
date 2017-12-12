package pu.zajhhaptaueuh.ztanphsop.network

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import pu.zajhhaptaueuh.ztanphsop.models.BikeData

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class ApiClient {



    suspend fun getBikeById(userId: Int): BikeData {
        delay(1_000)

        val bike = BikeData(
                0,
                "dummy",
                "type",
                "m",
                222222,
                444444,
                false,
                28
        )



        println("will return $bike")

        return bike

    }

}