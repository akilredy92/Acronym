package com.android.acronymapp.view

import android.app.ProgressDialog
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.acronymapp.databinding.ActivityMainBinding
import com.android.acronymapp.view.adapter.AcronymAdapter
import com.android.acronymapp.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null

    // view binding for the activity
    private var _binding: ActivityMainBinding? = null
    private val activityMainBinding get() = _binding!!
    private lateinit var adapter: AcronymAdapter
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        adapter = AcronymAdapter()
        activityMainBinding.rvAcronymList.adapter = adapter
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        activityMainBinding.searchBtn.setOnClickListener {
            val searchStr = activityMainBinding.etSearch.text.toString().uppercase(Locale.ROOT)
            if (searchStr.isNotEmpty() &&
                searchStr.trim().length >= 2
            ) {
                hideKeyboard()
                showDialog("Loading....")
                mainActivityViewModel.getAcronyms(activityMainBinding.etSearch.text.toString())
                    .observe(this) { listOfAcronyms ->
                        hideDialog()
                        adapter.clearLongForms()
                        if (listOfAcronyms.isNotEmpty() && listOfAcronyms[0].lfs!!.isNotEmpty()) {
                            adapter.addLongForms(listOfAcronyms[0].lfs)
                        } else {
                            Snackbar.make(
                                activityMainBinding.root,
                                "No results found",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else
                Snackbar.make(
                    activityMainBinding.root,
                    "Please enter minimum 2 characters to search an acronym or initialism",
                    Snackbar.LENGTH_SHORT
                ).show()
        }
    }

    private fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showDialog(message: String?) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            mProgressDialog!!.setCancelable(true)
        }
        mProgressDialog!!.setMessage(message)
        mProgressDialog!!.show()
    }

    private fun hideDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }
}