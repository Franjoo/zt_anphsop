package pu.zajhhaptaueuh.ztanphsop.network

import kotlinx.coroutines.experimental.delay
import pu.zajhhaptaueuh.ztanphsop.Constants
import pu.zajhhaptaueuh.ztanphsop.models.BikeData

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class ApiClient {

    /**
     * simulates a server request
     */
    suspend fun requestBikeData(): BikeData {
        delay(1_000)

        return BikeData(
                Constants.DUMMY_BIKE_ID,
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