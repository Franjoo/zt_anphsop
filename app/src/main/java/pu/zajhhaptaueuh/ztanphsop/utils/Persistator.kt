package pu.zajhhaptaueuh.ztanphsop.utils

import android.content.Context
import pu.zajhhaptaueuh.ztanphsop.models.BikeData

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class Persistator {

    private val PREFS_FILENAME = "de.zajhhaptaueuh.prefs"

    fun restoreDataHolder(){

    }

//    fun saveBikeData(context: Context, bikeData: BikeData): Boolean {
//        val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)
//        return prefs.edit()
//                .putLong("bike_id", bikeData.id)
//                .putString("bike_name", bikeData.name)
//                .putString("bike_name", bikeData.type)
//                .putString("bike_manufacturer", bikeData.manufacturer)
//                .putInt("bike_primary_color", bikeData.primary_color)
//                .putInt("bike_secondary_color", bikeData.secondary_color)
//                .putBoolean("bike_registered", bikeData.registered)
//                .putInt("bike_size", bikeData.size)
//                .commit()
//    }
//
//    fun restoreBikeData(context: Context): BikeData {
//        val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)
//        return BikeData(prefs.getLong("bike_id", 0),
//                prefs.getString("bike_name", ""),
//                prefs.getString("bike_name", ""),
//                prefs.getString("bike_manufacturer", ""),
//                prefs.getInt("bike_primary_color", 0),
//                prefs.getInt("bike_secondary_color", 0),
//                prefs.getBoolean("bike_registered", false),
//                prefs.getInt("bike_size", 0))


//        return BikeData(with(prefs){
//            getLong("bike_id", 0)
//            getString("bike_name", "")
//            getString("bike_name", "")
//            getString("bike_manufacturer", "")
//            getInt("bike_primary_color", 0)
//            getInt("bike_secondary_color", 0)
//            getBoolean("bike_registered", false)
//            getInt("bike_size", 0))
//        })
//    }

}