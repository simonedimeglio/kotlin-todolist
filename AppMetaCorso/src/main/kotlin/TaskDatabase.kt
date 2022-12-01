//  Classe del database delle task e tutti i metodi collegati
class TaskDatabase {
    //  Array di oggetti Task
    var database: ArrayList<Task> = ArrayList()

    //  Contatore per gli ID (dato che gli ID devono essere univoci)
    var contatoreID: Int = 1




    /*
     CREATE
     */

    //  Funzione che crea una nuova task e le relative subtask
    fun create(nome: String, idUTENTE: Int) {
        //  Aggiungo una nuova task all'array database passandogli ID della task, nome della task, e l'ID dell'utente. Stampo che la task è stata creata e incremento degli ID
        database.add(Task(contatoreID, nome, idUTENTE))

        //  Variabile di controllo per effetuare un while
        var controllo = false
        //  Variabile per leggere l'input dell'utente
        var leggiString = ""

        //  Ciclo che aggiunge subtask alla task e termina quando l'utente decide di smettere di aggiungere subtask
        while (!controllo) {

            //  Stampo le opzione dell'utente e poi richiedo la scelta dell'utente in input che viene salvato come stringa
            println("\nScrivi il nome di una subtask per aggiungere una subtask.\nScrivi 'esci' per completare la task principale")
            leggiString = readLine().toString()

            //  Se l'utente ha deciso di uscire
            if (leggiString.equals("esci")) {
                //  Pongo controllo a true per uscire dal while al termine di questo ciclo
                controllo = true
            }
            //  Se l'utente ha deciso di immettere una subtask
            else {
                //  Crea una subtask all'ultima task del database (che è appena stata creata) e la chiama come l'input dell'utente
                database[database.size-1].createSubTask(leggiString)
            }
        }
        //  Stampo che la task è stata creata e aumento di 1 il contatore degli ID
        println("\n\tTask creata.")
        contatoreID++
    }

    /*
    READ
     */

    //  Funzione che stampa la parte del database dell'utente se non è vuoto
    fun read(idUTENTE: Int) {
        //  Se il database non è vuoto
        if(!checkDBempty(idUTENTE)) {
            println()
            //  Ciclo l'array database da inizio a fine
            for (i in 0 until database.size) {
                //  Se la task appartiente all'utente
                if (database[i].IDutente==idUTENTE) {
                    //  Stampo l'id, il nome della task, e lo stato di completamento della task alla posizione i del database
                    println("ID: ${database[i].id}, Nome_task: ${database[i].nomeTask}, Completamento: ${database[i].completed}")
                }
            }
        }
        //  Se il database è vuoto
        else {
            println("\n\tIl database è vuoto.")
        }
    }

