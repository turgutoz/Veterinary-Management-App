package com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto

import java.time.LocalDateTime

class VeterinarianSaveDTO {
    var diplomaNo: Long = 0L
    var citizenId: String? = null
    var firstName: String? = null
    var middleName: String? = null
    var lastName: String? = null
    var birthDate: String? = null //LocalDateTime = LocalDateTime.of(1991, 2, 2, 0, 0)
    var registerDate: String? = null //LocalDateTime = LocalDateTime.now()
}