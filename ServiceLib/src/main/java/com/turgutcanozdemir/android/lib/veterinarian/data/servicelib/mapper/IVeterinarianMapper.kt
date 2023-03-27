package com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper

import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianDTO
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianSaveDTO
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.Veterinarian

interface IVeterinarianMapper {
    fun toVeterinarian(veterinarianSaveDTO: VeterinarianSaveDTO) : Veterinarian
    fun toVeterinarianDTO(veterinarian: Veterinarian) : VeterinarianDTO
  //  fun toVeterinarianDTO(veterinarian: VeterinarianJavaService) : VeterinarianDTO
}