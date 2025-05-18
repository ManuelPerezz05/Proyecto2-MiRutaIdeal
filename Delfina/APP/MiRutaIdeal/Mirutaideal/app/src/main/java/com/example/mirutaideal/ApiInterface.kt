package com.example.mirutaideal

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("rutas")
    fun getRutas(): Call<List<Ruta>>

    @POST("routes")
    fun addRuta(@Body newRoute: Ruta): Call<Ruta>

    @GET("routes/{id}")
    fun getRouteById(@Path("id") id: Int): Call<Ruta>

    @POST("register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<UserResponse>

    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>


}