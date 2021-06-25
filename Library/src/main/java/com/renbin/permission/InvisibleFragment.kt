package com.renbin.permission

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 *data:2021/6/24
 *Author:renbin
 */
//typealias关键字可以用于给任意类型指定一个别名，比 如我们将(Boolean, List<String>) -> Unit的别名
// 指定成了PermissionCallback
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    private var callback : PermissionCallback ?= null

    fun requestNoew(cb : PermissionCallback,vararg  permissions : String){
        callback = cb
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
  //      super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode ==1){
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()){
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }

            val allGranted = deniedList.isEmpty()
            callback?.let {
                it(allGranted,deniedList)
            }
        }
    }
}