package com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper

import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianDTO
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianSaveDTO
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.Veterinarian
import javax.inject.Inject

class VeterinarianMapper @Inject constructor() : IVeterinarianMapper {
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

    override fun toVeterinarian(veterinarianSaveDTO: VeterinarianSaveDTO): Veterinarian {
        return Veterinarian().apply {
            diplomaNo = veterinarianSaveDTO.diplomaNo
            citizenId = veterinarianSaveDTO.citizenId
            firstName = veterinarianSaveDTO.firstName
            middleName = veterinarianSaveDTO.middleName
            lastName = veterinarianSaveDTO.lastName
         //   birthDate = veterinarianSaveDTO.birthDate
          //  registerDate = veterinarianSaveDTO.registerDate
        }
    }
        //...
}