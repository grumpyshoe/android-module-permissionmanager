package com.grumpyshoe.module.permissionmanager.model

import android.content.pm.PackageManager

data class PermissionResult(val result: Map<String, Int>, val requestCode: Int) {


    /**
     * check if all permissions are granted
     *
     */
    fun areAllGranted(): Boolean {
        var allGranted = true
        result.values.forEach {
            if (it != PackageManager.PERMISSION_GRANTED) {
                allGranted = false
            }
        }
        return allGranted
    }


    /**
     * check if at least one permission is granted
     *
     */
    fun isOneGranted(): Boolean {
        var oneGranted = false
        result.values.forEach {
            if (it == PackageManager.PERMISSION_GRANTED) {
                oneGranted = true
            }
        }
        return oneGranted
    }


    /**
     * get all granted permissions
     *
     */
    fun getGranted(): List<String> {
        return result.filter { it.value == PackageManager.PERMISSION_GRANTED }.map { it.key }
    }


    /**
     * get all denied permissions
     *
     */
    fun getDenied(): List<String> {
        return result.filter { it.value == PackageManager.PERMISSION_DENIED }.map { it.key }
    }

}