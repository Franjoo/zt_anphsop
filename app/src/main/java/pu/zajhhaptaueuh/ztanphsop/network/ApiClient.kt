package pu.zajhhaptaueuh.ztanphsop.network

import kotlinx.coroutines.experimental.delay
import pu.zajhhaptaueuh.ztanphsop.models.BikeData

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class ApiClient {

    /**
     * simulates a
     */
    suspend fun requestBikeData(): BikeData {
        delay(1_000)

        val bikeIdFromUser = "dummyBikeId"
        return BikeData(
                bikeIdFromUser,
                "dummy",
                "type",
                "m",
                "222222",
                "444444",
                false,
                "28"
        )
    }

}