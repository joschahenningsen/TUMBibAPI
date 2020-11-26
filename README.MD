# TUMBibAPI

this API provides information about study room reservations from [www.ub.tum.de/arbeitsplatz-reservieren](https://www.ub.tum.de/arbeitsplatz-reservieren)

## Endpoints 
`:8080/forBib?bib={MI|SG|WS|CH|PH...}`

```
[
  {
    "bib": "Mathematik & Informatik",
    "from": "Freitag, 27. November 2020:8:00",
    "til": "Freitag, 27. November 2020:15:45",
    "reservationId": "-1"
  },
  {
    "bib": "Mathematik & Informatik",
    "from": "Freitag, 27. November 2020:16:00",
    "til": "Freitag, 27. November 2020:23:45",
    "reservationId": "1135096596"
  },
  ...
]
```