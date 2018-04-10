# Film Logger



## Dokumentaatio

Käyttöohje

[Vaatimusmäärittely](https://github.com/emmalait/otm-harjoitustyo/blob/master/FilmLogger/dokumentaatio/vaatimusmaarittely.md)

Arkkitehtuurikuvaus

Testausdokumentti

[Työaikakirjanpito](https://github.com/emmalait/otm-harjoitustyo/blob/master/FilmLogger/dokumentaatio/tyoaikakirjanpito.md)

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

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*
