
  # Permissionmanager

  ![AndroidStudio 3.1.4](https://img.shields.io/badge/Android_Studio-3.1.4-brightgreen.svg)
  ![minSDK 16](https://img.shields.io/badge/minSDK-API_16-orange.svg?style=flat)
  ![targetSDK 27](https://img.shields.io/badge/targetSDK-API_27-blue.svg)

  `Permissionmanager` is a small wrapper for handling [permission requests](https://developer.android.com/training/permissions/requesting).
M
  ## Installation

Add `jitpack` to your repositories in Project `build.gradle` :
```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
    ...
}
```

Add dependency to your app `build.gradle` :
```gradle
implementation 'com.github.grumpyshoe:android-module-permissionmanager:1.0.0'
```


## Usage

Get instance of `Permissionmanager` :

```kotlin
val permissionManager: PermissionManager = PermissionManagerImpl
```


To handle the users decision you have to delegate the response of `onRequestPermissionsResult` in your activity to the library like this:
```
override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
            ?: super.onRequestPermissionsResult(requestCode, permissions, grantResults)

}
```


### Check permission
Check permission grant state by calling `checkPermissions`.
```kotlin
permissionManager.checkPermissions(
    activity = this,
    permissions = <array-of-permissions>,
    onPermissionsGranted = {
        // handle permissions granted
    },
    onPermissionDenied = { permissions ->
        // handle permissions denied
    })
```

It's best practise to inform the user about why permission are needed before requesting them so you can define a `PermissionRequestExplanation` at  `permissionRequestPreExecuteExplanation` with information that explains this request.
When adding this parameter, a AlertDialog will beshown before requestin permissions.

If the user denies a permission first but another action requests this again because it's needed to do the job, then that's where `shouldShowRequestPermissionRationale` takes place.

  You can also define which titel should be shown by adding a `PermissionRequestExplanation` to the attribute `permissionRequestRetryExplanation`.

  At leat you can define which *requestCode* should be used on permissino requests. The default is *_8102_*.


  _Example implementation with all available attributes:_
```kotlin
permissionManager.checkPermissions(
    activity = this,
    permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
    onPermissionsGranted = {
        // handle permissions granted
    },
    onPermissionDenied = { permissions ->
        // handle permissions denied
    },
    permissionRequestPreExecuteExplanation = PermissionRequestExplanation(
            title = "Pre Custom Permission Hint",
            message = "The App will request the permissions ..."),
    permissionRequestRetryExplanation = PermissionRequestExplanation(
            title = "Retry Custom Permission Hint",
            message = "You denied the permissions previously but this permissions are needed because ..."),
    requestCode = 123)
```

  If you want to customize the button labels that are shown on `shouldShowRequestPermissionRationale = true` just add a translation for the following string to your `strings.xml`:
  - permission_request_explanation_btn_grant_text _("grant" by default)_
  - permission_request_explanation_btn_deny_text _("deny" by default)_


## Troubleshooting
Please make sure you have added corresponding permissions to yout Manifest, otherwise you get this response in Logcat without anythins noticable on UI-Screen:
> PermissionManager: Permissions denied: [...]


## Need Help or something missing?

Please [submit an issue](https://bit.ly/2N6VHnh) on GitHub.


## License

This project is licensed under the terms of the MIT license. See the [LICENSE](LICENSE) file.

## Build Environment
```
Android Studio 3.1.4
Build #AI-173.4907809, built on July 23, 2018
JRE: 1.8.0_152-release-1024-b01 x86_64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Mac OS X 10.13.4
```
