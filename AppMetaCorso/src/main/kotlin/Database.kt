class Database {
    //  Array di oggetti Item
    var database: ArrayList<Item> = ArrayList()

    //  Contatore per gli ID
    var contatoreID: Int = 1




    /*
     CREATE
     */

    //  Funzione che crea una nuova task
    fun create(nome: String, idUTENTE: Int) {
        //  Aggiungo un nuovo item all'array passandogli ID e nome della task, stampo che la task è stata creata e incremento degli ID
        database.add(Item(contatoreID, nome, idUTENTE))

        var controllo = false
        var leggiString = ""
        while (!controllo) {
            println("\nScrivi il nome di una subtask per aggiungere una subtask.\nScrivi 'esci' per completare la task principale")
            leggiString = readLine().toString()
            if (leggiString.equals("esci")) {
                controllo = true
            }
            else {
                database[database.size-1].createSubTask(leggiString)
            }
        }
        println("\n\tTask creata.")
        contatoreID++
    }

    /*
    READ
     */

    //  Funzione che stampa il database se non è vuoto
    fun read(idUTENTE: Int) {
        //  Se il database non è vuoto
        if(!checkDBempty(idUTENTE)) {
            println()
            //  Ciclo l'array database e stampo ogni elemento
            for (i in 0 until database.size) {
                //  Se la task appartiente all'utente
                if (database[i].IDutente==idUTENTE) {
                    println("ID: ${database[i].id}, Nome_task: ${database[i].nomeTask}, Completamento: ${database[i].completed}")
                }
            }
        }
        //  Se il database è vuoto
        else {
            println("\n\tIl database è vuoto.")
        }
    }

    fun readSubTask(idUTENTE: Int, idTask: Int) {
        var num = 0
        var controllo = false

        for (i in 0 until database.size) {
            if (database[i].IDutente==idUTENTE) {
                if (database[i].id==idTask) {
                    //  La variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente
                    controllo = true
                    num = i
                    break
                }
            }
        }
        if (controllo) {
            if (!database[num].checkSubTaskListEmpty()) {
                println("ID: ${database[num].id}, Nome_task: ${database[num].nomeTask}, Completamento: ${database[num].completed}")
                database[num].printSubTaskList()
            }
            else {
                println("\n\tLa task con ID: ${idTask} non ha subtask.")
            }
        }
        else {
            println("\n\tLa task con ID: ${idTask} non esiste.")
        }
    }


    /*
    UPDATE
     */

    //  Funzione che completa una task con ID specificato
    fun completaTask(ID: Int, idUTENTE: Int) {
        //  Variabile di controllo per controllare se abbiamo trovato una task che ha come ID il valore passato come parametro
        var controllo: Boolean = false

        //  Variabile usato per contenere la posizione della item con ID richiesto
        var num = 0

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {

            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se l'item in posizione i del database ha id uguale all'ID passato come parametro
                if (database[i].id==ID) {
                    //  La variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente
                    controllo = true
                    num = i
                    break
                }
            }
        }

        //  Se la task con ID passato come parametro esiste
        if (controllo) {
            //  Se la task in posizione num ha completed come vero
            if(database[num].completed) {
                //  Stampo che la task era gia stata completata
                println("\n\tLa task è gia completata.")
            }
            //  Se la task in posizione num ha completed come falso
            else {
                //  Metto il completed della task in posizione num a vero e stampo di aver completato la task
                database[num].completed=true
                println("\n\tHai completato la task.")
            }
        }
        //  Se la task con ID passato come parametro non esiste
        else {
            //  Stampo che non esista una task che ha come ID il valore passato come parametro
            println("\n\tTask con ID: ${ID} non esiste.")
        }
    }

    //  Funzione che decompleta una task con ID specificato
    fun deCompletaTask(ID: Int, idUTENTE: Int) {
        //  Variabile di controllo per controllare se abbiamo trovato una task che ha come ID il valore passato come parametro
        var controllo: Boolean = false

        //  Variabile usato per contenere la posizione della item con ID richiesto
        var num = 0

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {
            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se l'item in posizione i del database ha id uguale all'ID passato come parametro
                if (database[i].id==ID) {
                    //  La variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente
                    controllo = true
                    num = i
                    break
                }
            }
        }

        //  Se la task con ID passato come parametro esiste
        if (controllo) {
            //  Se la task in posizione num ha completed come falso
            if(!database[num].completed) {
                //  Stampo che la task era gia non completata
                println("\n\tLa task è gia non completata.")
            }
            //  Se la task in posizione num ha completed come vero
            else {
                //  Metto il completed della task in posizione num a falso e stampo di aver decompletato la task
                database[num].completed=false
                println("\n\tHai decompletato la task.")
            }
        }
        //  Se la task con ID passato come parametro non esiste
        else {
            //  Stampo che non esista una task che ha come ID il valore passato come parametro
            println("\n\tTask con ID: ${ID} non esiste.")
        }
    }


    //CREA TASK CHE CAMBIA IL NOME DELLA TASK


    /*
    DELETE
     */

    //  Funzione che elimina una task con ID specificato
    fun eliminaTask(ID: Int, idUTENTE: Int){

        //  Variabile di controllo per controllare se abbiamo trovato una task che ha come ID il valore passato come parametro
        var controllo: Boolean = false

        //  Variabile usato per contenere la posizione della item con ID richiesto
        var num = 0

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {
            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se l'item in posizione i del database ha id uguale all'ID passato come parametro
                if (database[i].id==ID) {
                    //  La variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente
                    controllo = true
                    num = i
                    break
                }
            }
        }

        //  Se la task con ID passato come parametro esiste
        if (controllo == true) {
            //  Elimino task in posizione num e stampo di aver eliminato la task
            database.removeAt(num)
            println("\n\tHai eliminato la task.")
        }
        //  Se la task con ID passato come parametro non esiste
        else {
            //  Stampo che non esista una task che ha come ID il valore passato come parametro
            println("\n\tTask con ID: ${ID} non esiste.")
        }
    }


    /*
    FUNZIONI DI SUPPORTO
     */

    //  Funzione che controlla se il database è vuoto, ritorno true se è vuoto, ritorno false se non è vuoto
    fun checkDBempty(idUTENTE: Int): Boolean {
        var controllo = true

        for (i in 0 until database.size) {
            if(database[i].IDutente==idUTENTE) {
                controllo = false
                break
            }
        }
        return controllo
    }


    //  Funzione che controlla se tutte le task del database sono completate, ritorna true se sono tutte completate, altrimenti ritorna false
    fun checkIsAllCompleted(idUTENTE: Int): Boolean {
        //  Variabile di controllo che serve a vedere se tutte le task sono completate
        var controllo = true

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {
            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se la task in posizione i del database ha il completamento a falso
                if (database[i].completed==false) {
                    //  Metto la variabile di controllo a false per segnalare che non tutte le task sono completate e esco dal ciclo for
                    controllo = false
                    break
                }
            }
        }
        //  Ritorno il valore di controllo
        return controllo
    }


    //  Funzione che controlla se tutte le task del database sono non completate, ritorna true se sono tutte non completate, altrimenti ritorna false
    //  (Uguale alla funzione di prima ma all'if controllo se è true invece che false)
    fun checkNoneIsCompleted(idUTENTE: Int): Boolean {
        //  Variabile di controllo che serve a vedere se tutte le task sono completate
        var controllo = true

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {
            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se la task in posizione i del database ha il completamento a falso
                if (database[i].completed==true) {
                    //  Metto la variabile di controllo a false per segnalare che non tutte le task sono non completate e esco dal ciclo for
                    controllo = false
                    break
                }
            }

        }
        //  Ritorno il valore di controllo
        return controllo
    }

}