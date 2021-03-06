package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import pu.zajhhaptaueuh.ztanphsop.Bikes
import pu.zajhhaptaueuh.ztanphsop.Constants
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.dialogs.DialogProvider
import pu.zajhhaptaueuh.ztanphsop.models.BikeData
import pu.zajhhaptaueuh.ztanphsop.navigation.Navigator
import pu.zajhhaptaueuh.ztanphsop.network.ApiClient
import pu.zajhhaptaueuh.ztanphsop.usecases.BaseActivity
import pu.zajhhaptaueuh.ztanphsop.utils.Utils
import kotlin.coroutines.experimental.CoroutineContext


/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

class BikeDetailActivity : BaseActivity() {

    //    private val tag = this::class.simpleName as String
    val tag: String = EditBikeActivity@ this.javaClass.simpleName
    private var bikeId: String? = null

    /** lifecycle */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bike_detail)

        setMenuValues()
        setupMenuClickListener()
        setupViews()
        setupMenu()
        setupActionBar()

        if (Bikes.isEmpty()) {
            restoreBikeData()
        } else {
            bikeId = Constants.DUMMY_BIKE_ID // todo
        }

//        if (savedInstanceState != null) {
//            bikeId = savedInstanceState.getString(Constants.BUNDLE_BIKE_ID)
//        }

    }

    /** lifecycle */
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(Constants.Bundles.BIKE_ID, bikeId)
        super.onSaveInstanceState(outState)
    }

    /** lifecycle */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            Constants.Results.RESULT_SAVED_CHANGES ->
                Utils.snack(this, getString(R.string.snack_saved_changes))
        }
    }

    /** lifecycle */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.ab_bike_detail, menu)
        Utils.tintMenu(menu, resources.getColor(R.color.toggleActivated))
        return true
    }

    /** lifecycle */
    override fun onResume() {
        super.onResume()

        // handle bundle extras
        val bikeDataUpdated = intent.getBooleanExtra(Constants.Bundles.SAVED_CHANGES, false)
        if (bikeDataUpdated) {
            Utils.snackLongDelayed(this, getString(R.string.snack_saved_changes))
        }
    }

    /** lifecycle */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_camera -> Navigator.gotoNotImplementedActivity(this, getString(R.string.screen_title_edit_images))
            R.id.action_edit -> {
                val bundle = Bundle()
                bundle.putString(Constants.Bundles.BIKE_ID, bikeId)
                Navigator.withBundle(bundle).gotoEditBikeActivity(this)
            }
            R.id.action_delete -> delete()
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun setupMenuClickListener() {

        // handle menu clicks
        val listener: View.OnClickListener = View.OnClickListener {
            when (it.id) {
                R.id.item_chats -> Navigator.gotoChatsActivity(this)
                R.id.item_sightings -> Navigator.gotoSightingsActivity(this)
                R.id.item_theft_message -> Navigator.gotoNotImplementedActivity(this, getString(R.string.theft_messages))
            }
        }

        // assign listener to all menu items
        mutableListOf<ViewGroup>(
                findViewById(R.id.item_chats),
                findViewById(R.id.item_sightings),
                findViewById(R.id.item_theft_message))
                .forEach { v -> v.setOnClickListener(listener) }

    }

    private fun setMenuValues() {
        val stringValues = resources.getStringArray(R.array.bike_detail_list_items)
        setMenuItemValues(findViewById(R.id.item_theft_message), stringValues[0], stringValues[1])
        setMenuItemValues(findViewById(R.id.item_sightings), stringValues[2], stringValues[3])
        setMenuItemValues(findViewById(R.id.item_chats), stringValues[4], stringValues[5])
    }

    private fun setMenuItemValues(holder: ViewGroup, title: String, subTitle: String) {
        holder.findViewById<TextView>(R.id.head).text = title
        holder.findViewById<TextView>(R.id.sub).text = subTitle
    }

    private fun setupMenu() {
        // todo handle current insect mode state
    }

    private fun setupViews() {

        // toggle views
        setupToggle(R.id.bike_toggle_arm, R.string.arm, R.drawable.ic_notifications_active_white_24dp)
        setupToggle(R.id.bike_toggle_disarm, R.string.disarm, R.drawable.ic_notifications_off_white_24dp)
        setupToggle(R.id.bike_toggle_settings, R.string.settings, R.drawable.ic_settings_white_24dp)

        // headline
        val headline: TextView = findViewById(R.id.bike_headline)
        headline.typeface = ResourcesCompat.getFont(applicationContext, R.font.bitter)
    }

    private fun setupToggle(parentId: Int, stringID: Int, iconId: Int) {
        val icon: ImageView = findViewById<LinearLayout>(parentId).getChildAt(0) as ImageView
        val tv: TextView = findViewById<LinearLayout>(parentId).getChildAt(1) as TextView
        tv.text = resources.getString(stringID)
        icon.setImageResource(iconId)
    }

    private fun restoreBikeData() {
        val exceptionHandler: CoroutineContext = CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }
        async(UI + exceptionHandler) {
            val deferredBikeData: Deferred<BikeData> = async(CommonPool) { ApiClient().requestBikeData() }
            val bike = deferredBikeData.await()
            Bikes.put(bike.id, bike)
            bikeId = bike.id
        }
    }

    private fun delete() {
        DialogProvider.createDeleteBikeDialog(this,
                { Log.d(tag, "bike was deleted") }, // on yes
                { Log.d(tag, "dialog canceled") } // on cancel
        ).show()
    }


}
