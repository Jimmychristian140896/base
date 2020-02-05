package com.android.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.baselibrary.activity.MapsActivity
import com.android.baselibrary.extension.startActivity
import kotlinx.android.synthetic.main.activity_empty.*

class EmptyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        btnMap.setOnClickListener {
            startActivity(MapsActivity::class.java)
        }
    }
}
