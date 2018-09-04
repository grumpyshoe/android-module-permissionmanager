package com.grumpyshoe.modules.permissionmanager.sample

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.grumpyshoe.module.permissionmanager.PermissionManager
import com.grumpyshoe.module.permissionmanager.impl.PermissionManagerImpl
import com.grumpyshoe.module.permissionmanager.model.PermissionRequestExplanation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // get instance of permission manager
    private val permissionManager: PermissionManager = PermissionManagerImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            result.onUI { it.text = "Permission Status:\n\n..." }

            permissionManager.checkPermissions(
                    activity = this,
                    permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE),
                    onPermissionResult = { permissionResult ->
                        Log.d("PermissionManager", "Permission resul\n- granted: ${permissionResult.getGranted()}\n- denied: ${permissionResult.getDenied()}")
                        result.onUI { it.text = "Permission Status:\n\nGRANTED:\n${permissionResult.getGranted()}\n\nDENIED:\n${permissionResult.getDenied()}" }
                    },
                    permissionRequestPreExecuteExplanation = PermissionRequestExplanation(
                            title = "Pre Custom Permission Hint",
                            message = "The App will request the permissions ..."),
                    permissionRequestRetryExplanation = PermissionRequestExplanation(
                            title = "Retry Custom Permission Hint",
                            message = "You denied the permissions previously but this permissions are needed because ..."),
                    requestCode = 123)

        }

    }


    /**
     * handle permission request result
     *
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
                ?: super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }


    fun TextView.onUI(action: (TextView) -> Unit) {
        runOnUiThread {
            action(this)
        }
    }

}
