package com.devkey.cupones

import android.app.Application
import androidx.room.Room
import com.devkey.cupones.common.dataAccess.CouponDatabase

class CouponsAplication : Application() {

    companion object{
        lateinit var database: CouponDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this,
            CouponDatabase::class.java,
            "CouponDatabase")
            .build()

    }



}