# KinoXP

KinoXP er et biograf-bookingsystem, der giver kunder mulighed for at se kommende forestillinger, vælge sæder og gennemføre reservationer online. Administratorer kan administrere film, forestillinger og se reservationer via et beskyttet adminpanel. Systemet er bygget med Spring Boot, MySQL og Docker.

# Arkitektur

Projektet følger en klassisk lagdelt arkitektur med mikroserviceudvidelse:

**Hovedapplikation (KinoXP)**
- Controller — modtager HTTP requests fra frontend og returnerer JSON via REST API
- Service — indeholder forretningslogikken, herunder reservationshåndtering
- Repository — kommunikerer med databasen via Spring Data JPA
- Database — MySQL database med 8 tabeller: Film, Hall, Seat, Screening, Reservation, ReservationSeat, Admin og Customer

**Mikroservice (pricing-service)**
- Selvstændig Spring Boot applikation der håndterer prisberegning
- Kommunikerer med hovedapplikationen via REST ved hjælp af Spring RestClient
- Sikret med JWT (JSON Web Tokens)
- Kører på port 8081

# Sikkerhed


Applikationen bruger Spring Security med sessionsbaseret autentifikation via cookies.

**Roller:**
- `ROLE_ADMIN` — adgang til adminpanel, film og alle reservationer
- `ROLE_CUSTOMER` — adgang til booking og egne reservationer

**Endpoints:**
- Åbne: forsiden, forestillinger, sæder, login, registrering
- Beskyttede (kunde): `/api/reservations/**`
- Beskyttede (admin): `/api/admin/**`, `/api/films/**`

Passwords gemmes krypteret med BCrypt.

# Sådan kører du projektet med Docker Compose

Krav: Docker og Docker Compose skal være installeret.

Klon repositoriet
Opret en .env fil i projektets rodmappe med dine egne værdier for:

MYSQL_ROOT_PASSWORD

MYSQL_DATABASE

SPRING_DATASOURCE_URL

SPRING_DATASOURCE_USERNAME

SPRING_DATASOURCE_PASSWORD

# Start applikationen:

Åbn en terminal i projektets rodmappe (Git Bash) og kør følgende kommando for at bygge og starte både applikationen og databasen:

1. docker compose up --build

Dette starter tre containers:
- `kinoxp-db` — MySQL database
- `kinoxp-pricing` — pricing mikroservice på port 8081
- `kinoxp-app` — hovedapplikationen på port 8080

Seed scriptet seed.sql findes i projektets rodmappe og skal køres mod den kørende database via MySQL Workbench eller en tilsvarende klient på port 3307.

Åbn derefter browseren og gå til: http://localhost:8080

Hjemmesiden burde nu køre og være klar til brug.

**Miljøvariabler (.env):**

| Variabel | Beskrivelse |
|---|---|
| `MYSQL_ROOT_PASSWORD` | MySQL root password |
| `MYSQL_DATABASE` | Databasenavn |
| `SPRING_DATASOURCE_USERNAME` | Database brugernavn |
| `SPRING_DATASOURCE_PASSWORD` | Database password |

**JWT:**

JWT secret er konfigureret i `application.properties` og skal være identisk i begge services:

jwt.secret=kinoxp-super-secret-jwt-key-minimum-32-chars

# Brugere

**Admin:** Oprettes via `POST /api/admin` med brugernavn og password.

**Kunde:** Oprettes via registreringssiden på `/register.html`

# Sådan kører du testene

Testene kan køres direkte i IntelliJ ved at højreklikke på test-mappen og vælge "Run All Tests".

Testene bruger Mockito til unit tests og H2 in-memory database til integrationstesten — ingen MySQL forbindelse er nødvendig.

# ER Diagram

Et Chen-notation ER diagram der kortlægger KinoXP domænet. Diagrammet viser 8 entiteter — Cinema, Hall, Film, Screening, Seat, ReservationSeat, Reservation og Admin — med deres attributter og relationer. Dette viser den fulde dataarkitektur inklusive sædekategorier, reservationslogik og rollebaseret adgangsseparation mellem admin og kunde.

