//  Classe degli elementi individuali del TaskDatabase e tutti i metodi collegati
class Task(var id: Int, var nomeTask: String, var IDutente: Int) {
    var completed: Boolean = false

    //  Array di string che rappresenta le subtask
    var subTask: ArrayList<String> = ArrayList()


    //  Aggiunge una subtask alla lista della task che richiama la funzione
    fun createSubTask(nome: String) {
        //  Aggiunge alla lista la subtask passata tramite nome
        subTask.add(nome)
    }

    //  Stampa tutte le subtask della task che richiama la funzione
    fun printSubTaskList(){
        //  Se la lista delle subtask non è vuota
        if (!subTask.isEmpty()) {
            // Cicla la lista delle subtask
            for (i in 0 until subTask.size) {
                //  stampa la subtask nella posizione i
                println("\t${subTask[i]}")
            }
        }
    }

    //  Controlla se la task che richiama la funzione ha subtask
    fun checkSubTaskListEmpty(): Boolean {
        //  Se la lista delle subtask è vuota ritorna true
        if (subTask.isEmpty()) {
            return true
        }
        //  Se la lista delle subtask non è vuota ritorna falso
        else {
            return false
        }
    }

}
