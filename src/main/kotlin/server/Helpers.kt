package server

import model.Bib
import java.util.*


class Helpers {
    companion object {
        fun queryToMap(query: String?): MutableMap<String?, String?> {
            val result: MutableMap<String?, String?> =
                HashMap()
            for (param in query!!.split("&").toTypedArray()) {
                val entry: Array<String?> = param.split("=").toTypedArray()
                if (entry.size > 1) {
                    result[entry[0]] = entry[1]
                } else {
                    result[entry[0]] = ""
                }
            }
            return result
        }

        fun parseBib(bibStr: String?): Bib {
            return when (bibStr) {
                "CH" -> Bib.Chemie
                "Chemie" -> Bib.Chemie
                "MW" -> Bib.Maschinenwesen
                "Maschinenwesen" -> Bib.Maschinenwesen
                "MI" -> Bib.MI
                "Mathematik & Informatik" -> Bib.MI
                "ME" -> Bib.Medizin
                "Medizin" -> Bib.Medizin
                "PH" -> Bib.Physik
                "Physik" -> Bib.Physik
                "SP" -> Bib.Sport
                "Sport & Gesundheitswissenschaften" -> Bib.Sport
                "SG" -> Bib.Stammgelaende
                "Stammgelände" -> Bib.Stammgelaende
                "ST" -> Bib.Straubing
                "Straubing" -> Bib.Straubing
                "WS" -> Bib.Weihenstephan
                "Weihenstephan" -> Bib.Weihenstephan
                else -> Bib.Unknown
            }
        }
    }
}