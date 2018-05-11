# Film Logger

## Dokumentaatio

[Käyttöohje](https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Releaset
[Viikko 5](https://github.com/emmalait/FilmLogger/releases/tag/viikko5)

[Loppupalautus](https://github.com/emmalait/FilmLogger/releases/tag/loppupalautus)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Ohjelma voidaan suorittaa komentoriviltä komennolla:

```
mvn compile exec:java -Dexec.mainClass=filmlogger.ui.LoggerAppMain
```


### Testaus
Testit suoritetaan komennolla:

```
mvn test
```

Testikattavuusraportti luodaan komennolla:

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimessa tiedoston *target/site/jacoco/index.html*.

### Suoritettavan jar-tiedoston generointi
Suoritettava jar-tiedosto generoidaan komennolla:

```
mvn package
```

Suoritettava jar-tiedosto generoituu polkuun *target/FilmLogger-1.0-SNAPSHOT.jar*.

### JavaDoc
JavaDoc generoidaan komennolla:

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimessa tiedoston *target/site/apidocs/index.html*.

### Checkstyle
Tiedostoon [checkstyle.xml](https://github.com/emmalait/FilmLogger/blob/master/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla:

```
mvn jxr:jxr checkstyle:checkstyle
```

Virheraporttia voi tarkastella avaamalla selaimessa tiedoston *target/site/checkstyle.html*.
