package com.savvytech.tabapicalldemo

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun fetchAllPosts(): Call<List<PostModel>>
}