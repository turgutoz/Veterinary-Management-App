package org.csystem.android.app.veterinarian.viewmodel

import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfoWithFullName
import org.csystem.android.app.veterinarian.VeterinarianDetailsWithFullNameActivity

class VeterinarianDetailsWithFullNameActivityViewModel(var activity: VeterinarianDetailsWithFullNameActivity)
{
    var veterinarianInfoWithFullName: VeterinarianInfoWithFullName = VeterinarianInfoWithFullName(0, "", "", "")
}