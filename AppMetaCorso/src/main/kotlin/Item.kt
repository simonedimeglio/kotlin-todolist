//  Classe degli elementi individuali del database
class Item(var id: Int, var nomeTask: String, var IDutente: Int) {
    var completed: Boolean = false

    var liste: ArrayList<String> = ArrayList()


    fun createSubTask(nome: String) {
        liste.add(nome)
    }

    fun printSubTaskList(){
        if (!liste.isEmpty()) {
            for (i in 0 until liste.size) {
                //  Se la task appartiente all'utente
                println("\t${liste[i]}")
            }
        }
    }

    fun checkSubTaskListEmpty(): Boolean {
        if (liste.isEmpty()) {
            return true
        }
        else {
            return false
        }
    }


}
