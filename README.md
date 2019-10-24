## RMIPresident

# I. Lancer le serveur

Le serveur va s'exécuter sur la machine sur laquelle il est lancé. Par défaut, il se branchera sur le port 9999. Il est possible de modifier ce port dans le `main()` de la classe **`RmiServer`**.

Pour le lancer, il suffit d'exécuter ce `main()`.

# II. Lancer le client

Le **`RmiClient`** crée un **`Player`** qui va se connecter au **`RmiServer`**. Il est donc possible de paramétrer la connexion dans la méthode `findGame()` de **`Player`**. Par défaut, il va essayer de se connecter au réseau local sur le port 9999 sur le nom *RmiService*.

La connexion sera appelée une fois que le client aura entré un nom et cliqué sur le bouton *Find a game!*. Il sera alors dans un salon temporaire, mis en attente d'autres joueurs. Il faut quatre joueurs pour lancer une partie.
