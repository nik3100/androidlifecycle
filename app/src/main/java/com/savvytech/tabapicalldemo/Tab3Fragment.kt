package com.savvytech.tabapicalldemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
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

class Tab3Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    val formatter = SimpleDateFormat("HH:mm:ss.SS")
    val date = Date()
    val current = formatter.format(date)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("fragment3", "onAttach tab3 $current");
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.e("fragment3", "onCreate tab3 $current");

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.e("fragment3", "onCreateView tab3 $current");
        return inflater.inflate(R.layout.fragment_tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("fragment3", "onViewCreated tab3 $current")
        fetchAllPosts()

        lifecycleScope.launch {
            activity?.let {
                view.findViewById<TextView>(R.id.texttab3)?.setOnClickListener {
                    startActivity(Intent(requireContext(),SecondActivity::class.java))
                }
            }
        }

    }

    private var apiInterface:ApiInterface?=null

    init {
        Log.e("fragment3", "init tab3 $current");
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllPosts(): LiveData<List<PostModel>> {
        Log.e("fragment3", "onViewCreated tab3 fetchAllPosts ---- API CALL $current")
        val data = MutableLiveData<List<PostModel>>()

        apiInterface?.fetchAllPosts()?.enqueue(object : Callback<List<PostModel>> {

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

    override fun onStart() {
        super.onStart()
        Log.e("fragment3", "onStart tab3 $current");
    }

    override fun onResume() {
        super.onResume()
        Log.e("fragment3", "onResume tab3 $current")

    }

    override fun onPause() {
        super.onPause()
        Log.e("fragment3", "onPause tab3 $current");

    }

    override fun onStop() {
        super.onStop()
        Log.e("fragment3", "onStop tab3 $current");

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("fragment3", "onDestroyView tab3 $current");

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("fragment3", "onDestroy tab3 $current");

    }

    override fun onDetach() {
        super.onDetach()
        Log.e("fragment3", "onDetach tab3 $current");

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab3Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}