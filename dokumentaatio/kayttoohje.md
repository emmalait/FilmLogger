# Käyttöohje

## Lataaminen
Lataa tiedosto filmlogger.jar.

## Käynnistäminen
Ohjelma käynnistetään tiedoston sijainnissa komentoriviltä komennolla

```
java -jar filmlogger.jar
```
## Sisäänkirjautuminen

Ohjelma avaa kirjautumisnäkymän:

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/login.png?raw=true">

Käyttäjä voi kirjautua sisään syöttämällä käyttäjätunnuksensa ja klikkaamalla Login tai jos käyttäjä ei ole vielä rekisteröitynyt, voi tämä siirtyä rekisteröitymiseen Register-painikkeesta.

## Uuden käyttäjän rekisteröinti

Rekisteröityminen tapahtuu syöttämällä Username-kenttään käyttäjätunnus ja Name-kenttään nimi:

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/register.png?raw=true">

Jos luonti onnistuu, ilmestyy ikkunan alareunaan tästä kertova viesti. Muussa tapauksessa tekstin tilalla ilmoitetaan miksi luonti ei onnistunut.

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/register2.png?raw=true">

Back to login -painikkeella pääsee palaamaan kirjautumisikkunaan ja syöttämään luodun käyttäjänimen:

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/login2.png?raw=true">

Kirjautuminen tapahtuu klikkaamalla Login-painiketta.

## Elokuvan lisääminen watchlistille

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/logger1.png?raw=true">
Ohjelma avaa logger-näkymän. Käyttäjä voi lisätä watchlistille elokuvan syöttämällä sen tiedot alareunassa näkyviin kenttiin ja klikkaamalla Add film -painiketta.

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/logger2.png?raw=true">

## Elokuvan merkitseminen nähdyksi

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/logger3.png?raw=true">

Käyttäjän watchlistille lisäämät elokuvat näkyvät Watchlist-välilehdellä listana. Elokuvan voi merkitä nähdyksi klikkaamalla elokuvan vieressä olevaa Mark as seen -painiketta.

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/logger4.png?raw=true">

Tämän jälkeen elokuva poistuu watchlistiltä ja tulee näkyviin Seen-välilehdellä.

## Arvion lisääminen ja muokkaaminen elokuvalle

Elokuvalle lisätään arvio klikkaamalla Seen-välilehdellä sen vieressä olevaa Add review -painiketta.

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/review1.png?raw=true">

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/review2.png?raw=true">

Tämän jälkeen avautuu ikkuna, jossa käyttäjä voi syöttää elokuvalle katselupäivämäärän, numeerisen arvosanan (1-5) ja sanallisen arvion. Arvio tallennetaan klikkaamalla Add-painiketta.

<img src="https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/images/logger5.png?raw=true">

Lisäämisen jälkeen käyttäjä palautetaan logger-näkymän Seen-välilehteen. Jo tehtyjä arvioita pääsee tarkastelemaan ja halutessaan muokkaamaan klikkaamalla See review -painiketta.

## Uloskirjautuminen

Käyttäjä voi kirjata itsensä ohjelmasta ulos klikkaamalla oikean yläkulman Logout-painiketta. Tämän jälkeen käyttäjä palautetaan kirjautumisnäkymään.
