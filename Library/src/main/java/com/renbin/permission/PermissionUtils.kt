package com.renbin.permission

import androidx.fragment.app.FragmentActivity

/**
 *data:2021/6/25
 *Author:renbin
 */
object PermissionUtils {
    private const val TAG = "InvisibleFragment"

    fun request(activity: FragmentActivity, vararg permissions: String, callback:
    PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        //* 将一个 数组转换成可变长度参数传递过去。
        fragment.requestNoew(callback,*permissions)
    }
}