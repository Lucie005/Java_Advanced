# 🎮 Let's Play API - Java Advanced

Une API RESTful sécurisée développée dans le cadre du module Java Advanced (Bachelor Informatique).
Ce projet permet la gestion d'un catalogue de jeux vidéo, intégrant un système d'authentification robuste par token JWT et une gestion stricte des autorisations basées sur les rôles (Admin/User).

## 🛠️ Technologies Utilisées
* **Langage :** Java 21
* **Framework :** Spring Boot 3
* **Sécurité :** Spring Security & JSON Web Tokens (JWT), BCrypt
* **Base de données :** MongoDB Atlas (Cloud)
* **Outils de développement :** IntelliJ IDEA, HTTP Client
* **Protection (Rate Limiting) :** Bucket4j

## ✨ Fonctionnalités Principales
* **Authentification sécurisée :** Inscription, connexion et génération de tokens JWT.
* **Gestion des rôles :** Séparation des accès (lecture publique pour tous, modifications réservées aux administrateurs).
* **CRUD Produits :** Création, lecture, modification et suppression de jeux vidéo.
* **Gestion globale des erreurs :** Interception centralisée des exceptions (`@RestControllerAdvice`) pour garantir des réponses JSON propres (401, 403, 404, 500) au lieu des traces de crash serveur.

## 🌟 Fonctionnalités Avancées 
* **Configuration CORS :** L'API est configurée pour accepter de manière sécurisée les requêtes provenant d'un futur client front-end (ex: application React/Vue sur `http://localhost:3000`).
* **Rate Limiting :** Un filtre anti-spam et anti-brute-force a été implémenté via Bucket4j. L'API limite automatiquement le trafic (ex: 10 requêtes par minute) et renvoie un code `429 Too Many Requests` en cas d'abus.

## 🚀 Configuration et Lancement (Prérequis pour l'évaluation)

Pour des raisons évidentes de sécurité, le mot de passe de la base de données cloud n'est pas versionné sur ce dépôt. Pour exécuter et tester ce projet en local, une variable d'environnement doit être configurée.

### Sous IntelliJ IDEA :
1. En haut à droite, verifiez que le fichier ApiApplication est bien séléctionné et cliquez sur le menu déroulant de configuration (à gauche du bouton Run) puis sélectionnez **Edit Configurations...**.
2. Si le champ **Environment variables** n'est pas visible, cliquez sur **Modify options ⌄** à droite et ajoutez-le.
3. Cliquez sur l'icône de dossier à droite du champ et ajoutez la variable suivante :
    * **Name :** `MONGO_PASSWORD`
    * **Value :** *[Le mot de passe fourni par messagerie]*
4. Appliquez les modifications, fermez la fenêtre et lancez l'application avec le bouton **Run**. L'API démarrera sur le port `8080`.

## 🧪 Scénario de Test
Un fichier complet nommé `test.http` est inclus à la racine du projet. Il contient le scénario de test étape par étape, commenté et dans l'ordre chronologique d'exécution, permettant de valider l'ensemble des routes (création d'admin, récupération du token, opérations CRUD, et démonstration du filet de sécurité des erreurs).

---
**Auteur :** Lucie Barrez et Thibaud TABARD