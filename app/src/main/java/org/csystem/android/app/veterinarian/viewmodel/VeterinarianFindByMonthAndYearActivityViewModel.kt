package org.csystem.android.app.veterinarian.viewmodel

import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfo
import org.csystem.android.app.veterinarian.VeterinarianFindByMonthAndYearActivity
import org.csystem.android.app.veterinarian.viewmodel.adapter.VeterinarianListAdapter

data class VeterinarianFindByMonthAndYearActivityViewModel(
    var activity: VeterinarianFindByMonthAndYearActivity,
    var month: Int = 0,
    var year: Int = 0,
    var adapter: VeterinarianListAdapter<VeterinarianInfo> = VeterinarianListAdapter(activity)
) {
    fun handleFindButton() = activity.findButtonClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}