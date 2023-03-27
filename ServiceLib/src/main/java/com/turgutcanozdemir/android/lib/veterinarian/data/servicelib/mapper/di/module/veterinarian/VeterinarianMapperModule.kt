package com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.di.module.veterinarian

import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.veterinarian.IVeterinarianMapper
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.veterinarian.VeterinarianMapper
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