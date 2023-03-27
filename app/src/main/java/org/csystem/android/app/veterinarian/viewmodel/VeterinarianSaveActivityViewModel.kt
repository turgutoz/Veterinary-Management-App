package org.csystem.android.app.veterinarian.viewmodel

import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianSave
import org.csystem.android.app.veterinarian.VeterinarianSaveActivity

class VeterinarianSaveActivityViewModel(var activity: VeterinarianSaveActivity,  var veterinarianSave: VeterinarianSave? = VeterinarianSave()) {
    fun handleSaveButton() = activity.saveButtonClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}