package com.grumpyshoe.module.permissionmanager

import android.app.Activity
import com.grumpyshoe.module.permissionmanager.model.PermissionRequestExplanation
import com.grumpyshoe.module.permissionmanager.model.PermissionResult


/**
 * <p>PermissionManager - interface for easy access to permission handling</p>
 *
 * @since    1.0.0
 * @version  1.0.0
 * @author   grumpyshoe
 *
 */
interface PermissionManager {


    companion object {
        const val DEFAULT_PERMISSION_REQUEST_CODE = 8102
    }

    /**
     * check permission
     *
     */
    fun checkPermissions(activity: Activity, permissions: Array<out String>, onPermissionResult: ((PermissionResult) -> Unit)?, permissionRequestPreExecuteExplanation: PermissionRequestExplanation? = null, permissionRequestRetryExplanation: PermissionRequestExplanation? = null, requestCode: Int? = null): Boolean


    /**
     * handle permission request result
     *
     */
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean?


}