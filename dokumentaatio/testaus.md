# Testausdokumentti

Ohjelmaa on testattu automatisoiduilla yksikkö- ja integraatiotesteillä JUnitin avulla ja järjestelmätasolla manuaalisilla testeillä.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka
Automatisoidut testit keskittyvät sovelluslogiikan toteuttavien filmlogger.domain-pakkauksessa olevien luokkien testaukseen. Sovelluslogiikan keskeisesti toteuttavaa Logger-luokkaa testaa LoggerTest. Testit käyttävät tallennukseen DAO-rajapintojen toteutuksia FakeUserDAO, FakeFilmDAO, FakeTagDAO ja FakeTagDAO. Sovelluslogiikan luokille Film ja Review on muutamia tetejä testaamaan mm. niiden toString- ja compareTo-metodeja.

### DAO-luokat
Ohjelman toiminnan kannalta keskeisimmän DAO-luokan ReviewDAO:n toimintaa on testattu tilapäisessä fakeFilmlogger.db-tietokannassa.

### Testauskattavuus
Ohjelman testauksen rivikattavuus on 84% ja haarautumiskattavuus 76%. Käyttöliittymäkerros on jätetty testien ulkopuolelle.

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/testaus.png?raw=true">

## Järjestelmätestaus

Ohjelman järjestelmätestaus on toteutettu manuaalisesti.

### Asennus
Ohjelma on asennettu käyttöohjeen mukaisesti mac OS- sekä Windows-käyttöjärjestelmillä. Ohjelmaa on testattu sekä niin että ohjelman käyttämä tietokanta on luotu käynnistyksen yhteydessä että silloin kun tietokanta on jo olemassa.

### Toiminnallisuudet
Toiminnallisuudet on testattu määrittelydokumentin ja käyttöohjeen perusteella ja kaikki niissä mainitut toiminnallisuudet on käyty läpi. Kaikkiin kenttiin on yritetty syöttää erilaisia syötteitä.
