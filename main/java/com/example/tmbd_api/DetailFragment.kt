package com.example.tmbd_api

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.tmbd_api.data.network.TmdbService
import com.example.tmbd_api.detail.TVDetailViewModel
import com.example.tmbd_api.detail.TVDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.gridview.*
import kotlinx.android.synthetic.main.gridview.frontpostero
import kotlinx.android.synthetic.main.gridview.tv_show_name


class DetailFragment : Fragment() {
    private lateinit var viewModel: TVDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id:Long =DetailFragmentArgs.fromBundle(requireArguments()).id
        val viewModelFactory= TVDetailViewModelFactory(id,activity!!.application)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(TVDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.tv.observe(viewLifecycleOwner, Observer {
            setData(it)
        })
    }
    private fun setData(tv: TV){
        Glide.with(activity!!).load(TmdbService.POSTER_BASE_URL + tv.posterPath).error(R.drawable.poster_placeholder).into(frontpostero)
        Glide.with(activity!!).load(TmdbService.BACKDROP_BASE_URL +tv.backdropPath).into(backgroundposter)
        tv_show_name.text=tv.name
        tv_show_date.text=tv.firstAirDate.readableFormat()
        tv_show_description.text=tv.overView
    }
}