    //  Funzione che stampa una task specificata e tutte le sue subtask
    fun readSubTask(idUTENTE: Int, idTask: Int) {

        //  Variabile di supporto per ricordarmi la posizione nell'array della task desiderata
        var num = 0
        //  Variabile di controllo per vedere se esiste la task con id uguale all'idTask passato come parametro
        var controllo = false

        //  Ciclo l'array database
        for (i in 0 until database.size) {
            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se la task in posizione i del database ha id uguale all'idTask passato come parametro
                if (database[i].id==idTask) {
                    //  La variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente tramite break
                    controllo = true
                    num = i
                    break
                }
            }
        }
        //  Se è stata trovata la task desiderata
        if (controllo) {
            //  Se la lista delle subtask non è vuota
            if (!database[num].checkSubTaskListEmpty()) {
                //  Stampo la task desiderata, e richiamo la funzione per stampare tutte le subtask della task, salvata in posizione num dell'array
                println("ID: ${database[num].id}, Nome_task: ${database[num].nomeTask}, Completamento: ${database[num].completed}")
                database[num].printSubTaskList()
            }
            //  Se la lista delle subtask è vuota
            else {
                //  Stampo che la task con ID passato come parametro non ha subtask
                println("\n\tLa task con ID: ${idTask} non ha subtask.")
            }
        }
        //  Stampo che la task con ID passato come parametro non esiste
        else {
            println("\n\tLa task con ID: ${idTask} non esiste.")
        }
    }


    /*
    UPDATE
     */

    //  Funzione che completa una task con ID specificato, se appartiene all'utente che ha come ID idUTENTE
    fun completaTask(IDTask: Int, idUTENTE: Int) {
        //  Variabile di controllo per vedere se esiste la task con id uguale all'idTask passato come parametro
        var controllo: Boolean = false

        //  Variabile usato per contenere la posizione della task con ID richiesto
        var num = 0

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {

            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se la task in posizione i del database ha id uguale all'IDTask passato come parametro
                if (database[i].id==IDTask) {
                    //  La variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente tramite il break
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
            println("\n\tTask con ID: ${IDTask} non esiste.")
        }
    }

    //  Funzione che decompleta una task con ID specificato
    fun deCompletaTask(IDTask: Int, idUTENTE: Int) {
        //  Variabile di controllo per vedere se esiste la task con id uguale all'idTask passato come parametro
        var controllo: Boolean = false

        //  Variabile usato per contenere la posizione della task con ID richiesto
        var num = 0

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {
            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se la task in posizione i del database ha id uguale all'ID passato come parametro
                if (database[i].id==IDTask) {
                    //  La variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente tramite il break
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
                println("\n\tTask nuovamente da completare.")
            }
        }
        //  Se la task con ID passato come parametro non esiste
        else {
            //  Stampo che non esista una task che ha come ID il valore passato come parametro
            println("\n\tTask con ID: ${IDTask} non esiste.")
        }
    }

    /*
    DELETE
     */

    //  Funzione che elimina una task con ID specificato
    fun eliminaTask(IDTask: Int, idUTENTE: Int){

        //  Variabile di controllo per vedere se esiste la task con id uguale all'idTask passato come parametro
        var controllo: Boolean = false

        //  Variabile usato per contenere la posizione della task con ID richiesto
        var num = 0

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {
            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se la task in posizione i del database ha id uguale all'ID passato come parametro
                if (database[i].id==IDTask) {
                    //  La variabile di controllo viene messa a true, num prende il valore di i, e usciamo dal ciclo for immediatamente tramite il break
                    controllo = true
                    num = i
                    break
                }
            }
        }

        //  Se la task con ID passato come parametro esiste
        if (controllo == true) {
            //  Elimino la task in posizione num e stampo di aver eliminato la task
            database.removeAt(num)
            println("\n\tHai eliminato la task.")
        }
        //  Se la task con ID passato come parametro non esiste
        else {
            //  Stampo che non esiste una task che ha come ID il valore passato come parametro
            println("\n\tTask con ID: ${IDTask} non esiste.")
        }
    }


    /*
    FUNZIONI DI SUPPORTO
     */

    //  Funzione che controlla se la parte del database appartenente all'utente è vuota
    fun checkDBempty(idUTENTE: Int): Boolean {

        //  Variabile di controllo per vedere se la parte del database appartenente all'utente è vuoto
        var controllo = true

        //  Ciclo il database
        for (i in 0 until database.size) {
            //  Se trovo nel database una task che appartiene all'utente
            if(database[i].IDutente==idUTENTE) {
                //  Pongo il controllo a falso ed esco dal cilco immediatamente tramite il break
                controllo = false
                break
            }
        }
        //  Ritorno il valore di controllo (se la parte del database dell'utente è vuoto)
        return controllo
    }


    //  Funzione che controlla se tutte le task appartenenti all'utente nel database sono completate
    fun checkIsAllCompleted(idUTENTE: Int): Boolean {
        //  Variabile di controllo che serve a vedere se tutte le task sono completate
        var controllo = true

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {
            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se la task in posizione i del database ha il completamento a false
                if (database[i].completed==false) {
                    //  Metto la variabile di controllo a false per segnalare che non tutte le task sono completate e esco dal ciclo for tramite il break
                    controllo = false
                    break
                }
            }
        }
        //  Ritorno il valore di controllo (se la parte del database dell'utente ha tutte le task completate)
        return controllo
    }


    //  Funzione che controlla se tutte le task appartenenti all'utente nel database sono completate
    //  (Uguale alla funzione di prima ma all'if controllo se è true invece che false)
    fun checkNoneIsCompleted(idUTENTE: Int): Boolean {
        //  Variabile di controllo che serve a vedere se tutte le task sono non completate
        var controllo = true

        //  Ciclo ogni elemento del database
        for (i in 0 until database.size) {
            //  Se la task appartiente all'utente
            if (database[i].IDutente==idUTENTE) {
                //  Se la task in posizione i del database ha il completamento a true
                if (database[i].completed==true) {
                    //  Metto la variabile di controllo a true per segnalare che non tutte le task sono non completate e esco dal ciclo for tramite il break
                    controllo = false
                    break
                }
            }

        }
        //  Ritorno il valore di controllo (se la parte del database dell'utente ha tutte le task non completate)
        return controllo
    }

}