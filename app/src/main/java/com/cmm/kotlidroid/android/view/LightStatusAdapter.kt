package com.cmm.kotlidroid.android.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cmm.kotlidroid.R
import com.cmm.kotlidroid.android.model.LightStatusValue
import com.cmm.kotlidroid.android.view.LightStatusAdapter.LightStatusViewHolder

class LightStatusAdapter(context: Context) :
    RecyclerView.Adapter<LightStatusViewHolder>() {

    private val lightStatusList: MutableList<LightStatusValue> = mutableListOf();
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val onClickCallback = context as OnClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LightStatusViewHolder {
        return LightStatusViewHolder(
            inflater.inflate(
                R.layout.activity_android_light_status_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LightStatusViewHolder, position: Int) {
        val currentItem = lightStatusList[position]
        val lux = currentItem.lux

        holder.lightStatus.text = lux.toString()

        if (lux >= 0 && lux < 50) {
            holder.lightStatusIcon.setImageResource(R.drawable.ic_brightness_low)
        }

        if (lux >= 50 && lux < 150) {
            holder.lightStatusIcon.setImageResource(R.drawable.ic_brightness_medium)
        }

        if (lux >= 150) {
            holder.lightStatusIcon.setImageResource(R.drawable.ic_brightness_high)
        }
    }

    fun rebuild(statusList: ArrayList<LightStatusValue>) {
        lightStatusList.clear()
        lightStatusList.addAll(statusList)
        this.notifyDataSetChanged()
    }

    fun getItemAt(pos: Int): LightStatusValue {
        return lightStatusList.get(pos)
    }

    override fun getItemCount(): Int {
        return lightStatusList.size
    }

    fun deleteItem(pos: Int) {
        lightStatusList.removeAt(pos)
        this.notifyItemRemoved(pos)
    }

    inner class LightStatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lightStatus: TextView = itemView.findViewById(R.id.light_status_lux)
        val lightStatusIcon: ImageView = itemView.findViewById(R.id.light_status_icon)

        // Will be used later to display time of data in specific activity.
        private fun itemClicked() {
            onClickCallback.onItemClick(
                this@LightStatusAdapter.lightStatusList[adapterPosition]
            )
        }

        init {
            this.itemView.setOnClickListener { itemClicked() }
        }
    }
}