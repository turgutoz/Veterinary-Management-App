package com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.di.module

import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.IVeterinarianMapper
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.VeterinarianMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class VeterinarianMapperModule {
    @Binds
    abstract fun bindVeterinarianMapper(veterinarianMapper: VeterinarianMapper): IVeterinarianMapper
}