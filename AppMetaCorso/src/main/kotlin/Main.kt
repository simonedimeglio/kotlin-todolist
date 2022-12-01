fun main(args: Array<String>) {

    //  Istanza della classe UserDatabase
    var userDatabase: UserDatabase = UserDatabase()

    //  Istanza della classe Database
    var taskDatabase: TaskDatabase = TaskDatabase()

    //  Variabili di controllo usate per fare cicli while
    var controllo1 = false
    var controllo2 = false
    var controllo3 = false
    var controllo4 = false
    var controllo5 = false

    //  Variabili per leggere input da utente e fare controlli
    var letturaString1: String? = null
    var letturaString2: String? = null

    //  Crezione di utenti forzata per semplificare il testing
    //userDatabase.createUser("U1", "E1", "P1", "Come stai", "Bene")
    //userDatabase.createUser("U2", "E2", "P2", "Come stai", "Bene")

    //  Entro nel ciclo iscrizione/accesso fino a quando l'utente non da l'input per uscire
    while (!controllo1) {
        //  Stampo le opzione dell'utente e poi richiedo la scelta dell'utente in input che viene salvato come stringa
        println("\nCosa vuoi fare?\n1) Accedi.\n2) Iscriviti.\n3) Esci.")
        letturaString1 = readLine().toString()
        when(letturaString1) {
            //  Se l'utente ha immesso come input "1" (decide di fare l'accesso a un account)
            "1" -> {

                userDatabase.readUser()
                var usernameOemail: String = ""
                var password: String? = null
                var utenteID: Int? = null

                var contatoreErrori: Int = 0

                controllo2 = false

                if(userDatabase.checkUserDatabaseEmpty()) {
                    controllo2 = true
                    println("\nNon ci sono utenti")
                }

                while (!controllo2) {
                    println("\nImmetti Username o Email.")
                    letturaString2 = readLine().toString()
                    usernameOemail = letturaString2

                    println("\nImmetti Password.")
                    letturaString2 = readLine().toString()
                    password = letturaString2

                    utenteID = userDatabase.login(usernameOemail, password)
                    if (utenteID!=null) {
                        println("\n\tLogin effetuato con successo.")
                        controllo2 = true
                    }
                    else {
                        println("\n\tLogin fallito, riprovare.")
                        contatoreErrori += 1
                    }
                    if (contatoreErrori >= 3) {
                        controllo3 = false
                        controllo2 = true
                        while (!controllo3) {
                            println("\nHai sbagliato troppe volte.\nPremi 1 per uscire.\nPremi 2 per recupera password")
                            letturaString2 = readLine().toString()
                            when(letturaString2) {
                                "1" -> {
                                    println("\n\tTornerai alla schermata principale.")
                                    controllo3 = true
                                }
                                "2" -> {
                                    userDatabase.cambiaPassword(usernameOemail)
                                    controllo3 = true
                                }
                                else -> {
                                    println("\n\tHai inserito un dato invalido.")
                                }
                            }
                        }
                    }
                }

                if (utenteID != null) {
                    //  Stampo il database prima di entrare in un ciclo che continua fino a quando l'utente da l'input per farlo smettere
                    taskDatabase.read(utenteID)
                    controllo4 = false
                    while (!controllo4) {
                        //  Stampo le opzione dell'utente e poi richiedo la scelta dell'utente in input che viene salvato come stringa
                        println("\nCosa vuoi fare?\n1) Crea task.\n2) Visualizza i task.\n3) Completa una task.\n4) Poni 'a non completato' la task.\n5) Elimina una task.\n6) Esci.")
                        letturaString1 = readLine().toString()
                        when(letturaString1) {
                            //  Se l'utente ha immesso come input "1" (decide di creare una nuova task)
                            "1" -> {
                                //  Richiedo come input la stringa che sarà utilizzata nella funzione che creera la nuova task
                                println("\nCome vuoi chiamare la task?")
                                letturaString2 = readLine().toString()
                                taskDatabase.create(letturaString2, utenteID)
                            }
                            //

                            //  Se l'utente ha immesso come input "2" (decide di visualizzare le task)
                            "2" -> {
                                //  Richiamo la funziona che stampa il database
                                controllo5 = false
                                while (!controllo5) {
                                    taskDatabase.read(utenteID)
                                    println("\nA quale task vuoi visualizzare le subtask? (metti id).\nSe vuoi uscire scrivi 'esci'.")
                                    letturaString2 = readLine().toString()
                                    if (letturaString2.equals("esci")) {
                                        controllo5 = true
                                    }
                                    else if (isNumeric(letturaString2)) {
                                            taskDatabase.readSubTask(utenteID, letturaString2.toInt())
                                    }
                                    else {
                                        println("\n\tNon hai inserito un ID o 'esci'.")
                                    }
                                }
                            }

                            //  Se l'utente ha immesso come input "3" (decide di completare una task)
                            "3" -> {
                                //  Pongo la variabile di controllo a false per prevenire un errore dove sia rimasto a true da un richiamo precedente
                                controllo5=false

                                //  Se il database è vuoto
                                if (taskDatabase.checkDBempty(utenteID)) {
                                    //  Stampo che il database è vuoto e pongo la variabile di controllo a true per non entrare nel ciclo while
                                    //  (sarebbe inutile entrare nella schermata per completare le task se le task non esistono)
                                    println("\n\tIl database è vuoto")
                                    controllo5 = true
                                }
                                //  Se il database non è vuoto
                                else {
                                    //  Se tutte le task sono gia state completate
                                    if (taskDatabase.checkIsAllCompleted(utenteID)) {
                                        //  Stampo che tutte le task nel database sono gia state completate e pongo la variabile di controllo a true per non entrare nel ciclo while
                                        //  (sarebbe inutile entrare nella schermata per completare le task se le task sono tutte completate)
                                        controllo5 = true
                                        println("\n\tTutte le task sono già completate")
                                    }
                                }
                                //  Entro in un ciclo che continua fino a quando l'utente da l'input per farlo smettere o tutte le task sono completate
                                while (!controllo5) {
                                    //  Stampo il database poi richiedo la scelta dell'utente in input che viene salvato come stringa
                                    taskDatabase.read(utenteID)
                                    println("\nImmetti l'ID della task da completare, o scrivi esci se vuoi uscire.")
                                    letturaString2 = readLine().toString()

                                    //  Se l'utente ha immesso come input "esci" (decide di uscire dalla schermata di completamento delle task)
                                    if(letturaString2=="esci") {
                                        //  Pongo controllo2 a true per uscire dal while al termine di questo ciclo
                                        controllo5 = true
                                    }
                                    //  Se l'utente ha immesso come input un ID valido (decide di completare una task che ha come ID il valore inserito)
                                    else if(isNumeric(letturaString2)) {
                                        //  Richiama la funziona che completa la task con ID inserito (passando alla funziona la stringa contenente l'ID trasformata in un intero)
                                        taskDatabase.completaTask(letturaString2.toInt(), utenteID)
                                    }
                                    //  Se l'utente NON ha immesso come input un numero intero positivo o 'esci'
                                    else {
                                        //  Stampo che i valori inserito non sono adeguati
                                        println("\n\tNon hai inserito un ID o 'esci'.")
                                    }

                                    //  Se tutte le task sono gia state completate
                                    if (taskDatabase.checkIsAllCompleted(utenteID)) {
                                        //  Stampo che tutte le task nel database sono state completate e pongo la variabile di controllo a true per uscire dal while al termine di questo ciclo
                                        controllo5 = true
                                        println("\n\tTutte le task completate")
                                    }
                                }
                            }

                            //  Se l'utente ha immesso come input "4" (decide di decompletare una task)
                            "4" -> {
                                //  Pongo la variabile di controllo a false per prevenire un errore dove sia rimasto a true da un richiamo precedente
                                controllo5=false

                                //  Se il database è vuoto
                                if (taskDatabase.checkDBempty(utenteID)) {
                                    //  Stampo che il database è vuoto e pongo la variabile di controllo a true per non entrare nel ciclo while
                                    //  (sarebbe inutile entrare nella schermata per completare le task se le task non esistono)
                                    println("\n\tIl database è vuoto")
                                    controllo5 = true
                                }
                                else {
                                    //  Se nessuna task è stata completata
                                    if (taskDatabase.checkNoneIsCompleted(utenteID)) {
                                        //  Stampo che tutte le task nel database sono gia non completate e pongo la variabile di controllo a true per non entrare nel ciclo while
                                        //  (sarebbe inutile entrare nella schermata per decompletare le task se le task sono tutte non completate)
                                        controllo5 = true
                                        println("\n\tTutte le task sono già non completate")
                                    }
                                }
                                //  Entro in un ciclo che continua fino a quando l'utente da l'input per farlo smettere o tutte le task sono non completate
                                while (!controllo5) {
                                    //  Stampo il database poi richiedo la scelta dell'utente in input che viene salvato come stringa
                                    taskDatabase.read(utenteID)
                                    println("\nImmetti l'ID della task da riportare a non completato, o scrivi esci se vuoi uscire.")
                                    letturaString2 = readLine().toString()

                                    //  Se l'utente ha immesso come input "esci" (decide di uscire dalla schermata di decompletamento delle task)
                                    if(letturaString2=="esci") {
                                        //  Pongo controllo2 a true per uscire dal while al termine di questo ciclo
                                        controllo5 = true
                                    }
                                    //  Se l'utente ha immesso come input un ID valido (decide di decompletare una task che ha come ID il valore inserito)
                                    else if(isNumeric(letturaString2)) {
                                        //  Richiama la funziona che decompleta la task con ID inserito (passando alla funziona la stringa contenente l'ID trasformata in un intero)
                                        taskDatabase.deCompletaTask(letturaString2.toInt(), utenteID)
                                    }
                                    //  Se l'utente NON ha immesso come input un numero intero positivo o 'esci'
                                    else {
                                        //  Stampo che i valori inserito non sono adeguati
                                        println("\n\tNon hai inserito un ID o 'esci'.")
                                    }

                                    //  Se tutte le task sono gia state decompletate
                                    if (taskDatabase.checkNoneIsCompleted(utenteID)) {
                                        //  Stampo che tutte le task nel database sono state decompletate e pongo la variabile di controllo a true per uscire dal while al termine di questo ciclo
                                        controllo5 = true
                                        println("\n\tTutte le task sono non completate")
                                    }
                                }
                            }

                            //  Se l'utente ha immesso come input "5" (decide di eliminare una task)
                            "5" -> {
                                //  Pongo la variabile di controllo a false per prevenire un errore dove sia rimasto a true da un richiamo precedente
                                controllo5=false

                                //  Se il database è vuoto
                                if (taskDatabase.checkDBempty(utenteID)) {
                                    //  Stampo che il database è vuoto e pongo la variabile di controllo a true per non entrare nel ciclo while
                                    //  (sarebbe inutile entrare nella schermata per completare le task se le task non esistono)
                                    println("\n\tIl database è già vuoto")
                                    controllo5 = true
                                }
                                //  Entro in un ciclo che continua fino a quando l'utente da l'input per farlo smettere o tutte le task sono eliminate
                                while (!controllo5) {
                                    //  Stampo il database poi richiedo la scelta dell'utente in input che viene salvato come stringa
                                    taskDatabase.read(utenteID)
                                    println("\nImmetti l'ID della task da eliminare, o scrivi 'esci' se vuoi uscire.")
                                    letturaString2 = readLine().toString()
                                    //  Se l'utente ha immesso come input "esci" (decide di uscire dalla schermata di decompletamento delle task)
                                    if(letturaString2=="esci") {
                                        //  Pongo controllo2 a true per uscire dal while al termine di questo ciclo
                                        controllo5 = true
                                    }
                                    //  Se l'utente ha immesso come input un ID valido (decide di eliminare una task che ha come ID il valore inserito)
                                    else if(isNumeric(letturaString2)) {
                                        //  Richiama la funziona che elimina la task con ID inserito (passando alla funziona la stringa contenente l'ID trasformata in un intero)
                                        taskDatabase.eliminaTask(letturaString2.toInt(), utenteID)
                                    }
                                    //  Se l'utente NON ha immesso come input un numero intero positivo o 'esci'
                                    else {
                                        //  Stampo che i valori inserito non sono adeguati
                                        println("\n\tNon hai inserito un ID o 'esci'.")
                                    }

                                    //  Se il database è vuoto
                                    if (taskDatabase.checkDBempty(utenteID)) {
                                        //  Stampo che tutte le task nel database sono state eliminate e pongo la variabile di controllo a true per uscire dal while al termine di questo ciclo
                                        controllo5 = true
                                        println("\n\tIl database è vuoto")
                                    }
                                }
                            }

                            //  Se l'utente ha immesso come input "6" (decide di uscire dal programma)
                            "6" -> {
                                //  Stampo che l'utente uscira e pongo la variabile di controllo a true per uscire dal while al termine di questo ciclo (cosi termina il programma)
                                println("\nSei sicuro di voler uscire?\nScrivi 'si' per uscire.")
                                letturaString2 = readLine().toString()
                                if (letturaString2.equals("si")) {
                                    println("\nOra uscirai.")
                                    controllo4 = true
                                }
                                else {
                                    println("\nOperazione annullata.")
                                }
                            }

                            //  Se l'utente non ha immesso come input un numero intero tra 1 e 6
                            else -> {
                                println("\nnon hai inserito un valore valido.")
                            }
                        }
                    }
                }
            }
            //  Se l'utente ha immesso come input "2" (decide di registrare un account)
            "2" -> {
                var username: String = ""
                var email: String = ""
                var password: String? = null
                var secretQuestiion: String? = null
                var secretAnswer: String? = null
                controllo2 = false
                while (!controllo2) {
                    println("\nImmetti Username.")
                    letturaString2 = readLine().toString()
                    if(userDatabase.checkUsernameAvailability(letturaString2)) {
                        username = letturaString2
                        controllo2 = true
                    }
                    else {
                        println("\n\tUsername già utilizzato, sceglierne un altro.")
                    }
                }

                controllo2 = false
                while (!controllo2) {
                    println("\nImmetti Email.")
                    letturaString2 = readLine().toString()
                    if(userDatabase.checkEmailAvailability(letturaString2)) {
                        email = letturaString2
                        controllo2 = true
                    }
                    else {
                        println("\n\tEmail già utilizzato, sceglierne un altro.")
                    }
                }

                println("\nImmetti password.")
                letturaString2 = readLine().toString()
                password = letturaString2

                println("\nImmetti la domanda segreta.")
                letturaString2 = readLine().toString()
                secretQuestiion = letturaString2

                println("\nImmetti la risposta segreta.")
                letturaString2 = readLine().toString()
                secretAnswer = letturaString2

                //fun createUser(username: String, email: String, password: String, secretQuestion: String, secretAnswer: String)
                userDatabase.createUser(username, email, password, secretQuestiion, secretAnswer)

            }
            //  Se l'utente ha immesso come input "3" (decide di uscire dal programma)
            "3" -> {
                println("\nSei sicuro di voler uscire?\nScrivi 'si' per uscire.")
                letturaString2 = readLine().toString()
                if (letturaString2.equals("si")) {
                    println("\nOra uscirai.")
                    controllo1 = true
                }
                else {
                    println("\nOperazione annullata.")
                }
            }
            //  Se l'utente non ha immesso come input un numero intero tra 1 e 3
            else -> {
                println("\nNon hai inserito un valore valido.")
            }
        }
    }
}

//funziona che controlla se tutti i valori in una stringa sono numeri (presa da https://www.baeldung.com/kotlin/check-if-string-is-numeric)
fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}