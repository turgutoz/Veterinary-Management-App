package com.turgutcanozdemir.android.library.repo.veterinarian.di.hilt.module

import com.turgutcanozdemir.android.library.repo.veterinarian.repository.IVeterinarianQueryInfoRepository
import com.turgutcanozdemir.android.library.repo.veterinarian.repository.IVeterinarianRepository
import com.turgutcanozdemir.android.library.repo.veterinarian.repository.VeterinarianQueryInfoRepository
import com.turgutcanozdemir.android.library.repo.veterinarian.repository.VeterinarianRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VeterinarianModule {
    @Binds
    @Singleton
    abstract fun bindVeterinarianQueryInfoRepository(veterinarianQueryInfoRepository: VeterinarianQueryInfoRepository): IVeterinarianQueryInfoRepository

    @Binds
    @Singleton
    abstract fun bindVeterinarianRepository(veterinarianRepository: VeterinarianRepository) : IVeterinarianRepository
}