# Rent a Bike Verhuur applicatie.

Dit is de Rent a Bike verhuur applicatie waar, vanzelfsprekend, fietsen mee verhuurd kunnen worden.
De applicatie rekent vanzelf de prijs uit op basis van de aantal verhuurdagen en wat voor type fiets het is.
Een elektrische fiets is hierbij uitteraard duurder dan een niet elektrische fiets. 
De gebruiker kan zelf fietsen en klanten aan de database toevoegen om ze vervolgens met elkaar te koppelen via een Rental.


# Uitleg.
De gebruiker maakt als eerste fietsen aan die hij/zij vervolgens kunt verhuren. Deze fietsen hebben allemaal een fietsnummer die de gebruiker zelf
kan bepalen. Als voorbeeld heb ik "E1" genomen, E omdat de fiets elektrisch is en 1 omdat het de eerste is. Op de fiets wordt een sticker geplakt met het juiste
nummer zodat iedereen in de verhuur kan zien om welke fiets het gaat.
Als er een klant komt om de fiets te huren dan worden zijn/haar gegevens ingevoerd en zo word het object "Customer" aangemaakt.
Vervolgens kan de gebruiker een Rental aanmaken die leeg is. Hier kan een Customer aan worden toegevoegd en meerdere fietsen. 
Het is namelijk makkelijker om een Customer meerdere fietsen te kunnen verhuren i.v.m. families etc, maar ook zal het gebruiksvriendelijker zijn om niet voor
elke verhuurde fiets een Customer aan te maken. Dit kost veel tijd en het is niet rendabel.
De verhuurder kan de datum opgeven van uitgifte en de datum dat de fiets weer ingeleverd moet worden. Het systeem berekent vervolgens de juiste prijs
op basis van de hoeveelheid dagen en type fiets. Als de fietsen worden ingeleverd wordt er gecheckt of deze eventueel te laat zijn ingeleverd en of er dan nog
extra betaalt dient te worden. Het kan ook zo zijn dat door omstandigheden de fiets te vroeg ingeleverd wordt en dan zal het systeem een foutmelding geven.
De bedrijfsleider/manager/baas zal dan zelf bepalen wat voor actie ondernomen wordt. 



#Json data format

Ik heb mijn server ingesteld op Localhost:8090

localhost:8090/api/auth/signup
Om in te loggen deze link en post de volgende Json in de body mee:

Gebruiker met userrol:

{

    "username": "user",
    "email" : "user@user.com",
    "password" : "123456",
    "role": ["user"]
    
}

Gebruiker met mod- en userrol:

{

    "username": "mod",
    "email" : "mod@mod.com",
    "password" : "123456",
    "role": ["mod", "user"]
    
}


Gebruiker met adminrol:

{

    "username": "admin",
    "email" : "admin@admin.com",
    "password" : "123456",
    "role": ["admin"]
    
}


Gebruiker met alledrie de rollen:

{

    "username": "superadmin",
    "email" : "superadmin@admin.com",
    "password" : "123456",
    "role": ["admin", "mod", "user"]
    
}

Wanneer je inlogt geeft de backend-server een Json webtoken mee. Bewaar deze, want deze moet je meesturen.
Praat via Postman met de volgende link : localhost:8090/api/auth/signin     en geef de volgende Json in de body mee:

Inloggen User:

{

    "username":"user",
    "password":"123456"
    
}


Inloggen mod:

{

    "username":"mod",
    "password":"123456"
    
}


Inloggen admin:

{

    "username":"admin",
    "password":"123456"
    
}

Stuur nu het tokentype en accestoken mee met de Headers, dit doe je als volgt.
Je krijgt van de server een Bearer + acces token, kopieer deze token.
Voeg een header Authorization toe en zet daarin: 
- token type (Bearer)
- Acces token 

De volgende resultaten worden teruggegeven aan de server:
/api/test/all
Iedereen kan data uit deze end-point uitlezen.
Public Content.
/api/test/user
Alleen (ingelogd) gebruikers met de user-rol kunnen data uit deze API uitlezen.
User Content.
/api/test/mod
Alleen (ingelogd) gebruikers met de mod-rol kunnen data uit deze API uitlezen. Moderator Board.
/api/test/admin
Alleen (ingelogd) gebruikers met de admin-rol kunnen data uit deze API uitlezen. Admin Board.



