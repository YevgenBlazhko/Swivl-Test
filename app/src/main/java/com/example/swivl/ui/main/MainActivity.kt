package com.example.swivl.ui.main

import androidx.lifecycle.ViewModelProvider
import com.example.swivl.ViewModelProviderFactory
import com.example.swivl.BR
import com.example.swivl.R
import com.example.swivl.databinding.ActivityMainBinding
import com.example.swivl.mvvm.BaseActivity
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    HasAndroidInjector {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this, factory).get(MainViewModel::class.java)
}