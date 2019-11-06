## RMIPresident

# I. Lancer le serveur

Le serveur va s'exécuter sur la machine sur laquelle il est lancé. Par défaut, il se branchera sur le port 9999. Il est possible de modifier ce port dans le `main()` de la classe **`RmiServer`**.

Pour le lancer, il suffit d'exécuter ce `main()`.

# II. Lancer le client

Le **`RmiClient`** crée un **`Player`** qui va se connecter au **`RmiServer`**. Il est donc possible de paramétrer la connexion dans la méthode `findGame()` de **`Player`**. Par défaut, il va essayer de se connecter au réseau local sur le port 9999 sur le nom *RmiService*.

La connexion sera appelée une fois que le client aura entré un nom et cliqué sur le bouton *Find a game!*. Il sera alors dans un salon temporaire, mis en attente d'autres joueurs. Il faut quatre joueurs pour lancer une partie.

# III. Jouer la partie

*Les règles sont celles du jeu du Président : valeurs des cartes (3, 4, 5, 6, 7, 8, 9, 10, Valet, Dame, Roi, As, 2), le tas se ferme lorsque les quatres cartes d'une même valeur sont posées successivement, ou lorsqu'un 2 est posé. Le joueur qui ferme le tas garde la main. Le but est de vider sa main : le premier joueur à le faire est nommé Président, le second est Vice-Président, le troisième est Vice-Trou-du-cul et le joueur restant est le Trou-du-cul.*

1. Démarrage de la partie

Une fois les quatre joueurs réunis (il est possible de démarrer quatre instances de **`RmiClient`** sur une même machine), la vue changera et le joueur qui possède la Dame de coeur sera invité à jouer.

2. Déroulement du tour

Il doit pour cela sélectionner la carte qu'il désire placer dans la liste déroulante, qui correspond à sa main. Une fois la carte choisie, le joueur clique sur "OK" ou appuie sur "Entrée". S'il désire se coucher pour ce round, il peut cliquer sur "Annuler". Il ne pourra rejouer que lorsque le tas courant aura été fermé. Si le joueur n'a pas de coup légal possible, une fenêtre le lui indiquera à la place du choix de carte. Il sera alors couché jusqu'au prochain round. Le tour se joue dans l'ordre de connexion au serveur.

# IV. Fin de la partie

Lorsque les trois premiers joueurs ont réussi à vider leur main, la partie s'arrête et chaque joueur a une fenêtre lui indiquant son résultat et demande s'il désire rejouer. Si les quatres joueurs rejouent, une nouvelle partie se lance. Le président donnera deux cartes de son choix au trou-du-cul, le vice-président une au vice-trou-du-cul, ce dernier verra sa meilleure carte donnée au vice-président et les deux meilleures cartes du trou-du-cul seront données au président. La nouvelle partie peut alors démarrer.

# V. Points de synchronisation dans l'application 

1. Attente d'une partie.

Comme mentionné ci-dessus, une partie ne peut commencer que lorsque quatre joueurs sont réunis. Il nous faut donc un système qui permette d'indiquer aux clients le nombre de joueurs en attente et de les avertir lorsqu'une partie a été trouvé. Pour ce faire, nous avons pris la décision de mettre en application un pattern observer. 
Le server hérite de l'interface Observable. À chaque fois qu'un Client va rejoindre le serveur, un objet ClientObserver va lui être affecté. De plus, une variable indiquant le nombre de joueur en attente va être incrémentée. À chaque fois que la valeur de cette variable est modifiée, le server va notifier tout ses ClientObserver. Cette mécanique peut être observée dans la classe RmiServer du projet presidentServer.

2. Fin d'une manche

À la fin d'une manche, les joueurs doivent décider s'ils veulent continuer la partie. Il suffit qu'un seul des joueurs decide de ne plus vouloir jouer pour mettre fin à la partie. Ce mécanisme est représenté dans la classe KeepPlaying du projet presidentService.
Le serveur va créer un thread pour chaque joueur. Tant que le serveur n'a pas obtenu la réponse de chaque joueur ou qu'un joueur ne souhaite plus jouer ou qu'aucune réponse n'a été donné depuis 30 secondes, le serveur va attendre 30 secondes. À chaque fois qu'un joueur va donner sa réponse, il va notifier le serveur. Si aucune des conditions d'arrêt de la partie n'est obtenue, la partie peut continuer. 

3. Echange de cartes

Comme mentionné ci-dessus, les joueurs doivent échanger des cartes entre eux dès le début de la deuxième manche. Le mécanisme d'échange est décrit dans la méthode exchangeCardsPhase de la classe President du projet presidentService. Le serveur va créer un ExchangerRunnable pour chaque joueur. Ce Runnable va utiliser un objet Exchanger pour mener à bien son échange. 

4. La partie

