package org.csystem.android.app.veterinarian.viewmodel

import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfo
import org.csystem.android.app.veterinarian.VeterinarianDetailsActivity

class VeterinarianDetailsActivityViewModel(var activity: VeterinarianDetailsActivity)
{
    var veterinarianInfo: VeterinarianInfo = VeterinarianInfo(0, "", "", "", "", "", "")
}