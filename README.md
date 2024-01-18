# Easy Share

## Sommaire
- [Introduction](#introduction)
- [Pre-requis](#pre-requis)
- [Lancement](#lancement)
- [Quelques images](#quelques-images)
- [Architecture](#architecture)
- [Fonctionnalites](#fonctionnalites)

## Introduction
- **Classe** : ESGI 4AL2
- **Groupe 11** :
  - LAHMADI Zakarya
  - ZERGUINE Mohammed Mazene
  - ZHU Loïc

## Pre-requis
- **Docker** : https://docs.docker.com/get-docker/
- **Docker-compose** : https://docs.docker.com/compose/install/
- En tant que développeur du projet :
  - **NodeJS** : https://nodejs.org/en/download/
  - **NPM** : https://www.npmjs.com/get-npm

## Lancement
- **Docker** : `docker-compose up`

## Architecture
![android-architecture.drawio.png](docs/android-architecture.drawio.png)

## Choix de conception
- MVVM (Modèle-Vue-VueModèle) :
  - **Modèle** : représente les données et la logique métier
  - **Vue** : représente l’interface utilisateur
  - **VueModèle** : représente la logique de présentation et de liaison entre la vue et le modèle

## Quelques images
- TODO 1
- TODO 2

## Fonctionnalites
- fonctionnalitées de bases envisagées :
  - publication d’objets :
    - [ ] création
    - [ ] lister
    - [ ] modification
    - [ ] suppression
- fonctionnalitées bonus :
  - [x] inscription
  - [x] connexion
  - [x] déconnexion
  - [x] favoris :
    - [x] ajouter & supprimer une publication en favori
    - [x] lister les favoris
  - [ ] Publication :
    - [ ] contacter l’auteur d’une publication par mail
    - [ ] “dis/like”
    - [ ] CRUD de commentaires
