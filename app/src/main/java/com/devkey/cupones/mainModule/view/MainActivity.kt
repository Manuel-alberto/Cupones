package com.devkey.cupones.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.devkey.cupones.BR
import com.devkey.cupones.R
import com.devkey.cupones.common.utils.hideKeyword
import com.devkey.cupones.databinding.ActivityMainBinding
import com.devkey.cupones.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()

    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.coupon.observe(this@MainActivity){coupon->
                binding.isActive = coupon != null && coupon.isActive
            }
            it.getSnakckBarMsg().observe(this@MainActivity){msg->
                Snackbar.make(binding.root, getString(msg), Snackbar.LENGTH_SHORT).show()
            }
            it.isHideKeyboard().observe(this@MainActivity){isHide->
                if (isHide) hideKeyword(this@MainActivity, binding.root)
            }
        }
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }
}