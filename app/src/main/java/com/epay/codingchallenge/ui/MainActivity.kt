package com.epay.codingchallenge.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.epay.codingchallenge.R
import com.epay.codingchallenge.core.fragment.FragmentCommunicator
import com.epay.codingchallenge.databinding.ActivityMainBinding
import com.epay.codingchallenge.ui.citysearch.CitySearchBottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

/**
 * Created by Rafiqul Hasan
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentCommunicator {
    private var dataBinding: ActivityMainBinding by Delegates.notNull()
    private var citySearchDialog: CitySearchBottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initToolbar()
        initViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_search -> {
                if (citySearchDialog?.isVisible == true) {
                    citySearchDialog?.dismiss()
                }
                citySearchDialog = CitySearchBottomSheetDialog()
                citySearchDialog?.show(supportFragmentManager, "CitySearchBottomSheetDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }


    override fun showLoader() {
        runOnUiThread {
            if (!loaderDialog.isShowing) {
                loaderDialog.show()
            }
        }
    }

    override fun hideLoader() {
        runOnUiThread {
            if (loaderDialog.isShowing) {
                loaderDialog.dismiss()
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(dataBinding.toolbar)
    }

    private fun initViewPager() {
        val pagerAdapter = WeatherInfoFragmentPagerAdapter(this)
        dataBinding.pager.adapter = pagerAdapter
        dataBinding.pager.isUserInputEnabled = false
        TabLayoutMediator(dataBinding.tabLayout, dataBinding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.city_rio_de_janeiro)
                1 -> getString(R.string.city_beijing)
                else -> getString(R.string.city_los_angeles)
            }
        }.attach()
    }

    private val loaderDialog: AlertDialog by lazy {
        val builder = MaterialAlertDialogBuilder(this@MainActivity, R.style.LoaderDialog)
        val dialogView = LayoutInflater.from(this@MainActivity)
            .inflate(R.layout.dialog_loader, findViewById(android.R.id.content), false)
        builder.setView(dialogView)
        builder.setCancelable(false)
        return@lazy builder.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}