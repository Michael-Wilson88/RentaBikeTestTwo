       Introductie:

Dit is de Rent a Bike verhuur applicatie waar, vanzelfsprekend, fietsen mee verhuurd kunnen worden. Het is gemaakt voor medewerkers van de fietsverhuur dus niet voor reserveringen van klanten etc. De applicatie rekent vanzelf de prijs uit op basis van het aantal verhuurdagen en wat voor type fiets het is. Een elektrische fiets is hierbij uiteraard duurder dan een gewone fiets. De gebruiker kan zelf fietsen en klanten aan de database toevoegen om ze vervolgens met elkaar te koppelen via een Rental.
       Uitleg:

De gebruiker maakt als eerste fietsen aan die hij/zij vervolgens kunt gaan verhuren. Deze fietsen hebben allemaal en fietsnummer die de gebruiker zelf kan bepalen. Als voorbeeld heb ik “E1” genomen, E omdat de fiets elektrisch is en 1 omdat het de eerste is. Op de fiets wordt een sticker geplakt met het juiste nummer zodat iedereen in de verhuur kan zien om welke fiets het gaat.

Ik heb voor datatype String gekozen om functionele redenen. De belangrijkste reden is de grootte van de voorraad fietsen die de verhuurder heeft. Als dat aantal hoog ligt dan is het gemakkelijker om te categoriseren met behulp van een letter, anders worden de getallen te hoog om overzichtelijk op de fiets te plakken. Tevens bestaat er de kans dat een sticker loslaat waardoor het lastiger zal zijn om de juiste fiets op te zoeken. 

Wanneer er een klant komt om een fiets te huren dan worden zijn/haar gegevens ingevoerd en zo word het object “Customer” aangemaakt. Vervolgens kan de gebruiker een Rental aanmaken die leeg is. Hier kan een Customer aan worden toegevoegd en het aantal fietsen die de klant wilt huren. De Rental kan meerdere fietsen bevatten om nutteloze administratie te voorkomen. Denk aan families met kinderen en groepen waarbij het gemakkelijker is om meerdere fietsen op naam van 1 persoon te zetten. Dit is gebruiksvriendelijker en sneller in het werkproces, waardoor het uiteindelijk minder tijd kost en rendabeler wordt.
De verhuurder kan de datum opgeven van uitgifte en de datum dat de fiets weer ingeleverd dient te worden. Het systeem berekent vervolgens de juiste prijs uit op basis van de hoeveelheid dagen en het type fiets. Als de fietsen worden ingeleverd, wordt er gecheckt of deze eventueel te laat zijn ingeleverd en of er dan nog extra betaald dient te worden. Het kan ook zo zijn dat door omstandigheden de fiets te vroeg ingeleverd word, hier zal een foutmelding getoond worden. De leidinggevende kan dan zelf bepalen wat voor actie er ondernomen word.


       Json data format:

           Gebruikers
Ik heb mijn server ingesteld op localhost:8090.
Gebruik de volgende link om in te loggen en geef een van de onderstaande Json bodies mee:

localhost:8090/auth/signup

Gebruiker met userrol:

{

    "username" : "user",
    "email" : "user@user.com",
    "password" : "123456",
    "role" : ["user"]  

}


Gebruiker met mod- en userrol:

{

    "username" : "mod",
    "email" : "mod@mod.com",
    "password" : "123456",
    "role" : ["mod", "user"]  

}


Gebruiker met adminrol:

{

    "username" : "admin",
    "email" : "admin@admin.com",
    "password" : "123456",
    "role" : ["admin"]

}

Gebruiker met alledrie de rollen:

{

    "username" : "superadmin",
    "email" : "superadmin@admin.com",
    "password" : "123456",
    "role" : ["admin", "mod", "user"]   

}


Waneer je inlogt geeft de backend-server een Json webtoken mee. Bewaar deze, want deze moet je meesturen. Praat via Postman met de volgende link:

localhost:8090/auth/signin

Geef de volgende Json in de body mee.

           Inloggen User

{

    "username" : "user",
    "password" : "123456"   

}

Inloggen mod:

{

    "username" : "mod",
    "password" : "123456"

}

Inloggen admin:

{

    "username" : "admin",
    "password" : "123456"    

}

Stuur nu het tokentype en accestoken mee met de Headers, dit doe je als volgt.
Je krijgt van de server een Bearer + acces token, kopieer deze token.
Voeg een header Authorization toe en zet daarin: 
- token type (Bearer)
- Acces token 

