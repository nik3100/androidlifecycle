package com.savvytech.tabapicalldemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Tab1Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    val formatter = SimpleDateFormat("HH:mm:ss.SS")
    val date = Date()
    val current = formatter.format(date)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("fragment1", "onAttach tab1 $current");
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.e("fragment1", "onCreate tab1 $current");
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.e("fragment1", "onCreateView tab1");
        val view =  inflater.inflate(R.layout.fragment_tab1, container, false)
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("fragment1", "onViewCreated tab1 $current")
        fetchAllPosts()

        lifecycleScope.launch {
            activity?.let {
                view.findViewById<TextView>(R.id.texttab1)?.setOnClickListener {
                    startActivity(Intent(requireContext(),SecondActivity::class.java))
                }
            }
        }


    }

    override fun onStart() {
        super.onStart()
        Log.e("fragment1", "onStart tab1 $current");
    }

    override fun onResume() {
        super.onResume()
        Log.e("fragment1", "onResume tab1 $current")

    }

    override fun onPause() {
        super.onPause()
        Log.e("fragment1", "onPause tab1 $current");

    }

    override fun onStop() {
        super.onStop()
        Log.e("fragment1", "onStop tab1 $current");

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("fragment1", "onDestroyView tab1 $current");

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("fragment1", "onDestroy tab1 $current");

    }

    override fun onDetach() {
        super.onDetach()
        Log.e("fragment1", "onDetach tab1 $current");

    }

    private var apiInterface:ApiInterface?=null

    init {
        Log.e("fragment1", "init tab1 $current");
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllPosts(): LiveData<List<PostModel>> {
        Log.e("fragment1", "onViewCreated tab1 fetchAllPosts ---- API CALL $current")
        val data = MutableLiveData<List<PostModel>>()

        apiInterface?.fetchAllPosts()?.enqueue(object : Callback<List<PostModel>>{

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                data.value = emptyList()
            }

            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res as List<PostModel>
                }else{
                    data.value = emptyList()
                }
            }
        })
        return data
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}