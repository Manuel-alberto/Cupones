package com.devkey.cupones.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkey.cupones.R
import com.devkey.cupones.common.entities.CouponEntity
import com.devkey.cupones.common.utils.getMsgErrorByCode
import com.devkey.cupones.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    
    private val repository = MainRepository()

    val coupon = MutableLiveData(CouponEntity())

    private val hideKeyboard = MutableLiveData<Boolean>()
    fun isHideKeyboard() = hideKeyboard

    private val snackbarMsg = MutableLiveData<Int>()
    
    fun getSnakckBarMsg() = snackbarMsg

    fun consultCouponByCode(){
        coupon.value?.code?.let { code ->
            viewModelScope.launch {
                hideKeyboard.value = true
                coupon.value = repository.consultCouponByCode(code) ?: CouponEntity(code = code, isActive = false)
            }
        }
    }

    fun saveCoupon(){
        coupon.value?.let{couponEntity->
            hideKeyboard.value = true
            viewModelScope.launch {
                try {
                    couponEntity.isActive = true
                    repository.saveCoupon(couponEntity)
                    consultCouponByCode()
                    snackbarMsg.value = R.string.main_save_success
                }catch (e: Exception){
                    snackbarMsg.value = getMsgErrorByCode(e.message)
                }
            }
        }
    }
    
}