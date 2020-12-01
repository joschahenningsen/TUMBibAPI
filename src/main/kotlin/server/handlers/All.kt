package server.handlers

import com.sun.net.httpserver.HttpExchange
import server.APIServer

class All : Handler() {
    override fun handle(exchange: HttpExchange?) {
        val response = g.toJson(APIServer.appointments)
        exchange!!.responseHeaders.add("Content-type", "application/json; charset=UTF-8")
        send(200, response, exchange)
    }

}
