package com.kartum.ui.dashboard.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kartum.R
import com.kartum.apputils.MenuItem
import com.kartum.databinding.ItemSideMenuBinding
import com.kartum.interfaces.ClickListener
import java.util.*


class SideMenuAdapter(internal var context: Context) : RecyclerView.Adapter<SideMenuAdapter.MyViewHolder>() {

    private val data = mutableListOf<MenuItem>()
    internal var mEventlistener: EventListener? = null

    fun getItem(pos: Int): MenuItem {
        return data[pos]
    }

    fun addAll(mData: ArrayList<MenuItem>) {
        data.clear()
        data.addAll(mData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rowSideMenuBinding = DataBindingUtil.inflate<ItemSideMenuBinding>(inflater,
                R.layout.item_side_menu,parent,false)
        return MyViewHolder(rowSideMenuBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder,position: Int) {
        val item = getItem(position)
        holder.rowSideMenuBinding.menuItem = item
        holder.rowSideMenuBinding.clicker = object : ClickListener {
            override fun onClick(obj: Any) {
                if (mEventlistener != null) {
                    mEventlistener!!.onMenuItemClick(position,holder.rowSideMenuBinding.root)
                }
            }
        }
    }

    fun changeSelectedItemUi(position: Int) {
        for (item in data.indices) {
            data[item].isSelected = item == position
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(internal var rowSideMenuBinding: ItemSideMenuBinding) : RecyclerView.ViewHolder(rowSideMenuBinding.root)

    interface EventListener {
        fun onMenuItemClick(position: Int, view : View)
    }

    fun setEventListener(eventListener: EventListener) {
        this.mEventlistener = eventListener
    }
}
