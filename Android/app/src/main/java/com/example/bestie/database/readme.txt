==================={ PREMESSA }===================

Vi chiederei gentilmente di non esportare il db se lo avete riempito con dati "inutili" oppure
tabelle/campi di prova che non sono inerenti al progetto, così da tenerlo pulito e funzionante.
Grazie.
Direzione dell'azienda Bestie SPA


==================={ COME IMPORTARE CORRETTAMENTE IL DATABASE }===================

Essenzialmente bisogna aprire il file bestie.sql presente ins questa cartella con HeidiSQL ed
eseguire la query togliendo TUTTI i commenti (quelli in grigio) e tutta la parte di query finale
di ogni tabella con scritto "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;",
ricordandosi di mettere il ; alla fine di ogni query per la creazione della tabella.

==================={ COME ESPORTARE CORRETTAMENTE IL DATABASE }===================
Salvare il file in formato .sql effettuando il dump degli oggetti, successivamente vi si aprirà una
finestra di popup nella quale dovrete scegliere la directory di destinazione del file da esportare
sotto la voce "nome file". Cliccate l'icona della cartella e scegliete la directory che vi porta
all'interno della cartella dove è situato il file che state leggendo ora (documents/github/ecc...).
Assicuratevi poi che nella parte di sinistra siano selezionate tutte le tabelle del database.
Selezionate infine "Sostituisci dati esistenti" di fianco alla voce "dati" e "File.sql singolo"
di fianco alla voce "Output" ed esportate il tutto. Per controllare che il file sia stato esportato
correttamente fate doppio click su bestie.sql in questa nella cartella del file che state leggendo
ora e controllate che nel codice sql ci siano tutti i dati.
