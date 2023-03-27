package com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api

import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//TODO: http://161.97.141.113:50500/api/read/vets/monthyear?y=2023&m=1
//TODO: http://161.97.141.113:50500/api/read/vets/between/year?begin=1976&end=2021

interface IVeterinarianService {
    @GET("/api/read/vets/lastname")
    fun findByLastName(@Query("n")lastName: String) : Call<VeterinariansInfo>

    @GET("/api/read/vets/vet/diploma")
    fun findByDiplomaNo(@Query("no") diplomaNo: Long) : Call<VeterinarianInfo>

    @GET("/api/read/vets/count")
    fun count() : Call<CountInfo>

    @GET("/api/read/vets/monthyear")
    fun findByMonthYear(@Query("y") year: Int, @Query("m") month: Int) : Call<VeterinariansInfo>

    @GET("/api/read/vets/between/year")
    fun findByBetweenYear(@Query("begin") begin: Int, @Query("end") end: Int) : Call<VeterinariansInfoWithFullName>

    @POST("/api/update/vets/vet/save")
    fun save(@Body veterinarian: VeterinarianSave) : Call<VeterinarianSave>
}