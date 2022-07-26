package com.devkey.cupones.mainModule.model

import com.devkey.cupones.common.entities.CouponEntity
import com.devkey.cupones.common.utils.Constants
import com.devkey.cupones.common.utils.validateTextCode

class MainRepository {

    private val roomDatabase = RoomDatabase()

    suspend fun consultCouponByCode(code: String) = roomDatabase.consultCouponByCode(code)

    suspend fun saveCoupon(couponEntity: CouponEntity){
        if (validateTextCode(couponEntity.code)){
            roomDatabase.saveCoupon(couponEntity)
        }else{
            throw Exception(Constants.ERROR_LENGTH)
        }
    }

}