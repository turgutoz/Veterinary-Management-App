package com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.veterinarian

import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianDTO
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianSaveDTO
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfo
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.Veterinarian

interface IVeterinarianMapper {
    fun toVeterinarianDTO(veterinarian: Veterinarian) : VeterinarianDTO
    fun toVeterinarianSaveDTO(veterinarian: VeterinarianInfo) : VeterinarianSaveDTO
}