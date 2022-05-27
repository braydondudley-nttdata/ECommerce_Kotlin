package com.ecommerce_app.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ecommerce_app.databinding.ActivityProfileBinding
import com.ecommerce_app.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Profile: Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ActivityProfileBinding.inflate(inflater, container, false)
        binding.viewModel=viewModel
        binding.lifecycleOwner = this
        setInitialValues(viewModel, context)
        setupObservers(viewModel)
        return binding.root
    }

    private fun setupObservers(viewModel: MainViewModel) {
        viewModel.name.observe(viewLifecycleOwner, {
            if (!it.isNullOrBlank()) {
                viewModel.writeToSharedPref("name", it)
            }
        })
        viewModel.lastName.observe(viewLifecycleOwner, {
            if (!it.isNullOrBlank()) {
                viewModel.writeToSharedPref("lastname",it)
            }
        })
    }

    private fun setInitialValues(
        viewmodel: MainViewModel,
        context: Context?
    ) {
        val sharedPreference = viewmodel.repo.getSharedPref()
        viewmodel.name.value = sharedPreference?.getString("name","First name")
        viewmodel.lastName.value = sharedPreference?.getString("lastname","Last name")
    }
}