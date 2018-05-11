# Arkkitehtuurikuvaus

## Rakenne
Ohjelma on jaettu kolmeen pakkaukseen:

- filmlogger.ui
- filmlogger.domain
- filmlogger.dao

Pakkaus **filmlogger.ui** pitää sisällään JavaFX:llä toteutetun käyttöliittymän ja siihen liittyvät FXML Controller -luokat. Pakkaus **filmlogger.domain** sisältää sovelluslogiikasta vastaavat luokat ja pakkaus **filmlogger.dao** puolestaan sisältää tiedon tallentamiseen ja hakemiseen liittyvät luokat.

## Käyttöliittymä
Käyttöliittymässä on neljä näkymää:
- sisäänkirjautuminen (*LoginScene*)
- rekisteröityminen (*RegisterScene*)
- logattujen elokuvien tarkastelu -näkymä (*LoggerScene*)
- arvioinnin lisäys -näkymä (*ReviewScene*)

Näkymät ovat omiia Scene-olioitaan, jotka ovat yksikerrallaan näkyvissä ohjelmassa eli asetettuna ohjelman stageen. Käyttöliittymä on rakennettu FXML-toteutuksena. Näkymiä vastaavat FXML-tiedostot on tallennettu ohjelman resources/fxml-kansioon (*/src/main/resources/fxml*). Näkymien toiminnallisuudesta vastaavat Controller-luokat ovat ohjelman filmlogger.ui -pakkauksessa.

Ohjelman sovelluslogiikka on pyritty erottamaan käyttöliittymästä ja käyttöliittymän toiminnot toteuttavat Controller-luokat kutsuvat ensisijaisesti sovelluslogiikasta vastaavan Logger-olion metodeja. Kirjautumisen yhteydessä LoggerSceneen päivitetään kirjautunutta käyttäjää vastaavat elokuvien tiedot, jotka ladataan tietokannasta uudelleen myös aina lisäysten ja muokkausten yhteydessä. 

## Sovelluslogiikka
Sovelluksen loogisen datamallin muodostavat luokat User, Film, Tag ja Review, jotka kuvaavat käyttäjien ja elokuvien välisiä suhteita:

Ohjelman toiminnallisuudesta vastaa keskeisesti *Logger*-olion ohjelmakohtainen instanssi, joka tarjoaa käyttöliittymän toiminnoille metodit kuten esim.:
- boolean login(String username)
- User getCurrentUser()
- void logout()
- String createUser(String user, String username)
- List<Review> getWatchlist()
- String addToWatchlist(String filmName, String filmYear)

Loggerilla on pääsy käyttäjiin, elokuviin, arvioihin ja tunnisteisiin filmlogger.dao-pakkauksessa DAO-rajapinnat toteuttavien luokkien kautta. Sovelluslogiikka eli Logger-instanssi saa tiedon luokista konstruktorinsa kautta. Loggerin suhdetta tallennuksesta vastaaviin luokkiin kuvataan alla olevassa kuvassa.

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/pakkausluokkakaavio.png?raw=true">

## Tietojen pysyväistallennus

Ohjelma käyttää tietojen tallentamiseen SQL-tietokantaa. Tietojen tallennuksesta tietokantaan ja hakemisesta tietokannasta vastaavat filmlogger.dao-pakkauksen luokat DbUserDAO, DbFilmDAO, DbTagDAO ja DbReviewDAO. Luokat noudattavat Data Access Object -suunnittelumallia ja kaikki luokat toteuttavat niitä vastaavan rajapinnan. Sovelluslogiikka ei näin ollen ole suoraan yhteydessä tallennuksesta vastaaviin luokkiin ja tallennuksen toteutus voidaan tarvittaessa muuttaa jos tietoa halutaankin tallentaa toisessa muodossa. 

## Päätoiminnallisuudet

### Elokuvan lisääminen watchlistille
<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/sk-addToWatchlist.png?raw=true">

### Elokuvan merkitseminen nähdyksi

### Elokuvalle arvion lisääminen
