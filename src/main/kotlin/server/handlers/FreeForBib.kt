package server.handlers

import server.Helpers.Companion.queryToMap
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sun.net.httpserver.HttpExchange
import model.Bib
import server.APIServer
import server.Helpers

class FreeForBib : Handler() {
    override fun handle(exchange: HttpExchange?) {
        val params: Map<String?, String?> = queryToMap(exchange!!.requestURI.query)
        val bib: Bib = Helpers.parseBib(params["bib"])
        if (params["bib"] == null || bib == Bib.Unknown) {
            send(400, "bad request", exchange)
        } else {
            val response = g.toJson(APIServer.appointments.filter { it.bib == bib }.toList())
            exchange.responseHeaders.add("Content-type", "application/json; charset=UTF-8")
            send(200, response, exchange)
        }
    }

    companion object {
        val g: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    }

}
