package com.example.tmbd_api

import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.example.tmbd_api.list.TVListViewModel
import kotlinx.android.synthetic.main.fragment_recycler_view.*

class RecyclerViewFragment : Fragment() {
    private lateinit var viewModel: TVListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProviders.of(this).get(TVListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(tv_shows) {
            adapter = TVAdapter {
                findNavController().navigate(RecyclerViewFragmentDirections.actionRecyclerViewFragmentToDetailFragment(it))

            }

        }
        viewModel.tvs.observe(viewLifecycleOwner, Observer {
            (tv_shows.adapter as TVAdapter).submitList(it)
            if (it.isEmpty()){
                viewModel.fetchFromNetwork()
            }
        })
        viewModel.loadingStatus.observe(viewLifecycleOwner, Observer {loadingStatus->
            when{
                loadingStatus?.status==Status.LOADING->{
                    progressBar.visibility=View.VISIBLE
                    status_error.visibility=View.INVISIBLE
                }
                loadingStatus?.status==Status.SUCCESS->{
                    progressBar.visibility=View.INVISIBLE
                    status_error.visibility=View.INVISIBLE

                }
                loadingStatus?.status==Status.ERROR->{
                    progressBar.visibility=View.INVISIBLE
                    showErrorMessage(loadingStatus.errorCode,loadingStatus.message)
                    status_error.visibility=View.VISIBLE
                }
            }
        })

    }
    private fun showErrorMessage(errorCode: ErrorCode?,message: String?){
        when (errorCode){
            ErrorCode.NO_DATA->status_error.text="No Data return from server.Please try again"
            ErrorCode.NETWORK_ERROR->status_error.text="Error fetching data.Please check network"
            ErrorCode.UNKNOWN_ERROR->status_error.text="Unknown error $message"
        }
    }
}