//  Classe del database degli utenti e tutti i metodi collegati
class UserDatabase {
    //  Array di oggetti Task
    var userDatabase: ArrayList<User> = ArrayList()

    //  Contatore per gli ID (dato che gli ID devono essere univoci)
    var contatoreID: Int = 1

    /*
     CREATE
     */

    //  Funzione che crea un nuovo utente
    fun createUser(username: String, email: String, password: String, secretQuestion: String, secretAnswer: String) {
        //  Tutti i dati tranne l'ID sono passati al richiamo della funzione
        userDatabase.add(User(contatoreID, username, email, password, secretQuestion, secretAnswer))
        //  Stampo che la registrazione dell'utente è stata completata e incremento di 1 il contatore degli ID
        println("\n\tRegistrazione completata.")
        contatoreID += 1
    }


    /*
     READ
     */

    //  Funzione che stampa il database se non è vuoto
    fun readUser() {
        //  Se il database non è vuoto
        if(userDatabase.isNotEmpty()) {
            println()
            //  Cicla ogni elemento del database degli utenti
            for (i in 0 until userDatabase.size) {
                //  Stampa l'ID dell'utente, lo username, e l'email
                println("${userDatabase[i].id}) Username: ${userDatabase[i].username} - Email: ${userDatabase[i].email}")
            }
        }
        //  Se il database è vuoto
        else {
            //  Stampa che non ci sono utenti
            println("\n\tNessun utente registrato.")
        }
    }

    /*
     UPDATE
     */


    //  Funzione che cambia la password a un utente che ha come ID il valore passato come parametro
    fun cambiaPassword(usernameOEmailUtente: String) {
        //  Variabile usata per contenere la posizione dell'utente con ID richiesto
        var num = 0
        //  Variabile per leggere l'input dell'utente
        var leggiString: String = ""

        //  Ciclo il database degli utenti
        for (i in 0 until userDatabase.size) {
            //  Se l'IDUtente passato come parametro è uguale all'id dell'utente nella posizione i del database
            if (usernameOEmailUtente==userDatabase[i].username || usernameOEmailUtente==userDatabase[i].email) {
                //
                num = i
                break
            }
        }
        println("\nRispondi alla seguente domanda per poter cambiare la password")
        println("${userDatabase[num].secretQuestion}")
        leggiString = readLine().toString()
        if (leggiString.equals(userDatabase[num].secretAnswer)) {
            println("\nImmetti nuova password.")
            leggiString = readLine().toString()
            userDatabase[num].password = leggiString
            println("\n\tPassword salvata.")
            //mettere i controlli sulla password
        }
        else {
            println("\nRisposta sbagliata.")
        }
    }

    /*
     DELETE
     */

    fun deleteUser(username: String){
        var controllo: Boolean = false
        var num = 0
        for (i in 0 until userDatabase.size) {
            if (userDatabase[i].username==username) {
                controllo = true
                num = i
                break
            }
        }

        if (controllo == true) {
            userDatabase.removeAt(num)
            println("\n\tUtente eliminato.")
        }
        else {
            println("\n\tUtente con USERNAME: ${username} non esistente.")
        }
    }


    /*
    FUNZIONE DI CONTROLLO
     */

    fun checkUsernameAvailability(username: String): Boolean {
        var controllo: Boolean = true
        for (i in 0 until userDatabase.size) {
            if (userDatabase[i].username==username) {
                controllo = false
                break
            }
        }
        return controllo
    }

    fun checkEmailAvailability(email: String): Boolean {
        var controllo: Boolean = true
        for (i in 0 until userDatabase.size) {
            if (userDatabase[i].email==email) {
                controllo = false
                break
            }
        }
        return controllo
    }

    fun login(usernameOemail: String, password: String): Int? {
        var controllo1: Boolean = false
        var controllo2: Boolean = false
        var num: Int = 0

        for (i in 0 until userDatabase.size) {
            if (usernameOemail==userDatabase[i].username || usernameOemail==userDatabase[i].email) {
                num = i
                controllo1 = true
                break
            }
        }
        if (!controllo1) {
            return null
        }
        if (password == userDatabase[num].password) {
            controllo2 = true
        }
        if (controllo2) {
            return userDatabase[num].id
        }
        else {
            return null
        }
    }


    fun checkUserDatabaseEmpty(): Boolean {
        if(userDatabase.isEmpty()) {
            return true
        }
        else {
            return false
        }
    }

}