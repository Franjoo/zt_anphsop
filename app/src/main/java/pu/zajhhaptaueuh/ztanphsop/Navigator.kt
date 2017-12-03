package pu.zajhhaptaueuh.ztanphsop

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock

/* Copyright (C) million hunters GmbH - All Rights Reserved
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

        private val TAG: String = "Navigator"


        fun gotoSightingsActivity(context: Context) {
            // TODO
        }

        fun gotoRegisterInsectActivity(context: Context) {
            // TODO
        }

        fun gotoTheftMessageActivity(context: Context) {
            // TODO
        }

        fun gotoChatsActivity(context: Context) {
            val intent = Intent(context, ChatsActivity::class.java)
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, context.getString(R.string.dummy_extra_string))
            context.startActivity(intent)
        }

        fun gotoQrSafetyActivity(context: Context) {
            // TODO
        }

    }
}
