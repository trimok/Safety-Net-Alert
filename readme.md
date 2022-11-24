\title{
SafetyNet Alert
}

I. Contexte

II. Besoins

III . Modèle métier et Architecture logicielle

IV . DTO, Gestion des exceptions, Logging

V . Actuator

VI. Organisation des packages / classes

VII . Parcours logiciels

VIII . Tests

IX. Conclusion 

\section{Contexte}

- La société SafetyNet a besoin de créer une application dont le but est de permettre aux systèmes d'urgence de récupérer des informations sur les personnes (nom, prénom, adresse, age, téléphone, email, antécédents médicaux, caserne de pompiers).

- Le but est de créer une application back-end, accessible via HTTP, pour mettre à disposition des points d'entrée :

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-03.jpg?height=198&width=1156&top_left_y=1172&top_left_x=498)

- Un fichier de données est disponible au format Json. 

\section{Besoins (utilisateur)}

Obtenir une liste des personnes couvertes par une caserne de pompiers

Obtenir une liste des enfants (et membres du foyers), par adresse

Obtenir une liste de $\mathrm{n}^{\circ}$ de téléphone des personnes desservies par une caserne de pompiers

Système d'urgence

Obtenir une liste des personnes (et la caserne associée), par adresse

Obtenir une liste des personnes, groupée par foyer(adresse), desservies par une caserne de pompiers

Obtenir les informations sur une personne (y compris antécédents médicaux)

Obtenir les adresses mail, par ville 

\section{Besoins (système)}

Ajouter une nouvelle personne

Mettre à jour une personne existante

Supprimer une personne

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-05.jpg?height=268&width=221&top_left_y=742&top_left_x=366)

Ajouter un dossier médical

Mettre à jour un dossier médical

Supprimer un dossier médical

Ajouter un mapping adresse-caserne

Mettre à jour le numéro de la caserne de pompiers d'une adresse

Supprimer le mapping d'une caserne

Supprimer le mapping d'une adresse 

\section{Modèle métier et Architecture logicielle (1/3)}

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-06.jpg?height=1382&width=2361&top_left_y=313&top_left_x=427)



\section{Modèle métier et Architecture logicielle (2/3)}

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-07.jpg?height=1548&width=2182&top_left_y=272&top_left_x=542)



\section{Modèle métier et Architecture logicielle (3/3)}

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-08.jpg?height=876&width=2798&top_left_y=540&top_left_x=272)



\section{DTO, exceptions, logging}

- DTO

Des objets DTO (Data Transfert Objects) sont passés (à) et renvoyés (par) la couche controller, et transmis à la couche service

- Pour la partie requête, les objets DTO sont transmis jusqu'à la couche repository

- Pour la partie CRUD, La couche service transmet et reçoit des objets du modèle métier de la couche repository

- Gestion centralisée des exceptions

- la classe GlobalExceptionHandler gère l'ensemble des exceptions applicatives et autres, en renvoyant un objet d'erreur standard ApiError (type d'opération, message d'erreur, payload) - Des informations détaillées sur l'application et sa 'bonne santé' peuvent être obtenus grâce aux endpoints de type actuator (ajouter la ligne management. endpoints.web. exposure.include=* dans application.properties)

- actuator/metrics/jvm.memory.max [\{"statistic":"VALUE","value":4.517265405E9\}]

- actuator/beans

"scope":"singleton", "type"."net.safety.alert.service.AddressStationService, "dependencies":["addre ssStationRepository" ]

actuator/env/JAVA_HOME \{"value":"C:\\Program Files\\Javal|jdk-17.0.5"\}

actuator/loggers/net.safety.alert.database.Database " "effectiveLevel":"INFO" 

\section{Organisation des packages / classes}

$\sim \mathrm{rrc} / \mathrm{main} / \mathrm{java}$

早 net.safety.alert.config

曲 net.safety.alert.constants

車 net.safety.alert.controller

車 net.safety.alert.database

本 net.safety.alert.dto

早 net.safety.alert.exception

言 net.safety.alert.filter

車 net.safety.alert.model

早 net.safety.alert.repository

本 net,safety.alert,service

- the net.safety.alert.util

皿 $\mathrm{src} /$ main/resources