localhost:8090/createbike
    Post

{    

    "bikeNumber": "E1",
    "brand": "Gazelle",
    "frameNumber": "HA1234567",
    "retailPrice": 1200,
    "basePrice": 20.0,
    "electric": true    
    
}
 De basePrice is 20 voor een elektrische fiets en moet worden aangepast naar 10 voor een niet elektrische fiets.


localhost:8090/createcustomer
    Post
{

    "firstName" : "Kees",
    "lastName" : "van Dam",
    "age" : "60",
    "phoneNumber" : "0611223345",
    "emailAddress" : "Kees@rentabike.com",
    "passportNumber" : "HA72419BT",
    "country" : "Netherlands",
    "address" : "HavenStraat 22"
    
}

Extra Customers

{

    "firstName" : "Michael",
    "lastName" : "Wilson",
    "age" : "32",
    "phoneNumber" : "0611223346",
    "emailAddress" : "Willy@rentabike.com",
    "passportNumber" : "HA72419BP",
    "country" : "Netherlands",
    "address" : "Kerkstraat 1"

}

{

    "firstName" : "Isabelle",
    "lastName" : "Wilson",
    "age" : "19",
    "phoneNumber" : "0611223349",
    "emailAddress" : "Iesatron@rentabike.com",
    "passportNumber" : "HA72419BZ",
    "country" : "Netherlands",
    "address" : "Kerkstraat 1"

}

localhost:8090/createrental
    Post
Hierin post je een lege body, Met de addCustomer api kun je een klant toevoegen en met de addBike api kun je een of meerdere fietsen toevoegen.



localhost:8090/rentals/{id)/addbike
    Post
voorbeeld: localhost:8090/rentals/1/addbike

{

    "bikeNumber": "E1",
    "beginDate": "22-05-2021",
    "endDate": "23-05-2021"
    
}

Zoals je kunt zien is de rental_id al beschreven in de pathname. Het format voor de datum wordt automatisch omgezet van Json naar LocalDate.
De addBike rekent via calculateBike automatisch de prijs uit op basis van dagen, rentalDays. Als de opgegeven tijd langer is dan een maand dan begint de
rentalDays teller weer bij 1. Dit komt omdat Localdate automatisch de hoeveelheid dagen omzet naar een maand en vanaf daar weer verder gaat tellen.
De maand wordt opgeslagen en dan krijg je bijvoorbeeld: 1 month, 2 days.
Ik gebruik echter de variabele maand niet in mijn berekeningen, enkel dagen. Ik heb er ook niet voor gekozen om maanden te gebruiken, omdat het onrealistisch is.
Er zal bijna nooit iemand langer dan een maand een fiets huren, als dit wel het geval is dan zal dit afgesproken worden met de eigenaar van de fietsverhuur en dan
zal hij/zij dit zeer waarschijnlijk ook doen voor een aangepaste prijs.

Uiteraard, was dit een echt scenario, dan had ik eerst overlegd met de opdrachtgever.

localhost:8090/rentals/1/addcustomer

{

    "id":"1" 

}

Hier geldt hetzelfde als voor de addBike methode. Rental_id staat al in de pathway en je hoeft alleen het customer_id toe te voegen.


localhost:8090/rentals/1/returnbike

{

    "bikeNumber": "E1"

}

De returnBike methode spreekt voor zich. Je voert het fietsnummer in en het programma kijkt het na.
Wordt de fiets te laat ingeleverd dan krijg je een foutmelding te zien. Hier staat in hoeveel dagen de fiets te laat is en wat de klant dan eventueel nog moet bijbetalen.
Met de payBike methode kan dit dan opgelost worden, hier later uitleg over.
De fiets kan uiteraard ook te vroeg ingeleverd worden. Hier geeft het systeem ook een foutmelding van, het is aan de eigenaar over wat er dan zal gebeuren.


localhost:8090/rentals/1/paybike

{

    "bikeNumber": "E1"

}

De payBike methode heb ik gemaakt om een fiets te resetten. Deze methode kun je invoeren als een fiets te laat is, te vroeg, of als de gebruiker een fout heeft gemaakt.
Ik had ook een update optie kunnen maken, maar ik vond dit efficiënter.
Deze methode zet alle waarden van de fiets op zijn oorspronkelijke instellingen. Denk hieraan aan de returnDate, rentalDays en de rentalPrice.