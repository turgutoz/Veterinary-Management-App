package com.turgutcanozdemir.android.library.repo.veterinarian.entity

import java.time.LocalDateTime

class VeterinarianQueryInfo(
    var diplomaNo : Long = 0L,
    var queryCount: Long = 1,
    var queryDateTime : LocalDateTime = LocalDateTime.now(),
    var saveDateTime: LocalDateTime = LocalDateTime.now(),
    var sourceService: String = "Java Animal Hospital Service")

/*
        diploma_no INTEGER,
        query_count INTEGER default(1) not null,
        query_date_time INTEGER not null,
        save_date_time INTEGER not null,
        source_service TEXT not null,
 */