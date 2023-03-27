package com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.veterinarian

import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianDTO
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianSaveDTO
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfo
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.Veterinarian
import javax.inject.Inject

class VeterinarianMapper @Inject constructor(): IVeterinarianMapper {
    override fun toVeterinarianDTO(veterinarian: Veterinarian): VeterinarianDTO {
        return VeterinarianDTO().apply {
            diplomaNo = veterinarian.diplomaNo
            citizenId = veterinarian.citizenId
            firstName = veterinarian.firstName
            middleName = veterinarian.middleName
            lastName = veterinarian.lastName
            birthDate = veterinarian.birthDate
            registerDate = veterinarian.registerDate
        }
    }

    override fun toVeterinarianSaveDTO(veterinarianInfo: VeterinarianInfo): VeterinarianSaveDTO {
        return VeterinarianSaveDTO().apply {
            diplomaNo = veterinarianInfo.diplomaNo
            citizenId = veterinarianInfo.citizenId
            firstName = veterinarianInfo.firstName
            middleName = veterinarianInfo.middleName
            lastName = veterinarianInfo.lastName
            birthDate = veterinarianInfo.birthDate
            registerDate = veterinarianInfo.registerDate
        }
    }
}