# Film Logger



## Dokumentaatio

Käyttöohje

[Vaatimusmäärittely](https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/arkkitehtuuri.md)

Testausdokumentti

[Työaikakirjanpito](https://github.com/emmalait/FilmLogger/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Ohjelma voidaan suorittaa komentoriviltä komennolla:

```
mvn compile exec:java -Dexec.mainClass=filmlogger.ui.Main
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

### Checkstyle
Tiedostoon [checkstyle.xml](https://github.com/emmalait/FilmLogger/blob/master/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla:

```
mvn jxr:jxr checkstyle:checkstyle
```

Virheraporttia voi tarkastella avaamalla selaimessa tiedoston *target/site/checkstyle.html*.
