package com.epay.codingchallenge.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.epay.codingchallenge.R
import com.epay.codingchallenge.core.fragment.FragmentCommunicator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Rafiqul Hasan
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main), FragmentCommunicator {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
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

    override fun setActionBar(toolbar: Toolbar, enableBackButton: Boolean) {
        setSupportActionBar(toolbar)
        if (enableBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }
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
}