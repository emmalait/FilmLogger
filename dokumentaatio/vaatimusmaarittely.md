# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjät voivat pitää kirjaa katsomistaan elokuvista ja elokuvista joita he haluaisivat nähdä. Katsottujen elokuvien oheen voi lisäksi liittää pienimuotoisen arvostelun.

## Käyttäjät

Sovelluksella on ainoastaan yksi käyttäjärooli eli *normaali käyttäjä*. 

## Perusversion toiminnallisuus

### Ennen kirjautumista

- Käyttäjä voi luoda käyttäjätunnuksen järjestelmään.
  - Tunnuksen tulee olla uniikki ja pituudeltaan 5-15 merkkiä.
- Käyttäjä voi kirjautua järjestelmään.
  - Kirjautumiseen vaaditaan vain käyttäjätunnus, joka syötetään sille varattuun kenttään.
  - Jos syötettyä käyttäjätunnusta ei ole olemassa, ilmoittaa järjestelmä siitä virheviestillä ja kehotuksella luoda käyttäjätunnus.

### Kirjautumisen jälkeen

- Käyttäjä näkee *seen* ja *to watch* -listat logaamistaan elokuvista.
- Käyttäjä voi logata uuden elokuvan *to watch* -listalle:
  - Käyttäjä syöttää elokuvan nimen ja halutessaan valmistusvuoden.
- Käyttäjä voi logata elokuvan *seen*-listalle:
  - Tapaus A: Elokuva löytyy jo *to watch* -listalta.
    - Elokuvan voi merkitä nähdyksi valitsemalle sen nimen dropdown-valikosta.
    - Elokuvalle voi syöttää tähtiarvosanan (1-5 tähteä) ja/tai lyhyen (max. 1000 merkkiä) sanallisen arvostelun.
  - Tapaus B: Elokuva on uusi.
    - Käyttäjä syöttää elokuvan nimen ja halutessaan valmistumisvuoden.
    - Elokuvalle voi syöttää tähtiarvosanan (1-5 tähteä) ja/tai lyhyen (max. 1000 merkkiä) sanallisen arvostelun.

## Jatkokehitysideat

- Käyttäjä voi pitää kirjaa omistamistaan elokuvista.
- Käyttäjä voi lisätä nähtyjen elokuvien oheen lisätietoja ajankohdasta, paikasta, elokuvalipun hinnasta tmv.
- Käyttäjä voi lisätä elokuville tunnisteita (esim. genre), joiden pohjalta pystytään generoimaan muita listoja/näkymiä. 

## Käyttöliittymäluonnos

*TBA*