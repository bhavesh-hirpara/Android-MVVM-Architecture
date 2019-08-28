package com.kartum.base.viewmodel

import android.app.Application


open class BaseViewModel(application: Application) : AppViewModel(application) {



    private fun init() {
//        binder.drawer.drawerViewModel = this
//        binder.drawer.drawerViewClickHandler = DrawerViewClickHandler()
//        navHeader = customSideMenu.container
//        var mRecyclerView = customSideMenu.mRecyclerView
//
//        navHeader.llUserDetail.setOnClickListener {
//
//            val intent = Intent(getActivity(), ProfileActivity::class.java)
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
//            startActivity(intent)
////                startActivityForResult(intent,1564)
//            finishActivity()
//            hideMenu(true)
//
//        }
//
//        var layoutManager = LinearLayoutManager(this)
//        mRecyclerView.layoutManager = layoutManager
//
//        var menuAdapter = SideMenuAdapter(this)
//        mRecyclerView.adapter = menuAdapter
//
//        var data = ArrayList<SideMenuItem>()
//
//        data.add(SideMenuItem("1", R.mipmap.ic_home, getString(R.string.home)))
//        data.add(SideMenuItem("2", R.mipmap.ic_kundali, getString(R.string.kundli)))
//        data.add(SideMenuItem("3", R.mipmap.ic_horoscope, getString(R.string.horoscope)))
//        data.add(SideMenuItem("4", R.mipmap.ic_contact, getString(R.string.contact_list)))
//        data.add(SideMenuItem("5", R.mipmap.ic_language, getString(R.string.language)))
//        data.add(SideMenuItem("6", R.mipmap.ic_queation, getString(R.string.ask_ques)))
//        data.add(SideMenuItem("7", R.mipmap.ic_set_preference, getString(R.string.pref_screen)))
//        data.add(SideMenuItem("8", R.mipmap.ic_logout, getString(R.string.logout)))
//
//        menuAdapter.addAll(data)
//
//        menuAdapter.setEventListener(object : SideMenuAdapter.EventListener {
//            override fun onItemClick(pos: Int) {
//
//                var id = menuAdapter.getItem(pos).ID
//
//                if (id.contains("1")) {
//                    if (getActivity() is MainActivity) {
//                        hideMenu(true)
//                    } else {
//                        val intent = Intent(getActivity(), MainActivity::class.java)
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                        startActivity(intent)
//                        finishActivity()
//                        hideMenu(true)
//                    }
//
//                } else if (id.contains("2")) {
//                    if (getActivity() is KundliActivity) {
//                        hideMenu(true)
//                    } else {
//                        val i = Intent(getActivity(), KundliActivity::class.java)
//                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                        i.putExtra("from", "kundli")
//                        startActivity(i)
//                        finishActivity()
//                        hideMenu(true)
//                    }
//                } else if (id.contains("3")) {
//                    if (getActivity() is HoroScopeActivity) {
//                        hideMenu(true)
//                    } else {
//                        val i = Intent(getActivity(), HoroScopeActivity::class.java)
//                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                        startActivity(i)
//                        finishActivity()
//                        hideMenu(true)
//                    }
//                } else if (id.contains("4")) {
//                    if (getActivity() is ContactListActivity) {
//                        hideMenu(true)
//                    } else {
//                        val i = Intent(getActivity(), ContactListActivity::class.java)
//                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                        startActivity(i)
//                        hideMenu(true)
//                    }
//                } else if (id.contains("5")) {
//                    if (getActivity() is LanguageActivity) {
//                        hideMenu(true)
//                    } else {
//                        val i = Intent(getActivity(), LanguageActivity::class.java)
//                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                        startActivity(i)
//                        finishActivity()
//                        hideMenu(true)
//                    }
//                } else if (id.contains("6")) {
//                    if (getActivity() is AskQuestionActivity) {
//                        hideMenu(true)
//                    } else {
//                        val i = Intent(getActivity(), AskQuestionActivity::class.java)
//                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                        startActivity(i)
//                        finishActivity()
//                        hideMenu(true)
//                    }
//                }else if (id.contains("7")) {
//                    if (getActivity() is SetPrefActivity) {
//                        hideMenu(true)
//                    } else {
//                        val i = Intent(getActivity(), SetPrefActivity::class.java)
//                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                        startActivity(i)
//                        finishActivity()
//                        hideMenu(true)
//                    }
//                }
//                else if (id.contains("8")) {
//                    confirmLogout()
//                }
//            }
//        })
//
//        result = DrawerBuilder()
//                .withActivity(this)
//                .withCloseOnClick(true)
//                .withSelectedItemByPosition(-1)
//                .withCustomView(customSideMenu)
//                .withDrawerWidthDp(250)
//                .build()
//
//        imgMenu.visibility = View.VISIBLE
//        imgMenu.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                if (result.isDrawerOpen) {
//                    result.closeDrawer()
//                } else {
//                    result.openDrawer()
//                }
//            }
//        })
//    } else {
//        imgMenu.visibility = View.GONE
//
//    }
    }


}
