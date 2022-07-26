package com.devkey.cupones.mainModule.model

import android.database.sqlite.SQLiteConstraintException
import com.devkey.cupones.CouponsAplication
import com.devkey.cupones.common.dataAccess.CouponDao
import com.devkey.cupones.common.entities.CouponEntity
import com.devkey.cupones.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDatabase {

    private val dao: CouponDao by lazy{
        CouponsAplication.database.couponDao()
    }

    suspend fun consultCouponByCode(code: String) = dao.consultCouponByCode(code)

    suspend fun saveCoupon(cuponEntity: CouponEntity) = withContext(Dispatchers.IO){
        try {
            dao.addCoupon(cuponEntity)
        }catch (e: Exception){
            (e as? SQLiteConstraintException)?.let{ throw Exception(Constants.ERROR_EXIST)}
            throw Exception(e.message ?: Constants.ERROR_UNKNOW)
        }
    }

}