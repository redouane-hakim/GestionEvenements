# Gestion Événements (Spring Boot MVC)

Application web Spring Boot MVC (Thymeleaf) pour :
- Soumission d'une **demande d'autorisation** (Organisateur)
- **Validation** (accepter/refuser) par **Responsable/Admin**
- Publication/complétion de l'**événement** après acceptation
- Consultation publique, **participation** et **questions/réponses**

## Démarrage

Pré-requis : Java 17+, Maven 3.9+

```bash
mvn spring-boot:run
```

Puis ouvrir : http://localhost:8080

## Comptes démo

- Admin: `admin@demo.com` / `admin123`
- Organisateur: `org@demo.com` / `org123`

## URLs

Public:
- `/` (Home)
- `/events` (liste)
- `/events/{id}` (détail + participer + poser question)

Organisateur (ROLE_ORGANISATEUR):
- `/organisateur` (dashboard)
- `/organisateur/demande` (soumettre demande)
- `/organisateur/demandes` (traçabilité)
- `/organisateur/events` (mes événements)
- `/organisateur/events/{id}/edit` (compléter/modifier)
- `/organisateur/events/{id}/participants` (liste des participants)

Admin (ROLE_RESPONSABLE):
- `/admin` (dashboard)
- `/admin/demandes` (accepter/refuser + traçabilité)
- `/admin/events`
- `/admin/users`

H2 console:
- `/h2-console` (JDBC URL : `jdbc:h2:mem:gestionevenements`)
