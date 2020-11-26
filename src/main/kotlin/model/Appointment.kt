package model

data class Appointment(val bib: Bib, val from: String, val til: String, var reservationId: String? = null)
