# TUMBibAPI

this API provides information about study room reservations from [www.ub.tum.de/arbeitsplatz-reservieren](https://www.ub.tum.de/arbeitsplatz-reservieren)

A live demo: [https://tumbibapi.joschas.page/forBib?bib=MI](https://tumbibapi.joschas.page/forBib?bib=MI)

## Endpoints 
`/forBib?bib={MI|SG|WS|CH|PH...}`

```
[
  {
    "bib": "Mathematik & Informatik",
    "from": "2020-12-05T10:00:00.000",
    "til": "2020-12-05T14:45:00.000",
    "reservationId": "-1"
  },
  {
    "bib": "Mathematik & Informatik",
    "from": "2020-12-06T10:00:00.000",
    "til": "2020-12-06T14:45:00.000",
    "reservationId": "1135096596"
  },
  ...
]
```

`/all`

```
[
  {
    "bib": "Stammgelände",
    "from": "2020-12-05T10:00:00.000",
    "til": "2020-12-05T14:45:00.000",
    "reservationId": "748186522"
  },
  {
    "bib": "Stammgelände",
    "from": "2020-12-05T15:00:00.000",
    "til": "2020-12-05T19:45:00.000",
    "reservationId": "-1"
  },
  ...
```
