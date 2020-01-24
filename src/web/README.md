# CTFLY  - Web
Si utilizza SpringBoot e Docker per fornire un container autosufficiente che puo' essere utilizzato virtualmente ovunque e costituisce il front-end ed il back-end di tutta la parte web ovvero il gestionale.

Requisiti
------------
- docker 
- docker-compose


Utilizzo
-----------
- Posizionarsi nella directory del progetto:

```$ cd web/```
- Inizializzare il daemon di docker:

```$ sudo dockerd```
- Avviare il container:

```$ sudo docker-compose up```
- Testare il front-end da un browser:

```$ surf localhost:8080```
- Per ristabilire i permessi dei files una volta terminato il test:

```$ sudo chown -R $(whoami) ../web/ && sudo chgrp -R users ../web/```
