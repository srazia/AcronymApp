package com.android.acronymsearchapp

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.acronymsearchapp.databinding.ActivityMainBinding
import com.android.acronymsearchapp.ui.adapter.AcronymListAdapter
import com.android.acronymsearchapp.util.Status
import com.android.acronymsearchapp.viewmodel.AcronymViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var acronymListAdapter: AcronymListAdapter
    private lateinit var itemBinding: ActivityMainBinding
    val acronymViewModel: AcronymViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(itemBinding.root)

        initAdapter()

        itemBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {
                    if (it.length > 2) {
                        acronymViewModel.loadAcronymSearchData(this@MainActivity, it)
                    } else {

                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //    adapter.getFilter().filter(newText);
                return false
            }
        })

        setupObserver()

    }

    private fun initAdapter() {

        val layoutManager = LinearLayoutManager(this)
        // Set the layout manager to the RecyclerView
        itemBinding.recyclerView.layoutManager = layoutManager

        acronymListAdapter = AcronymListAdapter()
        itemBinding.recyclerView.adapter = acronymListAdapter
    }

    private fun setupObserver() {

        acronymViewModel.acronymListInfo.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                 it.data?.let {

                 }

                }
                Status.LOADING -> {
                    /*progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE*/
                }
                Status.ERROR -> {
                    //dataBinding.txtErrorMessageView.visibility = View.VISIBLE
                    //dataBinding.txtErrorMessageView.text = it.message
                }
            }
        })
    }

}

