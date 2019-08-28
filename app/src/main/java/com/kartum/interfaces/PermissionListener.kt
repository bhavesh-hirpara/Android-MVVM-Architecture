package com.kartum.interfaces

interface PermissionListener {

    fun onPermissionClick()

    fun onPermissionAllow(isAllow : Boolean)
}