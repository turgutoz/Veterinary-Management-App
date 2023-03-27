package org.csystem.android.app.veterinarian.configuration.inject.module

import com.karandev.util.retrofit.RetrofitUtil
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.GET_SERVICE_BASE_URL
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.IVeterinarianService
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.POST_SERVICE_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.veterinarian.configuration.inject.annotation.service.GetServiceAuthInterceptor
import org.csystem.android.app.veterinarian.configuration.inject.annotation.service.PostServiceAuthInterceptor

@Module
@InstallIn(ActivityComponent::class)
object VeterinarianServiceConfig {

    @GetServiceAuthInterceptor
    @Provides
    fun createVeterinarianGetService(): IVeterinarianService = RetrofitUtil.createRetrofit(
        GET_SERVICE_BASE_URL
    ).create(IVeterinarianService::class.java)

    @PostServiceAuthInterceptor
    @Provides
    fun createVeterinarianPostService(): IVeterinarianService = RetrofitUtil.createRetrofit(
        POST_SERVICE_BASE_URL
    ).create(IVeterinarianService::class.java)
}