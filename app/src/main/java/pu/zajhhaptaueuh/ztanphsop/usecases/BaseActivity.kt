package pu.zajhhaptaueuh.ztanphsop.usecases

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.utils.Utils

/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // enable screen transitions
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        // force portrait
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    protected open fun setupActionBar(title: String?) {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance)
        title?.let { toolbar.title = it }
        setSupportActionBar(toolbar)

        // prepare home icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val homeIndicatorDrawable = Utils.tintDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back_white_24dp, null)!!,
                resources.getColor(R.color.toggleActivated))
        supportActionBar?.setHomeAsUpIndicator(homeIndicatorDrawable)
    }

    protected open fun setupActionBar() {
        setupActionBar(null)
    }


    /**
     * @see
     * https://medium.com/@quiro91/improving-findviewbyid-with-kotlin-4cf2f8f779bb
     */
    protected fun <T : View> Activity.bind(@IdRes idRes: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return unsafeLazy { findViewById<T>(idRes) }
    }

    protected fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return unsafeLazy { findViewById<T>(idRes) }
    }

    private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

}

