package pu.zajhhaptaueuh.ztanphsop.usecases

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import pu.zajhhaptaueuh.ztanphsop.R

/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class NotImplementedActivity : BaseActivity() {

    companion object {
        val EXTRA_IDENTIFIER:String = "fj.intent.extra.IDENTIFIER"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.not_implemented_layout)

        val text = intent.getStringExtra(EXTRA_IDENTIFIER)
        findViewById<TextView>(R.id.not_implemented_tv)!!.text = ("[ $text not implemented yet ]")
    }
}