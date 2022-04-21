package com.lgtm.qr_reader

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.lgtm.qr_reader.databinding.ActivityMainBinding
import com.lgtm.qr_reader.delegate.viewBinding
import com.lgtm.qr_reader.permission.PermissionManager
import com.lgtm.qr_reader.view.scan.NavigationRootFragment
import com.lgtm.qr_reader.view.scan.ScanFragment

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMainBinding>()

    private var permissionManager = PermissionManager(this)

    private val exitToast: Toast by lazy {
        Toast.makeText(this, "뒤로가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG)
    }

    private var isTerminateMessageShown: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBottomNavigationView()
        initQrView()
    }

    private fun initQrView() {
        showRequiredPermissionPopup()
    }

    private fun showRequiredPermissionPopup() {
        permissionManager.setPermissions(PERMISSIONS_REQUEST_CODE, REQUIRED_PERMISSIONS)
            .onPermissionDenied { showPermissionSupportDialog() }
            .request()
    }

    private fun showPermissionSupportDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("앱 권한 설정 안내")
            setMessage("QR Reader 를 정상적으로 사용하기 위해 애플리케이션 정보 > 권한의 모든 권한이 필요합니다")
            setPositiveButton("권한설정") { dialog, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
                    Uri.parse("package:" + applicationContext.packageName)
                )
                startActivity(intent)
                dialog.cancel()
            }
            setNegativeButton("취소") { dialog, _ ->
                dialog.cancel()
            }
        }.show()
    }

    private fun initBottomNavigationView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val topFragment = navHostFragment.childFragmentManager.fragments[0]

        if (topFragment is NavigationRootFragment) {
            if (!isTerminateMessageShown) {
                isTerminateMessageShown = true
                exitToast.show()
                Handler(Looper.getMainLooper()).postDelayed({ isTerminateMessageShown = false }, 3000)
            } else {
                exitToast.cancel()
                finish()
            }
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val PERMISSIONS_REQUEST_CODE: Int = 1024

        val REQUIRED_PERMISSIONS : Array<String> = arrayOf(
            Manifest.permission.CAMERA
        )
    }
}