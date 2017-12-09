package pu.zajhhaptaueuh.ztanphsop.usecases

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
open class BaseActivity : AppCompatActivity() {



    protected open fun setupActionBar(title: String?) {
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance)
        toolbar.title = title
        setSupportActionBar(toolbar)

        // prepare home icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val homeIndicatorDrawable = Utils.tintDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back_white_24dp, null)!!,
                resources.getColor(R.color.toggleActivated) )
        supportActionBar?.setHomeAsUpIndicator(homeIndicatorDrawable)
    }
}

