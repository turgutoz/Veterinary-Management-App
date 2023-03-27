package com.turgutcanozdemir.android.lib.veterinarian.data.servicelib

import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianDTO
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto.VeterinarianSaveDTO
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.IVeterinarianMapper
import com.turgutcanozdemir.android.library.repo.veterinarian.dal.VeterinarianAppHelper
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.VeterinarianQueryInfo
import javax.inject.Inject

class VeterinarianAppService @Inject constructor(){
    @Inject
    lateinit var veterinarianAppHelper: VeterinarianAppHelper

    @Inject
    lateinit var veterinarianMapper: IVeterinarianMapper

    fun saveVeterinarian(veterinarianSaveDTOs: List<VeterinarianSaveDTO>) : Boolean
    {
        try {
            if(veterinarianSaveDTOs.isEmpty())
                return false

            val diplomaNo = veterinarianSaveDTOs[0].diplomaNo
            val list = veterinarianSaveDTOs.map { veterinarianMapper.toVeterinarian(it) }.toList()

            return veterinarianAppHelper.saveVeterinarian(VeterinarianQueryInfo(diplomaNo), list)
        }
        catch (ex: RepositoryException) {
            throw DataServiceException("VeterinarianAppService.saveVeterinarian", ex.cause)
        }
    }

    fun findVeterinariansByDiplomaNo(diplomaNo: Long): MutableIterable<VeterinarianDTO>
    {
        try {
            return veterinarianAppHelper.findVeterinariansByDiplomaNo(diplomaNo).map { veterinarianMapper.toVeterinarianDTO(it) }.toMutableList()
        }
        catch (ex: RepositoryException) {
            throw DataServiceException("VeterinarianAppService.findVeterinariansByDiplomaNo", ex.cause)
        }
    }
}