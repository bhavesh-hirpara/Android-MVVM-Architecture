package com.kartum.ui.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.databinding.DataBindingUtil
import com.kartum.R
import com.kartum.apputils.Utils
import com.kartum.base.view.BaseActivity
import com.kartum.databinding.ActivitySplashBinding
import com.kartum.ui.dashboard.view.DashboardActivity
import com.kartum.ui.login.view.LoginActivity

class SplashActivity : BaseActivity() {
    internal var TAG = "SplashActivity"
    internal var handler = Handler()
    internal lateinit var binding: ActivitySplashBinding

    internal var startApp: Runnable = object : Runnable {
        override fun run() {
            handler.removeCallbacks(this)

            if (Utils.isUserLoggedIn(activity)) {
                val i = Intent(activity,DashboardActivity::class.java)
                startActivity(i)
                finish()
            } else {
                val i = Intent(activity,LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)

        Utils.getHashKey()

//        try {
//            if (Utils.isInternetConnected(activity)) {
//                checkPermissions(this)
        startApplication(1000)
//            } else {
//                handler.postDelayed(dialogRunnable,100)
//           }
//        } catch (e: NoInternetException) {
//            e.printStackTrace()
//        }
    }


    public fun startApplication(sleepTime: Long) {
        handler.postDelayed(startApp,sleepTime)
    }

    public override fun onDestroy() {
        try {
            handler.removeCallbacks(startApp)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

    private fun showInternetDialog() {
        val builder = AlertDialog.Builder(activity,R.style.MyAlertDialogStyle)
        builder.setTitle(getString(R.string.connection_title))
                .setMessage(getString(R.string.connection_not_available))
                .setPositiveButton(android.R.string.ok) { dialog,which ->
                    try {
                        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                        startActivity(intent)
                        finish()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                .setNegativeButton(getString(R.string.permission_setting)) { dialog,which ->
                    dialog.dismiss()
                    finish()
                }.show()
    }

    internal var dialogRunnable: Runnable = object : Runnable {
        override fun run() {
            handler.removeCallbacks(this)
            showInternetDialog()
        }
    }
}
