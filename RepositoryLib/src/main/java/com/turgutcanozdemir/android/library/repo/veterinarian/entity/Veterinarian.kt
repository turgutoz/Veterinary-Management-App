package com.turgutcanozdemir.android.library.repo.veterinarian.entity

import java.time.LocalDateTime

class Veterinarian {
    var diplomaNo = 0L
    var citizenId: String? = null
    var firstName: String? = null
    var middleName: String? = null
    var lastName: String? = null
    var birthDate: LocalDateTime = LocalDateTime.of(1991, 2, 2, 0, 0)
    var registerDate: LocalDateTime = LocalDateTime.now()
}

/*
  veterinarian_id INTEGER primary key AUTOINCREMENT,
        diploma_no INTEGER not null,
        citizen_id TEXT not null,
        first_name TEXT not null,
        middle_name TEXT,
        last_name TEXT,
        birth_date INTEGER not null,
        register_date INTEGER not null,
 */