<img width="1024" height="768" alt="KinoXPER (2)" src="https://github.com/user-attachments/assets/87fea7b2-2a55-438a-9c65-c4a96dd26578" />


# Wireframes

Fem wireframes der dækker hele brugerrejsen. Kundesiden: hjemmeside med forestillingsoversigt, sædevalgside med farvekodede cowboy- og sofapladser samt dynamisk prisberegning, og en bekræftelsesside efter booking. Adminsiden: sikker loginside, hoveddashboard med håndtering af forestillinger og reservationer.

<img width="1024" height="768" alt="KINOXPHOMEPAGE (1)" src="https://github.com/user-attachments/assets/6f3d9ae8-b1af-4916-8511-70979c44240a" />

<img width="1024" height="768" alt="KINOXPBOOKINGPAGE (1)" src="https://github.com/user-attachments/assets/699c558c-6e1f-4f7b-b483-86c87e78b9d9" />

<img width="1024" height="768" alt="KINOXPBOOKINGPAGE (2)" src="https://github.com/user-attachments/assets/d5bfba49-83bc-4d88-9e43-e32f80c6d31d" />

<img width="1024" height="768" alt="KINOXPBOOKINGPAGE (5)" src="https://github.com/user-attachments/assets/a544f02c-64b7-4b81-8fa6-ec71b9cc75b3" />

<img width="1024" height="768" alt="KINOXPBOOKINGPAGE (7)" src="https://github.com/user-attachments/assets/f137ca33-31a3-4d05-be14-8203fc925be1" />


# KinoXP Product Backlog

Sprint 1 — March 3–7

| Story | Priority | Points | Status |
| --- | --- | --- | --- |
| As a customer I want to see a list of current screenings so I know what films are showing and when | MVP | 1 | DONE |
| As a customer I want to see available seats for a screening so I can choose where to sit | MVP | 2 | DONE |
| As a customer I want to reserve specific seats so I can secure my spot | MVP | 3 | DONE |
| As a customer I want to see the total price before confirming so I know what I'm paying | MVP | 2 | DONE |
| As a customer I want to receive confirmation of my reservation so I have proof of booking | MVP | 1 | DONE |
| As a customer I want group discount applied automatically so I don't have to request it manually | MVP | 2 | DONE IN SPRINT 2 |
| As an admin I want to log in securely so that former employees cannot access the system | MVP | 2 | DONE IN SPRINT 2 |

Sprint 2 — March 8–14

| Story | Priority | Points | Status |
| --- | --- | --- |--------|
| As an admin I want to create a new screening so I can add films to the schedule | MVP | 2 | DONE   |
| As an admin I want to edit an existing screening so I can correct mistakes or update times | MVP | 2 | DONE   |
| As an admin I want to delete a screening so I can remove cancelled shows | MVP | 1 | DONE   |
| As an admin I want to see all reservations for a screening so I know how many seats are sold | MVP | 2 | DONE   |

Sprint 3 — Sikkerhed og mikroservice

| Story | Priority | Points | Status |
| --- | --- | --- | --- |
| As a customer I want to log in so I can see my existing reservations | MVP | 3 | DONE |
| As a customer I want to register an account so I can book and manage reservations | MVP | 2 | DONE |
| As a system I want pricing to be handled by a separate microservice so the logic is decoupled | MVP | 5 | DONE |
| As a system I want the microservice to be secured with JWT so only authorized services can call it | MVP | 3 | DONE |

Nice To Have

| Story | Priority | Points | Status           |
| --- | --- | --- |------------------|
| As an admin I want to manage seat pricing per hall so I can update prices without changing code | Nice To Have |  | CUT              |
| As an admin I want to add a 3D surcharge to a screening so the price reflects the equipment used | Nice To Have |  | DONE             |
| As an admin I want to block broken seats from being reserved so customers cannot book unavailable seats | Nice To Have |  | WORKAROUND       |

<img width="1485" height="905" alt="burndown" src="https://github.com/user-attachments/assets/88ad1e5a-ade8-4302-8f9c-6f7d7850e753" />


