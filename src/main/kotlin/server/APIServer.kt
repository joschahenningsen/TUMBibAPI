package server

import com.sun.net.httpserver.HttpServer
import model.Appointment
import server.handlers.All
import server.handlers.ForBib
import java.net.InetSocketAddress
import java.util.*
import java.util.concurrent.Executors

class APIServer {
    private var server: HttpServer = HttpServer.create(InetSocketAddress(8080), 0)

    init {
        TUMAPIFetcher().start()
        server.createContext("/forBib", ForBib())
        server.createContext("/all", All())
        server.executor = Executors.newFixedThreadPool(10)
        server.start()
    }

    companion object {
        var appointments: ArrayList<Appointment> = ArrayList()
    }
}

