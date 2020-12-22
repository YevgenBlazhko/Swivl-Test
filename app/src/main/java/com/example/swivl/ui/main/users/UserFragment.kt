package com.example.swivl.ui.main.users

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.swivl.BR
import com.example.swivl.R
import com.example.swivl.ViewModelProviderFactory
import com.example.swivl.databinding.FragmentUsersBinding
import com.example.swivl.mvvm.BaseFragment
import com.example.swivl.ui.main.MainActivity
import com.example.swivl.utils.AppConstants
import com.example.swivl.utils.InternetConnection
import kotlinx.android.synthetic.main.fragment_users.*
import javax.inject.Inject

class UserFragment : BaseFragment<FragmentUsersBinding, UserViewModel>(),
    UserAdapterListener, SwipeRefreshLayout.OnRefreshListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var userAdapter: UserAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_users

    override val viewModel: UserViewModel
        get() = ViewModelProvider(
            this,
            factory
        ).get(UserViewModel::class.java)

    override fun onRetryClick() {
        viewModel.fetchUsers()
    }

    override fun onItemClick(item: User) {
        if (InternetConnection.check(requireActivity())) {
            val bundle = bundleOf(AppConstants.LOGIN to item.login)
            findNavController().navigate(R.id.to_userDetailsFragment, bundle)
        } else Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userAdapter = UserAdapter(arrayListOf(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        if (activity != null) (activity as MainActivity).setSupportActionBar(
            getViewDataBinding().toolbar
        )
        setHasOptionsMenu(true)
        setUpRecyclerView()
        srl.setOnRefreshListener(this)
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().resultsBeanRecyclerView.layoutManager = LinearLayoutManager(activity)
        getViewDataBinding().resultsBeanRecyclerView.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().resultsBeanRecyclerView.adapter = userAdapter
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onRefresh() {
        viewModel.fetchUsers()
        srl.isRefreshing = false
    }
}