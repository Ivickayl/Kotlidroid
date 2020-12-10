package com.cmm.kotlidroid.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cmm.kotlidroid.R
import com.cmm.kotlidroid.android.model.LightStatusValue
import kotlinx.android.synthetic.main.activity_android_light_item_details.*

class AndroidActivityLightItemDetails : AppCompatActivity() {

    private lateinit var lightItem: LightStatusValue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_light_item_details)

        lightItem = intent.getSerializableExtra("lightItem") as LightStatusValue

        details_light_status_value.setText(lightItem.lux.toString())
        details_light_status_date.setText(lightItem.date)

        // TODO : Refactor
        var luxValue: Float = lightItem.lux
        if (luxValue >= 0 && luxValue < 50) {
            details_light_status_icon.setImageResource(R.drawable.ic_brightness_low)
        }

        if (luxValue >= 50 && luxValue < 150) {
            details_light_status_icon.setImageResource(R.drawable.ic_brightness_medium)
        }

        if (luxValue >= 150) {
            details_light_status_icon.setImageResource(R.drawable.ic_brightness_high)
        }
    }
}