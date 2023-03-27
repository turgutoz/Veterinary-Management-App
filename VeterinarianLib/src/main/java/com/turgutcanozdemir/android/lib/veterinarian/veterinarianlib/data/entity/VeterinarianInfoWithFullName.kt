package com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity

import java.io.Serializable


data class VeterinarianInfoWithFullName(
    var diplomaNo: Long,
    var citizenId: String?,
    var fullName: String?,
    var registerDate: String?
) : Serializable
{
    override fun toString() = "$fullName ($registerDate)"
}