De volgende resultaten worden teruggegeven aan de server:
/test/all
Iedereen kan data uit deze end-point uitlezen.
Public Content.
/test/user
Alleen (ingelogd) gebruikers met de user-rol kunnen data uit deze API uitlezen.
User Content.
/test/mod
Alleen (ingelogd) gebruikers met de mod-rol kunnen data uit deze API uitlezen. Moderator Board.
/test/admin
Alleen (ingelogd) gebruikers met de admin-rol kunnen data uit deze API uitlezen. Admin Board.


           Create Bike (post request)

Localhost:8090/createBike

{   
 
    "bikeNumber" : "E1",
    "brand" : "Gazelle",
    "frameNumber" : "HA1234567",
    "retailPrice" : 1200,
    "basePrice" : 20.0,
    "electric" : true      

}

De basePrice voor een elektrische fiets is 20.0 maar moet verandert worden naar 10.0 als het een gewone fiets is.


           Create Customer (post request)

localhost:8090/createcutomer

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

Extra customers: 

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

{   
 
    "firstName" : "Martien",
    "lastName" : "Wilson",
    "age" : "70",
    "phoneNumber" : "0611223345",
    "emailAddress" : "MWilson@rentabike.com",
    "passportNumber" : "HA72419BT",
    "country" : "Netherlands",
    "address" : "Vlashoven 32"

}


           Create Rental (post request)

localhost:8090/createrental

Hierin post je een lege body, met de addCustomer api kun je een klant toevoegen en met de addBike api kun je een of meerdere fietsen toevoegen.

           Add Bike (post request)

localhost:8090/rentals/{id}/addbike 

Voorbeeld: localhost:8090/rentals/1/addbike

{

    "bikeNumber" : "E1",
    "beginDate" : "22-05-2021",
    "endDate" : "23-05-2021"   

}

Zoals je kunt zien is de rental_id al beschreven in de pathname. Het format voor de datum wordt automatisch omgezet van Json naar LocalDate.
De addBike rekent via calculateBike automatisch de prijs uit op basis van dagen, rentalDays. Als de opgegeven tijd langer is dan een maand dan begint derentalDays teller weer bij 1. Dit komt omdat Localdate automatisch de hoeveelheid dagen omzet naar een maand en vanaf daar weer verder gaat tellen. De maand wordt opgeslagen en dan krijg je bijvoorbeeld: 1 month, 2 days.Ik gebruik echter de variabele maand niet in mijn berekeningen, enkel dagen. Ik heb er ook niet voor gekozen om maanden te gebruiken, omdat het onrealistisch is.
Er zal bijna nooit iemand langer dan een maand een fiets huren, als dit wel het geval is dan zal dit afgesproken worden met de eigenaar van de fietsverhuur en dan zal hij/zij dit zeer waarschijnlijk ook doen voor een aangepaste prijs.
Uiteraard, was dit een echt scenario, dan had ik dit eerst overlegd met de opdrachtgever.


           Add Customer (post request)

localhost:8090/rentals/1/addcustomer

{

    "id" : "1" 

}

Hier geldt hetzelfde als voor de addBike api. Rental_id staat al in de pathway en je hoeft alleen het customer_id toe te voegen.


           Return Bike (post request)

localhost:8090/rentals/1/returnbike

{

    "bikeNumber" : "E1"

}

De returnBike methode spreekt voor zich. Je voert het fietsnummer in en het programma kijkt het na.
Wordt de fiets te laat ingeleverd dan krijg je een foutmelding te zien. Hier staat in hoeveel dagen de fiets te laat is en wat de klant dan eventueel nog moet bijbetalen.
Met de payBike methode kan dit dan opgelost worden, hier later uitleg over.
De fiets kan uiteraard ook te vroeg ingeleverd worden. Hier geeft het systeem ook een foutmelding van, het is aan de eigenaar over wat er dan zal gebeuren.


           Pay Bike (post request)
           
localhost:8090/rentals/1/paybike

{

    "bikeNumber" : "E1"

}

De payBike methode heb ik gemaakt om een fiets te resetten. Deze methode kun je invoeren als een fiets te laat is, te vroeg, of als de gebruiker een fout heeft gemaakt.
Ik had ook een update optie kunnen maken, maar ik vond dit efficiënter.
Deze methode zet alle waarden van de fiets op zijn oorspronkelijke instellingen. Denk hieraan aan de returnDate, rentalDays en de rentalPrice.

           Get requests:

GetBikes:
localhost:8090/bikes 
localhost:8090/bikes/id

GetRentals:
localhost:8090/rentals
localhost:8090/rentals/1 

GetCustomers:
localhost:8090/customer
localhost:8090/customer/lastname


