package org.csystem.android.app.veterinarian.viewmodel

import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfoWithFullName
import org.csystem.android.app.veterinarian.VeterinarianFindByBetweenYearActivity
import org.csystem.android.app.veterinarian.viewmodel.adapter.VeterinarianListAdapter

data class VeterinarianFindByBetweenYearActivityViewModel(
    var activity: VeterinarianFindByBetweenYearActivity,
    var begin: Int = 0,
    var end: Int = 0,
    var adapter: VeterinarianListAdapter<VeterinarianInfoWithFullName> = VeterinarianListAdapter(activity)
) {
    fun handleFindButton() = activity.findButtonClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}