package server

import model.Appointment
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.jsoup.Jsoup
import server.handlers.Handler
import java.lang.NullPointerException
import java.time.format.DateTimeFormatter
import java.util.*

class TUMAPIFetcher : Thread() {

    override fun run() {
        while (true) {
            update()
            // update every 5 minutes
            sleep(1000 * 60 * 5)
        }
    }

    private fun update() {
        val appointments: ArrayList<Appointment> = ArrayList()
        for (i in 0 until 10) {
            val call = Jsoup.connect("https://www.ub.tum.de/arbeitsplatz-reservieren?field_teilbibliothek_tid_selective=All&field_tag_value_selective=All&page=$i")
                    .execute()
            if (call.statusCode() != 200) {
                break
            }
            val response = call.parse()
            try {
                response.getElementsByClass("view-resevierungen-lesesaal")!!.last()
                        .getElementsByTag("tbody").last()
                        .getElementsByTag("tr").forEach { tableRow ->
                            val bib = Helpers.parseBib(tableRow.child(0).text())
                            val date = tableRow.child(1).text()
                            val times = tableRow.child(2).text().split(" – ")
                            val from = LocalDateTime.parse("$date ${times[0]}", dtf)
                            val til = LocalDateTime.parse("$date ${times[1]}", dtf)
                            val reservationId =
                                    if (tableRow.child(3).text() == "ausgebucht") {
                                        "-1"
                                    } else {
                                        tableRow.child(3).getElementsByTag("a").attr("href").replace("/reserve/", "")
                                    }
                            appointments.add(Appointment(bib, Handler.fmt.print(from), Handler.fmt.print(til), reservationId))
                        }
            } catch (e: Exception) {
                //page i-1 was the last page.
                if (e is NullPointerException) {
                    println("stopping at page $i")
                    synchronized(APIServer.appointments) {
                        APIServer.appointments = appointments
                    }
                    println("fetched ${appointments.size} entries")
                    break
                }else {
                    e.printStackTrace()
                    break
                }
            }
        }
    }
    companion object {
        val dtf = DateTimeFormat.forPattern("EEEE, d. MMMM yyyy H:mm").withLocale(Locale.GERMAN)
    }
}
