package com.ameen.weatherphoto.presentation.fragment.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.weatherphoto.databinding.FragmentHistoryBinding
import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData
import com.ameen.weatherphoto.presentation.adapter.WeatherPhotoHistoryAdapter
import com.ameen.weatherphoto.presentation.listener.WeatherHistoryItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(), WeatherHistoryItemClickListener {

    //@Inject
    //lateinit var database: WeatherAppDataBase

    private lateinit var recyclerAdapter: WeatherPhotoHistoryAdapter

    private val historyViewModel: HistoryViewModel by viewModels()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentHistoryBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initRecycler()
        return binding.root
    }

    private fun initRecycler() {

        if (!this::recyclerAdapter.isInitialized) {
            recyclerAdapter = WeatherPhotoHistoryAdapter(this)

            binding.historyImagesRecycler.apply {
                adapter = recyclerAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                hasFixedSize()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHistoryData()
    }

    private fun getHistoryData() {

        historyViewModel.getAllWeatherPhotoHistory()
        historyViewModel.historyWeatherData.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.diff.submitList(it)
        })

    }

    private val TAG = "HistoryFragment"
    override fun onWeatherHistoryItemClicked(selectedPhoto: WeatherPhotoHistoryData) {
        Log.e(TAG, "onWeatherHistoryItemClicked: $selectedPhoto")
        val action = HistoryFragmentDirections.actionHistoryFragmentToDetailsFragment(selectedPhoto)
        findNavController().navigate(action)
    }


}