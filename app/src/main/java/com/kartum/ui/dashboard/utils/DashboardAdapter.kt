package com.kartum.ui.dashboard.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kartum.R
import com.kartum.apputils.MenuItem
import com.kartum.apputils.Utils
import kotlinx.android.synthetic.main.item_dashboard.view.*


/**
 * Created by Kartum Infotech (Bhavesh Hirpara) on 30-Mar-19.
 */

class DashboardAdapter() : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private var data: MutableList<MenuItem> = ArrayList<MenuItem>()
    private lateinit var mEventListener: EventListener
    internal var isSelected = false

    interface EventListener {
        fun onItemClick(pos: Int)
    }

    lateinit var context : Context

    constructor(context: Context) : this(){
        this.context = context
    }

    fun addAll(mData: List<MenuItem>) {
        try {

            this.data.clear()
            this.data.addAll(mData)


        } catch (e: Exception) {
            Utils.sendExceptionReport(e)
        }


        notifyDataSetChanged()
    }

    fun add(mData: MenuItem) {
        try {
            this.data.add(mData)

        } catch (e: Exception) {
            Utils.sendExceptionReport(e)
        }


        notifyDataSetChanged()
    }

    fun getItem(pos: Int): MenuItem {
        return data[pos]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.item_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.tvIconName.text = item.name;
        holder.ivIcon.setImageResource(item.icon)

        holder.container.setOnClickListener {
            if (mEventListener != null){
                mEventListener.onItemClick(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view : View)  : RecyclerView.ViewHolder(view) {
        var tvIconName: TextView = view.tvIconName as TextView
        var ivIcon = view.ivIcon
        var container = view.container

    }

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

}