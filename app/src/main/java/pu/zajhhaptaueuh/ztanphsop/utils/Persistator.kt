package pu.zajhhaptaueuh.ztanphsop.utils

import android.content.Context
import pu.zajhhaptaueuh.ztanphsop.Constants
import pu.zajhhaptaueuh.ztanphsop.models.BikeData

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class Persistator {

    fun saveBikeData(context: Context, bikeData: BikeData): Boolean {
        val prefs = context.getSharedPreferences(Constants.Prefs.PREFS_FILENAME, 0)
        return prefs.edit()
                .putString("bike_id", bikeData.id)
                .putString("bike_name", bikeData.name)
                .putString("bike_name", bikeData.type)
                .putString("bike_manufacturer", bikeData.manufacturer)
                .putString("bike_primary_color", bikeData.primary_color)
                .putString("bike_secondary_color", bikeData.secondary_color)
                .putBoolean("bike_registered", bikeData.registered)
                .putString("bike_size", bikeData.size)
                .commit()
    }

    fun restoreBikeData(context: Context): BikeData {
        val prefs = context.getSharedPreferences(Constants.Prefs.PREFS_FILENAME, 0)
        return BikeData(
                prefs.getString("bike_id", ""),
                prefs.getString("bike_name", ""),
                prefs.getString("bike_name", ""),
                prefs.getString("bike_manufacturer", ""),
                prefs.getString("bike_primary_color", ""),
                prefs.getString("bike_secondary_color", ""),
                prefs.getBoolean("bike_registered", false),
                prefs.getString("bike_size", ""))


//        return BikeData(with(prefs){
//            getString("bike_id", 0)
//            getString("bike_name", "")
//            getString("bike_name", "")
//            getString("bike_manufacturer", "")
//            getString("bike_primary_color", "")
//            getString("bike_secondary_color", "")
//            getBoolean("bike_registered", false)
//            getString("bike_size", "")
//        })
    }

}