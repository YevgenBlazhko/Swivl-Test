package com.example.swivl.ui.main.users_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.example.swivl.BR
import com.example.swivl.R
import com.example.swivl.ViewModelProviderFactory
import com.example.swivl.databinding.FragmentUserDetailsBinding
import com.example.swivl.mvvm.BaseFragment
import com.example.swivl.ui.main.MainActivity
import com.example.swivl.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_user_details.*
import javax.inject.Inject


class UserDetailsFragment :
    BaseFragment<FragmentUserDetailsBinding, UserDetailsViewModel>(), View.OnClickListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    private var actionBar: ActionBar? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_user_details

    override val viewModel: UserDetailsViewModel
        get() = ViewModelProvider(this, factory).get(UserDetailsViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val login = it.getString(AppConstants.LOGIN)
            if (!login.isNullOrEmpty()) viewModel.fetchUsers(login)
        }
    }
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        setObservable()
        site_tv.setOnClickListener(this)
    }

    private fun setUp() {
        setUpToolbar()
    }

    private fun setObservable() {
        viewModel.userLiveDataLiveData.observe(viewLifecycleOwner, {
            actionBar?.title = it.login
        })
    }

    private fun setUpToolbar() {
        if (activity != null) {
            (activity as MainActivity).setSupportActionBar(getViewDataBinding().toolbar)
            actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            actionBar?.setDisplayShowTitleEnabled(true)
        }
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.site_tv) passToSite()
    }
    
    private fun passToSite() {
        val url = "${viewModel.userLiveDataLiveData.value?.html_url}"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}