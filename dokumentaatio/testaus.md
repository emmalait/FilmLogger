# Testausdokumentti

Ohjelmaa on testattu automatisoiduilla yksikkö- ja integraatiotesteillä JUnitin avulla ja järjestelmätasolla manuaalisilla testeillä.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka
Automatisoidut testit keskittyvät sovelluslogiikan toteuttavien filmlogger.domain-pakkauksessa olevien luokkien testaukseen. Sovelluslogiikan keskeisesti toteuttavaa Logger-luokkaa testaa LoggerTest. Testit käyttävät tallennukseen DAO-rajapintojen toteutuksia FakeUserDAO, FakeFilmDAO, FakeTagDAO ja FakeTagDAO. Sovelluslogiikan luokille Film ja Review on muutamia tetejä testaamaan mm. niiden toString- ja compareTo-metodeja.

### DAO-luokat
Ohjelman toiminnan kannalta keskeisimmän DAO-luokan ReviewDAO:n toimintaa on testattu tilapäisessä fakeFilmlogger.db-tietokannassa.

### Testauskattavuus
Ohjelman testauksen rivikattavuus on X% ja haarautumiskattavuus X%. Käyttöliittymäkerros on jätetty testien ulkopuolelle.

## Järjestelmätestaus

Ohjelman järjestelmätestaus on toteutettu manuaalisesti.

### Asennus

### Toiminnallisuudet
