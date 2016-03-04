**Aihe:** Luolastoseikkailu-peli

**Käyttäjät:** Pelaaja

Vuoropohjainen peli, jossa pelaaja voi liikuttaa pelihahmoa kentällä. Pelikentällä on sekä kerättäviä aarteita että vihollisia, joita vastaan pelaaja voi taistella. Peli päättyy, kun pelaaja kuolee tai kun kaikki kentän viholliset ovat kuolleet.

**Rakenne:**
caves.entities: 
Sprite: abstrakti luokka pelin olioiden sijaintien ja kuvien muistamiseen.
Treasure: tallentaa pelissä kerättävien aarteiden pistearvon ja palautettavien elämäpisteiden määrän.
Actor: abstrakti luokka itsestään toimivien peliolioiden liikkumiseen ja taistelemiseen.
Enemy: pelin vihollisten liikkumiskäyttäytyminen.
Player: tallentaa pelaajan pisteet.
SpriteEnum: sisältää peliolioiden ja tiilien kuvien tiedostosijainnit ja lataa kuvat Image-olioiksi tarvittaessa.
EntityManager: hoitaa peliolioiden vuorossa tapahtuvat toiminnot.

caves.gamestates: 
State: abstrakti luokka joka tarjoaa metodit pelitilanteen päivittämiseen ja piirtämiseen.
Gameplay: päivittää ja piirtää pelitilanteen, vaihtaa pelitilanteen EndStateksi pelin päätyttyä.
Gameover: piirtää pelin päättymisen, vaihtaa pelitilanteen PlayStateksi pelaajan aloittaessa uuden pelin.
MainMenu: piirtää päävalikon.
Manual: piirtää peliohjeet.

caves.gui: 
Screen: pääluokka, käynnistää käyttöliittymän.

caves.logic: 
Tile: sisältää tiilen tyypin ja mahdollisesti yhden peliolion ja esineen.
Area: sisältää pelikentän 2d-arrayna Tile-olioita. Tarjoaa metodit peliolioiden poistamiseen ja siirtämiseen tiilestä toiseen.
AreaGenerator: tarjoaa metodit satunnaisen pelikentän generointiin.
Game: kokoaa yhteen pelin toiminnan kannalta olennaiset osat.

caves.status: 
StatusDisplay: seuraa pelaajan ja peliolioiden tilaa ja piirtää sen näytölle.

![Luokkakaavio](./luokkakaavio.png)
![Sekvenssikaavio 1](./game.updateState().png)
![Sekvenssikaavio 2](./entitymanager.updateEnemies().png)
