package com.example.swivl.ui.main.users

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.swivl.databinding.ItemRetryBinding
import com.example.swivl.databinding.ItemUserFeedBinding
import com.example.swivl.mvvm.BaseRecyclerViewAdapter
import com.example.swivl.mvvm.BaseViewHolder
import com.example.swivl.utils.AppConstants.VIEW_TYPE_EMPTY
import com.example.swivl.utils.AppConstants.VIEW_TYPE_NORMAL

class UserAdapter(items: MutableList<User>, listener: UserAdapterListener) :
    BaseRecyclerViewAdapter<User>(items, listener) {

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isNotEmpty()) VIEW_TYPE_NORMAL else VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                UserViewHolder(
                    ItemUserFeedBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    ItemRetryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    inner class UserViewHolder(private val mBinding: ItemUserFeedBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            val userDataItem = items[position]
            mBinding.userDataItem = userDataItem
            mBinding.item = UserItemView { itemListener.onItemClick(userDataItem) }
            mBinding.executePendingBindings()
        }
    }

    inner class EmptyViewHolder(private val mBinding: ItemRetryBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            mBinding.item = EmptyItemView { itemListener.onRetryClick() }
            mBinding.executePendingBindings()
        }
    }
}