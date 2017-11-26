package pu.zajhhaptaueuh.ztanphsop

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * ©Franz Benthin
 */
class BikeDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bike_detail)
    }

    override fun onResume() {
        super.onResume()
        setupViews()
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