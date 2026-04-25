# Fullstack App - Conflictes entre països

Aplicació fullstack per a la gestió de conflictes i països desenvolupada amb:
- Frontend: Vue 3 + Vite + Pinia
- Backend: Spring Boot (Java)
- Base de dades: PostgreSQL (Supabase)
- Deploy Frontend: Vercel
- Deploy Backend: Railway
---
# Demo en producció

## Frontend (Vercel)
https://frontend-conflict.vercel.app

## Backend API (Railway)
https://fullstack-tasca-conflict-production-198b.up.railway.app

---
# Arquitectura del sistema
    Vue 3 (Vercel)

↓ HTTP REST API

    Spring Boot (Railway)

↓

    PostgreSQL (Supabase)


El frontend consumeix una API REST desplegada al núvol que es connecta a una base de dades PostgreSQL externa.

---
# Tecnologies utilitzades

## Frontend
- Vue 3
- Vite
- Pinia (state management)
- Vercel (deploy)

## Backend
- Spring Boot
- Hibernate
- PostgreSQL Driver
- Railway (deploy)

## Base de dades
- Supabase PostgreSQL

---

# Variables d'entorn

## Backend (Spring Boot)

Configurades a Railway:

DB_URL= jdbc:postgresql://aws-1-eu-central-1.pooler.supabase.com:5432/postgres

DB_USER= postgres.oyducviclwpmpzlsckif

DB_PASS= proyecto_Fullstack

El fitxer 'application.yaml' utilitza:

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USER}
    password: ${DB_PASS}

```
---
# Frontend (Vue 3 + Vite)
Local (.env.local)
VITE_API_URL = http://localhost:8080

## Producció (Vercel)

Configurat a:

Vercel Dashboard -> Project -> Settings -> Environment Variables

VITE_API_URL=https://fullstack-tasca-conflict-production-198b.up.railway.app
 
## CORS (Backend)

Per permetre la comunicació entre frontend i backend en producció, s’ha configurat CORS a Spring Boot:

    registry.addMapping("/api/**")
        .allowedOrigins("http://localhost:5173",
            "https://fullstack-tasca-conflict-production-198b.up.railway.app/",
            "https://frontend-conflict.vercel.app")
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowCredentials(true);

## Configuració SPA Routing (Vercel)

Per evitar errors 404 en refrescar rutes del frontend (Vue Router), s’ha afegit:

### vercel.json
    {
        "rewrites": [
            { "source": "/(.*)",
            "destination": "/index.html" }
        ]
    }

## Problemes trobats i solucions
### 1. Error de connexió a la base de dades
_Error_: Tenant or user not found

**Causa**: Credencials no configurades correctament al backend.

_Solució_: S’han configurat correctament les variables al Railway per evitar hardcoding.

### 2. Error CORS entre frontend i backend

~~Error~~: Blocked by CORS policy: No 'Access-Control-Allow-Origin'

**Causa**: No configurat els permisos del Frontend al Spring Boot.

_Solució_: Configuració de CORS a Spring Boot per permetre el domini de Vercel.

### 3. URL duplicada en peticions API
~~Problema~~: /api/v1/conflicts/api/v1/conflicts

_Solució_: Correcció de constants API_URL als stores de Pinia.


