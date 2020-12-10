package com.cmm.kotlidroid.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmm.kotlidroid.R
import com.cmm.kotlidroid.android.model.LightStatusValue
import com.cmm.kotlidroid.android.view.LightStatusAdapter
import com.cmm.kotlidroid.android.view.OnClickCallback
import com.cmm.kotlidroid.android.view.SwipeToDelete
import com.cmm.kotlidroid.android.viewmodel.LightStatusViewModel
import kotlinx.android.synthetic.main.activity_android.*

class AndroidActivity : AppCompatActivity(), OnClickCallback {

    private lateinit var adapter: LightStatusAdapter
    private lateinit var sensorViewModel: LightStatusViewModel
    private var observerLightStatus = Observer<LightStatusValue> {
        updateLuxUI(it)
    }
    private var dbObserverLightStatus = Observer<List<LightStatusValue>> {
        updateRecyclerView(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)

        sensorViewModel = ViewModelProvider(this)[LightStatusViewModel::class.java]

        adapter = LightStatusAdapter(this)
        light_status_recycler_view.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        light_status_recycler_view.adapter = adapter

        //Swipe to delete
        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter, sensorViewModel))
        itemTouchHelper.attachToRecyclerView(light_status_recycler_view)
    }

    override fun onStart() {
        super.onStart()
        sensorViewModel.lightMutableLiveData.observe(this, observerLightStatus)
        sensorViewModel.dbLightMutableLiveData.observe(this, dbObserverLightStatus)
    }

    override fun onStop() {
        sensorViewModel.lightMutableLiveData.removeObserver(observerLightStatus)
        sensorViewModel.dbLightMutableLiveData.removeObserver(dbObserverLightStatus)
        super.onStop()
    }

    private fun updateLuxUI(value: LightStatusValue) {
        light_sensor_value.text = value.lux.toString()
    }

    private fun updateRecyclerView(value: List<LightStatusValue>) {
        adapter.rebuild(ArrayList(value))
    }

    fun addValue(view: View) {
        val bleuh: String = light_sensor_value.text.toString()
        val value: Float = bleuh.toFloat()

        sensorViewModel.addData(value)
    }

    fun deleteAll(view: View) {
        sensorViewModel.deleteAll()
    }

    override fun onItemClick(itemClicked: LightStatusValue) {
        startActivity(Intent(this, AndroidActivityLightItemDetails::class.java).also {
            it.putExtra("lightItem", itemClicked)
        })
    }
}