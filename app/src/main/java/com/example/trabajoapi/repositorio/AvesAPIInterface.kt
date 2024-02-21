package com.example.AvesApi.repositorio

import com.example.trabajoapi.repositorio.Ave
import com.example.trabajoapi.repositorio.Habitat
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://192.168.56.1:3060"

interface AvesAPIInterface {
    @GET("/aves")
    @Headers("Accept: application/json")
    suspend fun getAllAves(): Response<List<Ave>>

    @GET("/aves/{id}")
    @Headers("Accept: application/json")
    suspend fun getAveById(@Path("id") id: Int): Response<Ave>

    @GET("/aves/filtro")
    @Headers("Accept: application/json")
    suspend fun getAvesByNombreAndHabitat(
        @Query("nombre") nombre: String,
        @Query("habitat") habitat: String
    ): Response<List<Ave>>

    @POST("/aves")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun createAve(@Body ave: Ave): Response<Ave>

    @PUT("/aves/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun updateAve(@Path("id") id: Int, @Body newAve: Ave): Response<Ave>

    @PUT("/aves/{id}/habitat")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun updateHabitat(@Path("id") id: Int, @Body newHabitat: Habitat): Response<Ave>

    @GET("/habitats")
    @Headers("Accept: application/json")
    suspend fun getAllHabitats(): Response<List<Habitat>>

    @GET("/habitats/{id}")
    @Headers("Accept: application/json")
    suspend fun getHabitatById(@Path("id") id: Int): Response<Habitat>

    @POST("/habitats")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun createHabitat(@Body habitat: Habitat): Response<Habitat>

    @DELETE("/habitats/{id}")
    @Headers("Accept: application/json")
    suspend fun deleteHabitat(@Path("id") id: Int): Response<Unit>
}

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
object AvesApi {
    val retrofitService: AvesAPIInterface by lazy {
        retrofit.create(AvesAPIInterface::class.java)
    }
}
