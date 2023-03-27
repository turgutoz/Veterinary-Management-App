package com.turgutcanozdemir.android.library.repo.veterinarian.dal

import com.turgutcanozdemir.android.library.repo.veterinarian.entity.Veterinarian
import com.turgutcanozdemir.android.library.repo.veterinarian.repository.IVeterinarianQueryInfoRepository
import com.turgutcanozdemir.android.library.repo.veterinarian.repository.IVeterinarianRepository
import javax.inject.Inject
import com.karandev.util.data.repository.exception.RepositoryException
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.VeterinarianQueryInfo
import java.sql.SQLException

class VeterinarianAppHelper @Inject constructor() {
    @Inject
    lateinit var veterinarianRepository: IVeterinarianRepository

    @Inject
    lateinit var veterinarianQueryInfoRepository: IVeterinarianQueryInfoRepository

    fun findVeterinariansByDiplomaNo(diplomaNo: Long) : MutableIterable<Veterinarian>
    {
        try {
            return veterinarianRepository.findByDiplomaNo(diplomaNo)
        }
        catch (ex: Exception) {
            throw RepositoryException("VeterinarianAppHelper.findVeterinariansByDiplomaNo", ex)
        }
    }

    fun saveVeterinarian(veterinarianQueryInfo: VeterinarianQueryInfo, veterinarians: Iterable<Veterinarian>) : Boolean {
        try {
            if (veterinarianQueryInfoRepository.existsById(veterinarianQueryInfo.diplomaNo))
                return false

        veterinarianQueryInfoRepository.save(veterinarianQueryInfo)
        veterinarians.forEach { veterinarianRepository.save(it) }

        return true
        }
        catch (ex: SQLException) {
            throw RepositoryException("VeterinarianAppHelper.saveVeterinarian", ex)
        }
    }

    //...
}