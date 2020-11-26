package server.handlers

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import java.nio.charset.StandardCharsets

abstract class Handler: HttpHandler {

    protected fun send(code: Int , body: String, exchange: HttpExchange){
        exchange.sendResponseHeaders(code, body.toByteArray().size.toLong())
        val os = exchange.responseBody
        os.write(body.toByteArray(StandardCharsets.UTF_8))
        os.flush()
        os.close()
    }

}