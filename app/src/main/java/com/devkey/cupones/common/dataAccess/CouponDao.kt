package com.devkey.cupones.common.dataAccess

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devkey.cupones.common.entities.CouponEntity

@Dao
interface CouponDao  {

    @Query ("SELECT * FROM couponentity WHERE code = :code")
    suspend fun consultCouponByCode(code: String): CouponEntity?

    @Insert
    suspend fun addCoupon(couponEntity: CouponEntity): Long

}