package server.handlers

import com.sun.net.httpserver.HttpExchange
import server.APIServer
import server.Helpers
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ForDay: Handler() {
    override fun handle(exchange: HttpExchange?) {
        val params: Map<String?, String?> = Helpers.queryToMap(exchange!!.requestURI.query)
        val date : LocalDate = LocalDate.parse(params["day"], formatter)
        val response = g.toJson(APIServer.appointments.filter { LocalDateTime.parse(it.from, germanDTformatter).toLocalDate().isEqual(date) }.toList())
        exchange.responseHeaders.add("Content-type", "application/json; charset=UTF-8")
        send(200, response, exchange)
    }

    companion object{
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val germanDTformatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, d. MMMM yyyy HH:mm", Locale.GERMAN)
    }
}