- $\mathrm{mrc} / \mathrm{test/java}$

$>$ 車 net.safety.alert.tests

$>$ 甫 net.safety.alert.tests.util

$\vee$ 昌 net.safety.alert.tests

$>$ AddressStation Tests.java

> DatabaseTests.java

> MedicalRecordTests.java

$>$ PersonTests.java

$>$ D QueryTests.java $\checkmark$ 荀 net.safety.alert.controller

> DddressStationController.java

> MedicalRecordController.java

> PersonController.java

$>$ DueryController.java

$\sim$ 甚 net.safety.alert.service

$>$ AddressStationService.java

> IAddressStationService.java

> II IMedicalRecordService.java

$>L_{0}^{\mathrm{I}}$ IPersonService.java

> I I IQueryService.java

$>$ MedicalRecordService.java

$>$ PersonService.java

$>$ QueryService.java

$\vee$ 昆 net.safety.alert.repository

$>18$ AddressStationRepository.java

$>\int_{9}^{I} \mid$ AddressStationRepository.java

> I I MedicalRecordRepository.java

$>$ IPersonRepository.java

> II IQueryRepository.java

$>10$ MedicalRecordRepository.java

> 8 PersonRepository.java

$>$ QueryRepository.java $\vee$ 蚫 net.safety.alert.model

[D. Address.java

[D Allergie.java

DireStation.java

19 Medication.java

[D Person.java

[8 Personld.java

$\vee$ 蝍 net.safety.alert.dto

\& AddressDTO.java

D. AdultByAddressDTO.java

(A ChildrenByAddressDTO.java

D ChildrensByAddressDTO.java

[A EmailsByCityDTO.java

D. FireStationDTO.java

[A JsonDTO.java

[1. MappingAddressStationDTO.java

D. MedicalRecordDTO.java

D. MedicationDTO.java

[A PersonByAddressDTO.java

A PersonByFirstNameLastNameDTO.java

I PersonByStationDTO.java

A PersonDTO.java

A. PersonGroupByAddressByListStationDTO.java

A PersonsByAddressDTO.java

月 PersonsByFirstNameLastNameDTO.java

D. PersonsByStationDTo.java

D PersonsGroupByAddressByListStationDTo.java

A PhonesByStationDTO.java Controller

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-12.jpg?height=219&width=1451&top_left_y=169&top_left_x=731)

Service
![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-12.jpg?height=1412&width=2788&top_left_y=405&top_left_x=406)

\section{Parcours logiciel (GET)}

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-13.jpg?height=352&width=2213&top_left_y=178&top_left_x=724)

Service
![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-13.jpg?height=1256&width=2856&top_left_y=563&top_left_x=425)

\section{Tests (rapport SureFire)}

- Les tests sont plutôt des tests d'intégration : la couche controller est 'mockée', mais les autres couches logicielles travaillent normalement.

- 48 tests au total

- Tests de bon fonctionnement

- Tests d'erreur

- Erreurs fonctionnelles

- Données non valides

- Paramètre (GET) non valide

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-14.jpg?height=795&width=2386&top_left_y=1065&top_left_x=474)



\section{Tests (exemple de code)}

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-15.jpg?height=1551&width=2944&top_left_y=289&top_left_x=167)



\section{Tests (couverture de code Jacoco)}

Remarque :

Les packages config, constants, dto, exception, model, util ont été exclus du rapport.

\section{SafetyNetAlert}

![](https://cdn.mathpix.com/cropped/2022_11_24_78b9e52af894284c7f93g-16.jpg?height=693&width=2675&top_left_y=997&top_left_x=159)



\section{Conclusion}

- Beaucoup de refactoring.

- Eviter la duplication de code

- Isoler les couches logicielles

- Utiliser des singletons (database, mapper Json)

- Les annotations lombok allègent le code

- Difficultés rencontrées

- Beaucoup de petites subtilités, mais qui peuvent faire perdre du temps

- Exemple : Serialisation / Deserialisation

- Pour qu'un mapper Json (ObjectMapper) puisse traiter les dates de type LocalDate, il faut une dépendance Maven jackson-datatype-jsr310 et il faut enregistrer un JavaTimeModule auprès du mapper. 

\section{Merci}