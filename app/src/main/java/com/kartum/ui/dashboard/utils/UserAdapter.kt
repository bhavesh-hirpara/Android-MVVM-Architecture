package com.kartum.ui.dashboard.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kartum.R
import com.kartum.apputils.Utils
import com.kartum.databinding.ItemProgressbarBinding
import com.kartum.databinding.ItemUserBinding
import com.kartum.ui.dashboard.datamodel.UserData


/**
 * Created by Kartum Infotech (Bhavesh Hirpara) on 30-Mar-19.
 */

class UserAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: MutableList<UserData.Datum> = ArrayList<UserData.Datum>()
    private lateinit var mEventListener: EventListener
    internal var isSelected = false
    private var isFooterEnabled = false
    private val VIEW_TYPE_ITEM = 1
    private val VIEW_TYPE_PROGRESSBAR = 0

    interface EventListener {
        fun onItemClick(pos: Int)
    }

    lateinit var context : Context

    constructor(context: Context) : this(){
        this.context = context
    }

    fun addAll(mData: List<UserData.Datum>,page: Int) {
        try {
            if (page == 1) {
                data.clear()
                data.addAll(mData)
                notifyDataSetChanged()
            } else {
                data.addAll(mData)
                notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Utils.sendExceptionReport(e)
        }


        notifyDataSetChanged()
    }

    fun add(mData: UserData.Datum) {
        try {
            this.data.add(mData)

        } catch (e: Exception) {
            Utils.sendExceptionReport(e)
        }


        notifyDataSetChanged()
    }

    fun getItem(pos: Int): UserData.Datum {
        return data[pos]
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_ITEM) {
            var inflater = LayoutInflater.from(context)
            var view = DataBindingUtil.inflate<ItemUserBinding>(inflater,
                    R.layout.item_user,parent,false)
            ViewHolder(view)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val itemProgressbarBinding = DataBindingUtil.inflate<ItemProgressbarBinding>(inflater,
                    R.layout.item_progressbar,parent,false)
            ProgressViewHolder(itemProgressbarBinding)
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,position: Int) {

        if (holder is ProgressViewHolder) {
            holder.itemProgressbarBinding.progressBar.setIndeterminate(true)
        } else if (holder is ViewHolder && data.size > 0 && position < data.size) {
            var item = data[position]
            holder.itemBinder.data = item

            if (item.avatar.isNullOrEmpty().not()) {
                Utils.loadImage(holder.itemBinder.imgUserProfile,item.avatar!!,context,R.color.gray_light)
            } else {
                holder.itemBinder.imgUserProfile.setImageResource(R.mipmap.ic_user)
            }

            holder.itemBinder.container.setOnClickListener {
                if (mEventListener != null) {
                    mEventListener.onItemClick(position)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun enableFooter(isEnabled: Boolean) {
        try {
            if (isEnabled && data.isEmpty()) {
                return
            }
            this.isFooterEnabled = isEnabled
            notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isFooterEnabled && position >= data.size) VIEW_TYPE_PROGRESSBAR else VIEW_TYPE_ITEM
    }

    inner class ProgressViewHolder(internal var itemProgressbarBinding: ItemProgressbarBinding) : RecyclerView.ViewHolder(itemProgressbarBinding.root) {}

    inner class ViewHolder(internal var itemBinder: ItemUserBinding)  : RecyclerView.ViewHolder(itemBinder.root)

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

}