package pu.zajhhaptaueuh.ztanphsop.navigation

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.util.Log
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.usecases.NotImplementedActivity
import pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail.ChaseMapActivity
import pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail.ChatsActivity
import pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail.EditBikeActivity

/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

/**
 * The Navigator provides methods for setting up intent extras and starting Activity transitions.
 * It is also responsible for handling the Activity Backstack.
 */
class Navigator {

    companion object {

        private val TAG = Navigator::class.simpleName
        private val OP_ACTIVITY = "Start Activity: "

        fun gotoEditBikeActivity(context: Context) {
            val intent = Intent(context, EditBikeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)

            Log.i(TAG, OP_ACTIVITY + EditBikeActivity::class.simpleName)
        }

        fun gotoSightingsActivity(context: Context) {
            val intent = Intent(context, ChaseMapActivity::class.java)
            context.startActivity(intent)

            Log.i(TAG, OP_ACTIVITY + ChaseMapActivity::class.simpleName)
        }


        fun gotoChatsActivity(context: Context) {
            val intent = Intent(context, ChatsActivity::class.java)
            context.startActivity(intent)

            Log.i(TAG, OP_ACTIVITY + ChatsActivity::class.simpleName)
        }


        fun gotoNotImplementedActivity(context: Context, identifier: String) {
            val intent = Intent(context, NotImplementedActivity::class.java)
            intent.putExtra(NotImplementedActivity.EXTRA_IDENTIFIER, identifier)
            context.startActivity(intent)

            Log.i(TAG, OP_ACTIVITY + NotImplementedActivity::class.simpleName + "/  $identifier")
        }

    }
}
