package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.utils.Utils

/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

class RegisterInsectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bike_chats)
    }

    override fun onResume() {
        super.onResume()

        setupActionBar()
    }

    private fun setupActionBar() {
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance)
        setSupportActionBar(toolbar)

        // prepare home icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val homeIndicatorDrawable = Utils.tintDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back_white_24dp, null)!!,
                resources.getColor(R.color.toggleActivated))
        supportActionBar?.setHomeAsUpIndicator(homeIndicatorDrawable)
    }
}
