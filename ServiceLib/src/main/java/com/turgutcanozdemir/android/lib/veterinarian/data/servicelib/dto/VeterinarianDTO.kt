package com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.dto

import java.time.LocalDateTime

class VeterinarianDTO {
    var diplomaNo = 0L
    var citizenId: String? = null
    var firstName: String? = null
    var middleName: String? = null
    var lastName: String? = null
    var birthDate: LocalDateTime = LocalDateTime.of(1991, 2, 2, 0, 0)
    var registerDate: LocalDateTime = LocalDateTime.now()
}