package pu.zajhhaptaueuh.ztanphsop.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import pu.zajhhaptaueuh.ztanphsop.Constants
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.usecases.NotImplementedActivity
import pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail.BikeDetailActivity
import pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail.ChaseMapActivity
import pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail.ChatsActivity
import pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail.EditBikeActivity


/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

/**
 * The Navigator provides methods for setting up intent extras and starting Activities with transitions.
 * It is also responsible for handling the Activity Backstack.
 */
class Navigator {

    @Suppress("JAVA_CLASS_ON_COMPANION")
    companion object {

        val TAG = Navigator@ this.javaClass.simpleName
        private val OP_ACTIVITY = "Start Activity: "
        private var pendingBundle: Bundle? = null

        fun withBundle(bundle: Bundle): Companion {
            pendingBundle = bundle
            return this
        }

        fun gotoBikeDetailActivity(context: Activity, savedChanges: Boolean) {

            val intent = Intent(context, BikeDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

            pendingBundle?.let {
                intent.putExtras(it)
            }

            context.setResult(Constants.RESULT_SAVED_CHANGES, intent)
            context.startActivityForResult(intent, Constants.RESULT_SAVED_CHANGES)
            context.overridePendingTransition(R.anim.left_in, R.anim.left_out)
            context.finish()

            clearPendingActions()

            logStart(BikeDetailActivity@ this.javaClass.simpleName)
        }

        private fun clearPendingActions() {
            pendingBundle = null
        }

        fun gotoBikeDetailActivity(context: Activity) {
            gotoBikeDetailActivity(context, false)
//            val intent =  Intent(context, EditBikeActivity::class.java)
//            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


        }

        fun gotoEditBikeActivity(context: Activity) {
            val intent = Intent(context, EditBikeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            pendingBundle?.let {
                intent.putExtras(it)
            }

            context.startActivity(intent)
            context.overridePendingTransition(R.anim.right_in, R.anim.right_out)

            logStart(EditBikeActivity@ this.javaClass.simpleName)
        }

        fun gotoSightingsActivity(context: Activity) {
            val intent = Intent(context, ChaseMapActivity::class.java)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.right_in, R.anim.right_out)
            logStart(ChaseMapActivity@ this.javaClass.simpleName)
        }

        fun gotoChatsActivity(context: Activity) {
            val intent = Intent(context, ChatsActivity::class.java)
            context.startActivity(intent)
            context.overridePendingTransition(R.anim.right_in, R.anim.right_out)

//            ActivityOptions.makeSceneTransitionAnimation(context, Pair<View,String>(context.findViewById(android.R.id.content),).toBundle();

            logStart(ChatsActivity@ this.javaClass.simpleName)
        }


        fun gotoNotImplementedActivity(context: Context, identifier: String) {
            val intent = Intent(context, NotImplementedActivity::class.java)
            intent.putExtra(NotImplementedActivity.EXTRA_IDENTIFIER, identifier)
            context.startActivity(intent)
            logStart(NotImplementedActivity@ this.javaClass.simpleName)
        }

        private fun logStart(className: String) {
            Log.d(TAG, OP_ACTIVITY + className)
        }

    }
}
