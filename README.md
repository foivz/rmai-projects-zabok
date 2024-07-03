# Inicijalne upute za prijavu projekta iz Razvoja mobilnih aplikacija i igara

Poštovane kolegice i kolege, 

čestitamo vam jer ste uspješno prijavili svoj projektni tim na kolegiju Razvoj mobilnih aplikacija i igara, te je za vas automatski kreiran repozitorij koji ćete koristiti za verzioniranje vašega koda, ali i za pisanje dokumentacije.

Ovaj dokument (README.md) predstavlja **osobnu iskaznicu vašeg projekta**. Vaš prvi zadatak je **kratko opisati projektni prijedlog** na način da ćete prijavu vašeg projekta, sukladno uputama danim u ovom tekstu, napisati upravo u ovaj dokument, umjesto ovoga teksta.

Za upute o sintaksi koju možete koristiti u ovom dokumentu i kod pisanje vaše projektne dokumentacije pogledajte [ovaj link](https://guides.github.com/features/mastering-markdown/).
Sav programski kod potrebno je verzionirati u glavnoj **master** grani i **obvezno** smjestiti u mapu Software. Sve artefakte (npr. slike) koje ćete koristiti u vašoj dokumentaciju obvezno verzionirati u posebnoj grani koja je već kreirana i koja se naziva **master-docs** i smjestiti u mapu Documentation.

Nakon vaše prijave bit će vam dodijeljen mentor s kojim ćete tijekom semestra raditi na ovom projektu. Mentor će vam slati povratne informacije kroz sekciju Discussions također dostupnu na GitHubu vašeg projekta. A sada, vrijeme je da opišete vaš projekt. Za opis vašeg projektnog prijedloga molimo vas koristite **predložak** koji je naveden u nastavku, a započnite tako da kliknete na *olovku* u desnom gornjem kutu ovoga dokumenta :) 

# Kuharica 

## Projektni tim

Ime i prezime | E-mail adresa (FOI) | JMBAG | Github korisničko ime
------------  | ------------------- | ----- | ---------------------
Nataša Dramac | ndramac21@student.foi.hr | 1003120544 | NatasaDramac
Tia Kobasić Čonč | tkobasicc21@student.foi.hr | ... | Tkobasicc

## Opis domene
Kroz ovaj projekt kreirat će se mobilna aplikacija pod nazivom Kuharica koja će sadržavati nekoliko kategorija s različitim receptima.

## Specifikacija projekta
Ova specifikacija opisuje osnovne funkcionalnosti aplikacije za pregled i upravljanje receptima. Aplikacija omogućava korisnicima pristup raznim receptima, njihovo pregledavanje prema kategorijama te dodavanje omiljenih recepata na listu favorita. Za pristup aplikaciji potrebna je prijava korisnika (korisnik unosi korisničko ime i lozinku, nakon uspješne autentifikacije, korisnik dobiva pristup aplikaciji), nakon čega korisnik može pregledavati recepte prema kategorijama (recepti su organizirani u kategorije (npr. predjela, glavna jela, deserti), svaki recept sadrži detalje poput sastojaka, uputa za pripremu i vremena kuhanja). Korisnici recepte također mogu dodavati u omiljene recepte, odnosno favorite (saki recept ima opciju "Dodaj u favorite", omiljeni recepti se pohranjuju na korisnički račun, korisnici mogu pregledavati i upravljati svojim omiljenim receptima).
U slučaju neuspješne prijave, korisnik dobiva odgovarajuću poruku o grešci.

Oznaka | Naziv | Kratki opis 
------ | ----- | ----------- 
F01 | Login | Za pristup dnevniku potrebna je autentikacija korisnika pomoću login funkcionalnosti. 
F02 | Pregled recepata | Korisnici će nakon prijave u aplikaciju moći pregledavati recepte prema odabranim kategorijama.
F03 | Dodavanje recepata u favorite | Korisnici će moći dodati odabrane recepte na listu svojih omiljenih recepata.

## Tehnologije i oprema
Umjesto ovih uputa jasno popišite sve tehnologije, alate i opremu koju ćete koristiti pri implementaciji vašeg rješenja. Projekti se razvijaju koristeći Android razvojne okvire, a vrsta projekta može biti mobilna aplikacija ili mobilna aplikacija s elementima igara. Ne zaboravite planirati korištenje tehnologija u aktivnostima kao što su projektni menadžment ili priprema dokumentacije. Tehnologije koje ćete koristiti bi trebale biti javno dostupne, a ako ih ne budemo obrađivali na vježbama u vašoj dokumentaciji ćete morati navesti način preuzimanja, instaliranja i korištenja onih tehnologija koje su neophodne kako bi se vaš programski proizvod preveo i pokrenuo. Pazite da svi alati koje ćete koristiti moraju imati odgovarajuću licencu. Što se tiče zahtjeva nastavnika, obvezno je koristiti git i GitHub za verzioniranje programskog koda, GitHub Wiki za pisanje tehničke i projektne dokumentacije, a projektne zadatke je potrebno planirati i pratiti u alatu GitHub projects. 
