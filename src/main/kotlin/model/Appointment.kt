package model

import org.joda.time.LocalDateTime

data class Appointment(val bib: Bib, val from: String, val til: String, var reservationId: String? = null)
