## Tehtävä 1

```mermaid
classDiagram
    Monopoli "1" --> "2" Noppa
    Monopoli "1" --> "2..8" Pelaaja
    Monopoli "1" --> "1" Pelilauta

    Pelilauta --> "40" Ruutu
    Pelaaja --> "1" Pelinappula
    Pelinappula --> "1" Ruutu
    Ruutu --> "1" Ruutu
```