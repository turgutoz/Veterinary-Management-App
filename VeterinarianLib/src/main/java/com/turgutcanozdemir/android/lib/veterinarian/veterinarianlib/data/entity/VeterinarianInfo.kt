package com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity

import java.io.Serializable

data class VeterinarianInfo(
                        var diplomaNo: Long,
                        var citizenId: String?,
                        var firstName: String?,
                        var middleName: String?,
                        var lastName: String?,
                        var birthDate: String?,
                        var registerDate: String?
) :Serializable {
    override fun toString() = when {
        middleName != null -> "$firstName $middleName ${lastName?.uppercase()}"
        else -> "$firstName ${lastName?.uppercase()}"
    }
}