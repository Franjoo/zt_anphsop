package pu.zajhhaptaueuh.ztanphsop

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.R.attr.textColorPrimary


/**
 * ©Franz Benthin
 */
class BikeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bike_detail)
    }

//    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
//        super.onPrepareOptionsMenu(menu)
//
//        val item = menu.findItem(R.id.action_camera)
//        val icon = resources.getDrawable(R.drawable.ic_delete_white_24dp)
//        icon.setColorFilter(resources.getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN)
//
//        item.icon = icon
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.ab_bike_detail, menu)
        for (i in 0 until menu.size()) {
            val drawable = menu.getItem(i).icon
            if (drawable != null) {
                drawable.mutate()
                drawable.setColorFilter(resources.getColor(R.color.toggleActivated), PorterDuff.Mode.SRC_ATOP)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_camera -> {
                Log.d("L", "settings")
                return true
            }
            R.id.action_edit -> {
                Log.d("L", "favourite")
                return true
            }

            R.id.action_delete -> {
                Log.d("L", "favourite")
                return true
            }
            else -> {
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setupViews()
        setupActionBar()
    }

    private fun setupActionBar() {
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance)
        setSupportActionBar(toolbar)

        // prepare home icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val homeIndicatorDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back_white_24dp, null)
        homeIndicatorDrawable!!.mutate()
        homeIndicatorDrawable.setColorFilter(resources.getColor(R.color.toggleActivated), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(homeIndicatorDrawable)
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

}