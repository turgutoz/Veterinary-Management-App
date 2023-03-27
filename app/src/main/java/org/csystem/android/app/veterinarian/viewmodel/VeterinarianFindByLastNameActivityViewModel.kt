package org.csystem.android.app.veterinarian.viewmodel

import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfo
import org.csystem.android.app.veterinarian.VeterinarianFindByLastNameActivity
import org.csystem.android.app.veterinarian.viewmodel.adapter.VeterinarianListAdapter

data class VeterinarianFindByLastNameActivityViewModel(
    var activity: VeterinarianFindByLastNameActivity,
    var text: String = "",
    var adapter: VeterinarianListAdapter<VeterinarianInfo> = VeterinarianListAdapter(activity)
) {
    fun handleFindButton() = activity.findButtonClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}