package com.example.skywebproandroidapp.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skywebproandroidapp.adapter.PhotoAdapter
import com.example.skywebproandroidapp.ProviderFactory.PhotoViewModelProviderFactory
import com.example.skywebproandroidapp.R
import com.example.skywebproandroidapp.model.WeatherItem
import com.example.skywebproandroidapp.repository.PhotoRepository
import com.example.skywebproandroidapp.utils.Resource
import com.example.skywebproandroidapp.viewModel.PhotoViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_photo.*
import timber.log.Timber

class PhotoFragment: Fragment() {

    private lateinit var viewModel: PhotoViewModel
    lateinit var newsAdapter: PhotoAdapter
    lateinit var weatherItem: WeatherItem
    val TAG = "BreakingNewsFragment"
    var page = 0
    var isLoading = false
    val limit = 10

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoRepository = PhotoRepository()
        val viewModelProviderFactory = PhotoViewModelProviderFactory(photoRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(PhotoViewModel::class.java)


        viewModel.photoPicsum.observe(this, Observer { response ->
            Timber.d("TAG_OBSERVE")
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        Timber.d("TAG_LIST: ${Gson().toJson(newsResponse)}")
                        newsAdapter.differ.submitList(newsResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        setupRecyclerView()
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
//        newsAdapter = PhotoAdapter()
//        isLoading = true
//        paginationProgressBar.visibility = View.VISIBLE
//        val start = ((page) * limit) + 1
//        val end = (page + 1) * limit
//
//        for (i in start..end) {
//            newsAdapter.differ.currentList.size
//        }
//
//        Handler().postDelayed({
//            if (::newsAdapter.isInitialized) {
//                newsAdapter.notifyDataSetChanged()
//            } else {
//                rvBreakingNews.adapter = newsAdapter
//            }
//            isLoading = false
//            paginationProgressBar.visibility = View.GONE
//        }, 5000)

        newsAdapter = PhotoAdapter()
        val manager = LinearLayoutManager(requireActivity())
        rvBreakingNews?.layoutManager = manager
        rvBreakingNews?.adapter = newsAdapter
        newsAdapter.notifyDataSetChanged()


    }

    companion object {
        fun newInstance(): PhotoFragment = PhotoFragment()
    }
}