# TODOLIST APP

![app logo](https://user-images.githubusercontent.com/78272736/205112782-194b9830-5480-4575-8084-2a3aae7b1195.png)


Mid-term app realizzata in **Kotlin** per il corso Android App Developer (*Vianova/Experis*)

**Richieste obbligatorie:** 
1. L'app prevede l'inserimento di task (*todo*) e la possibilità di segnare le stesse come completate
2. Creare un sistema CRUD (*Create, Read, Update, Delete*) isolato. Le task devono essere integrate con tutte le funzioni CRUD funzionanti sulle medesime.
3. Il sistema si deve chiudere previo conferma

**Richieste aggiuntive scelte:** <br/><br/>
A. Rendere possibile avere gruppi di task nominativi. Far si che si possa scegliere se e a quale gruppo di task accedere dal menu iniziale, dopo aver fatto visualizzare i nomi (*se esistenti*) delle liste di task.<br/>
B. Login system funzionante che prevede la possibilità di recuperare la password, la presenza di username unico e richiesta dell'email anch'essa con obbligo di unicità

>**IDE**: *IntelliJ*
**TECNOLOGIA**: *Kotlin*
**DEVELOPERS**: *William Hourigan, Simone Di Meglio*

## Struttura del progetto

<img width="412" alt="Project Structure" src="https://user-images.githubusercontent.com/78272736/205112549-a5a04c6e-09f3-4055-8ad0-56bf6a8708b2.png">


## Funzionamento app

Entrando nell'app si accede al primo menù che prevede 3 possibili scelte
 
1) Accesso
2) Iscrizione
3) Uscita 

**Accesso**: L'accesso è possibile se sono presenti utenti registrati. In caso di utenti precedentemente registrati l'app richiede username o email, successivamente la password. Se si commette per 3 volte un errore nell'inserimento della password, l'app permette, attraverso una procedura di sicurrezza tramite domanda e risposta, di cambiare la password inserendone una nuova. 

**Iscrizione**: L'iscrizione prevede l'inserimento di diversi campi, quali username, email, password, domanda segreta, risposta segreta. Username e email dovranno essere diversi da username ed email già presenti nel sistema (*l'app provvederà automaticamente avvisando l'utente in caso di username o email già usate*)

**Uscita**: L'uscita chiude l'app, previa richiesta di conferma. 

Ad accesso effettuato ci troveremo di fronte ad un secondo menu: 

1) Crea Task
2) Visualizza Task 
3) Portare una Task nello status "completata"
4) Riportare una Task nello status "non completata"
5) Eliminare una Task
6) Uscire

**Creare una Task**: l'app richiederà il nome della Task, in seguito sarà possibile inserire una SubTask (*ovvero dei ToDo interni alla Task generata*) o completare la creazione della Task.

**Visualizzare Task**: Se presenti, verranno visualizzate le Task inserite dall'utente (*solo ed esclusivamente quelle inserite dall'utente che al momento ha effettuato il login, non saranno visibili le task degli altri utenti*) con la seguente struttura 

> *ID: x (ID della task), Nome_task: x (nome della task inserita), Completamento: x (status "completata" o "non completata")*

Nel caso di SubTask si potrà visualizzare le stesse dall'interno di questo menù.

**Completare Task**:  L'app permette di modificare lo status di una task da "non completata" a "completata" (*non completata" = false, "completata" = true*)

**Riportare una Task a "non completata"**:  L'app permette di modificare lo status di una task da "completata" a "non completata" (*non completata" = false, "completata" = true*)

**Eliminare Task**:  L'app permette di eliminare completamente una Task dalla lista delle Task inserite.

**Uscire**:  Previa richiesta di conferma, si potrà uscire dal menù interno per ritornare al menù di accesso/login

## Classi custom

Sono state create le seguenti classi:

- Task
- User
- TaskDatabase
- UserDatabase

**Task**: Classe relativa alle singole task che vengono inserite dall'utente che si registra o accede all'app. La Classe prevede un ID, il nome della task stessa e l'ID dell'utente che l'ha creata. 

**User**: Classe relativa al singolo utente che si registra all'app. La Classe prevede ID, username, email, password, secretQuestion (*domanda di sicurezza per recupero password*) e secretAnswer (*risposta alla secretQuestion*)

**TaskDatabase**: Classe che simula un database, usata per gestire le task inserite dagli utenti

**UserDatabase**: Classe che simula un database, usata per gestire le registrazioni degli utenti

## Roadmap

L'app prevede l'inserimento in futuro delle seguenti features: 

 1. Interfaccia UI (*Android Studio*)
 2. Funzione per cambio nome di una Task (*update*)
 3. Check della password (*regex*)
