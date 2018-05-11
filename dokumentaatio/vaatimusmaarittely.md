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

- Käyttäjä pääsee katsomaan *to watch* ja *seen* -listat logaamistaan elokuvista.
- Käyttäjä voi logata uuden elokuvan *to watch* -listalle:
  - Käyttäjä syöttää elokuvan nimen ja valmistusvuoden.
- Käyttäjä voi merkitä watchlistille logaamansa elokuvan nähdyksi, jolloin se näkyy *seen*-listalla.
- Käyttäjä voi lisätä näkemälleen elokuvalleen arvion, joka sisältää katselupäivämäärän, numeerisen arvion asteikolla 1-5 ja sanallisen arvion.

## Jatkokehitysideat

- Käyttäjä voi poistaa elokuvia watchlistiltä vaikka ei olisikaan katsonut niitä.
- Käyttäjä voi lisätä samasta elokuvasta useampia arvioita.
- Käyttäjä voi pitää kirjaa omistamistaan elokuvista.
- Käyttäjä voi lisätä nähtyjen elokuvien oheen lisätietoja katselupaikasta, elokuvalipun hinnasta tmv.
- Käyttäjä voi lisätä elokuville tunnisteita (esim. genre), joiden pohjalta pystytään generoimaan muita listoja/näkymiä. 
