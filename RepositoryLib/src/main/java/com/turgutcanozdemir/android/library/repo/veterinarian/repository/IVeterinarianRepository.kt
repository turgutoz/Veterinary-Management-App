package com.turgutcanozdemir.android.library.repo.veterinarian.repository

import com.karandev.util.data.repository.ICrudRepository
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.Veterinarian

interface IVeterinarianRepository : ICrudRepository<Veterinarian, Long> {
    fun findByDiplomaNo(diplomaNo: Long) : MutableIterable<Veterinarian>
}