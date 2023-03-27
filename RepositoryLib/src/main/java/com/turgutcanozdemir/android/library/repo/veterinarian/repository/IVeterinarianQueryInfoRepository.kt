package com.turgutcanozdemir.android.library.repo.veterinarian.repository

import com.karandev.util.data.repository.ICrudRepository
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.VeterinarianQueryInfo

interface IVeterinarianQueryInfoRepository : ICrudRepository<VeterinarianQueryInfo, Long>  {
    fun findByDiplomaNo(diplomaNo: Long) : VeterinarianQueryInfo?
}
