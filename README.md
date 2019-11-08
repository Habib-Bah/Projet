# Projet
 Developpement, Test.

Nous devons vérifier que l’application que notre équipe est entrain de développer pour notre client fonctionne correctement, qu’elle répond bien aux règles de gestion et les respecte.
Pour ce faire, nous avons défini une stratégie de test pour les tests unitaires et pour les tests d’intégration nous avons choisi d’implémenter et automatiser nos tests à l’aide de junit, maven et travis.


Pour lancer les tests, il faut lancer Docker : docker-compose up
En suite tapez la commande : 
    - mvn test -Ptest-consumer,test-business

Liens d'accès au GitHub du projet se trouve se trouve sur le fichier : lienGitHub.

Pour les tests nous avons choisi d'implementer DbUnit. Toute notre demarche est expliquée sur le fichier Rapport que nous avons mis a disposition dans le package du projet.
