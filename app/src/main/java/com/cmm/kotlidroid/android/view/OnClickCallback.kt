package com.cmm.kotlidroid.android.view

import com.cmm.kotlidroid.android.model.LightStatusValue

interface OnClickCallback {
    fun onItemClick(clickedItem: LightStatusValue)
}