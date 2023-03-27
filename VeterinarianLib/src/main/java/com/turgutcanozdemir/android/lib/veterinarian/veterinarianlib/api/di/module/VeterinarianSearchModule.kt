package com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.di.module

import com.karandev.util.retrofit.RetrofitUtil
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.GET_SERVICE_BASE_URL
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.IVeterinarianService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class VeterinarianServiceModule @Inject constructor(){
    @Provides
    fun provideVeterinarianSearch() : IVeterinarianService = RetrofitUtil.createRetrofitWithLogging(GET_SERVICE_BASE_URL)
        .create(IVeterinarianService::class.java)
}