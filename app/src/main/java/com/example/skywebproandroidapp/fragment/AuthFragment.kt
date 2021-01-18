package com.example.skywebproandroidapp.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.skywebproandroidapp.ProviderFactory.PhotoViewModelProviderFactory
import com.example.skywebproandroidapp.ProviderFactory.WeatherViewModelProviderFactory
import com.example.skywebproandroidapp.R
import com.example.skywebproandroidapp.model.Clouds
import com.example.skywebproandroidapp.model.Main
import com.example.skywebproandroidapp.model.Weather
import com.example.skywebproandroidapp.model.WeatherItem
import com.example.skywebproandroidapp.repository.PhotoRepository
import com.example.skywebproandroidapp.repository.WeatherRepository
import com.example.skywebproandroidapp.utils.Resource
import com.example.skywebproandroidapp.viewModel.PhotoViewModel
import com.example.skywebproandroidapp.viewModel.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_auth.*
import timber.log.Timber


class AuthFragment: Fragment() {

    private lateinit var viewModel: WeatherViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherRepository = WeatherRepository()
        val viewModelProviderFactory = WeatherViewModelProviderFactory(weatherRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(WeatherViewModel::class.java)

        loginBtn.setOnClickListener {
            if (login.text.toString().equals("aligarmakov@gmail.com") && password.text.toString() == "Ali001") {

                viewModel.weatherItem.observe(this, Observer {
                    Timber.d("TAG_OBSERVE")
                    when(it) {
                        is Resource.Success -> {
                            it.data.let {
                                Timber.d("TAG_LIST: ${Gson().toJson(it)}")

                                val contextView: View = requireActivity().findViewById(android.R.id.content)

                                Snackbar.make(contextView, it?.name.toString() + " "
                                  +it?.weather.toString() + " " + it?.main?.temp.toString(), Snackbar.LENGTH_SHORT)
                                  .show()
                            }
                        }
                        is Resource.Error -> {
                            it.message?.let { message ->
                                Log.e(TAG, "An error occured: $message")
                            }
                        }
                    }
                })
            } else {
                Toast.makeText(requireActivity(), "error", Toast.LENGTH_SHORT).show()
            }
        }


    }

    companion object {
        fun newInstance(): AuthFragment = AuthFragment()
    }
}