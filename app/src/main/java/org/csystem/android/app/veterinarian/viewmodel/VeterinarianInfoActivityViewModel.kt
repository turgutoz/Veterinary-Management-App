package org.csystem.android.app.veterinarian.viewmodel

import org.csystem.android.app.veterinarian.VeterinarianInfoActivity


class VeterinarianInfoActivityViewModel(var activity: VeterinarianInfoActivity) {
    fun handleCountButton() = activity.countButtonClicked()
    fun handleFindByLastNameButton() = activity.findByLastNameButtonClicked()
    fun handleFindByMonthAndYearButton() = activity.findByMonthAndYearButtonClicked()
    fun handleFindByBetweenYearButton() = activity.findByBetweenYearClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}