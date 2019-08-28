package com.kartum.base.view

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.kartum.R
import com.kartum.apputils.AsyncProgressDialog
import com.kartum.apputils.Constant
import com.kartum.apputils.ManagePermissionsImp
import com.kartum.databinding.TopbarBinding
import com.kartum.ui.view.MainActivity
import com.kartum.ui.view.SplashActivity


open class BaseActivity : AppCompatActivity() {

    internal var ad: AsyncProgressDialog? = null

    private val PermissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissionsImp


    internal lateinit var commonReciever: MyServiceReciever

    val activity: BaseActivity
        get() = this

    private var checkActivity: AppCompatActivity? = null

    private var tvTitleText: TextView? = null

    internal lateinit var toast: Toast


    internal lateinit var baseCallback: BaseCallback

    @SuppressLint("ShowToast")
    public override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        toast = Toast.makeText(activity, "", Toast.LENGTH_LONG)

        val intentFilter = IntentFilter()
        intentFilter.addAction(Constant.FINISH_ACTIVITY)
        commonReciever = MyServiceReciever()
        LocalBroadcastManager.getInstance(this).registerReceiver(
                commonReciever, intentFilter)

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarGradiant(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window.setBackgroundDrawableResource(R.drawable.top_bar_background)

        }
    }


    internal inner class MyServiceReciever : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            try {
                if (intent.action!!.equals(
                                Constant.FINISH_ACTIVITY, ignoreCase = true)) {
                    finish()
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun setTitleText(text: String) {
        try {
            if (tvTitleText == null)
                tvTitleText = findViewById<View>(com.kartum.R.id.tvTitleText) as TextView
            tvTitleText!!.text = text

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun showDialog(msg: String) {
        try {
            if (ad != null && ad!!.isShowing) {
                return
            }
            ad = AsyncProgressDialog.getInstant(activity)
            ad!!.show(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun setMessage(msg: String) {
        try {
            ad!!.setMessage(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissDialog() {
        try {
            if (ad != null) {
                ad!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val handled = super.onKeyDown(keyCode, event)

        // Eat the long press event so the keyboard doesn't come up.
        return if (keyCode == KeyEvent.KEYCODE_MENU && event.isLongPress) {
            true
        } else handled

    }

    fun showToast(text: String, duration: Int) {
        runOnUiThread {
            toast.setText(text)
            toast.duration = duration
            toast.show()
        }
    }


    fun initDrawerMenu(b: Boolean, topbar: TopbarBinding) {

        if (b) {


        }
    }

    override fun onDestroy() {
        try {
            LocalBroadcastManager.getInstance(applicationContext)
                    .unregisterReceiver(commonReciever)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        super.onDestroy()
    }


    internal interface BaseCallback {
        fun onMasterDataLoad()
    }

    fun checkPermissions(activity: AppCompatActivity) {
        // Initialize a list of required permissions to request runtime
        this.checkActivity = activity
        val list = listOf<String>(
                Manifest.permission.INTERNET,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_CONTACTS
        )

        // Initialize a new instance of ManagePermissions class
        managePermissions = ManagePermissionsImp(this, list, PermissionsRequestCode)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            managePermissions.checkPermissions(object : ManagePermissionsImp.IPermission {
                override fun onPermissionGranted() {
                    if (activity is SplashActivity) {
                        activity.startApplication(1000)
                    }
                }
            })
        } else {
            if (activity is SplashActivity) {
                activity.startApplication(1000)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            PermissionsRequestCode -> {
                if (checkActivity is SplashActivity) {
                    (checkActivity as SplashActivity).startApplication(1000)
                }
                return
            }
        }
    }

    fun finishActivity() {
        if (activity is MainActivity) {
        } else {
            activity.finish()
        }
    }


}
