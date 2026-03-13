import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Garage {
    private Posto[] posti;
    private ArrayList<Giornata> listaGiornate;

    public Garage() {
        this.posti = new Posto[100];
        this.listaGiornate = new ArrayList<>();
    }

    public void inserisciMacchina(String targa, String tipoDiVeicolo, String tipoDiVeicoloRiservato,
                                  int posto, boolean riservato, LocalDateTime ingresso) {
        int i = 0;

        if (posto != -1 && posti[posto] == null) {
            posti[posto] = new Posto(riservato);
            posti[posto].inserisciVeicolo(targa, tipoDiVeicolo, tipoDiVeicoloRiservato, ingresso);

        } else if (posto != -1 && posti[posto] != null) {
            System.out.println("Il posto selezionato è già occupato.");
            System.out.println("Verrai assegnato ad un altro posto automaticamente!");

            while (i < posti.length && posti[i] != null) {
                i++;
            }

            if (i < posti.length) {
                posti[i] = new Posto(riservato);
                posti[i].inserisciVeicolo(targa, tipoDiVeicolo, tipoDiVeicoloRiservato, ingresso);
            } else {
                System.out.println("Garage pieno!");
            }

        } else if (posto == -1) {
            while (i < posti.length && posti[i] != null) {
                i++;
            }

            if (i < posti.length) {
                posti[i] = new Posto(riservato);
                posti[i].inserisciVeicolo(targa, tipoDiVeicolo, tipoDiVeicoloRiservato, ingresso);
            } else {
                System.out.println("Garage pieno!");
            }
        }
    }

    public void rimuoviMacchina(String targa, int posto, LocalDateTime uscita) {
        double tariffaDaPagare;
        int i = 0;

        if (posto != -1 && posti[posto] != null) {
            tariffaDaPagare = posti[posto].terminaSosta(uscita);
            System.out.println("Costo parcheggio: " + tariffaDaPagare);
            aggiungiGuadagnoGiornata(tariffaDaPagare, uscita.toLocalDate());
            posti[posto] = null;

        } else {
            while (i < posti.length) {
                if (posti[i] != null && posti[i].ottieniTarga() != null && posti[i].ottieniTarga().equals(targa)) {
                    tariffaDaPagare = posti[i].terminaSosta(uscita);
                    System.out.println("Costo parcheggio: " + tariffaDaPagare);
                    aggiungiGuadagnoGiornata(tariffaDaPagare, uscita.toLocalDate());
                    posti[i] = null;
                    return;
                }
                i++;
            }

            System.out.println("Veicolo non trovato.");
        }
    }

    public double getGuadagnoGiornata(LocalDate dataGiornata) {
        for (int i = 0; i < listaGiornate.size(); i++) {
            if (listaGiornate.get(i).getDataGiornata().equals(dataGiornata)) {
                return listaGiornate.get(i).getImportoTotale();
            }
        }
        return 0;
    }

    public String getPostiLiberi() {
        String postiLiberi = "";
        for (int i = 0; i < posti.length; i++) {
            if (posti[i] == null) {
                postiLiberi += i + ", ";
            }
        }
        return postiLiberi;
    }

    private void aggiungiGuadagnoGiornata(double guadagnoPosto, LocalDate dataGuadagno) {
        boolean trovata = false;

        for (int i = 0; i < listaGiornate.size(); i++) {
            if (listaGiornate.get(i).getDataGiornata().equals(dataGuadagno)) {
                listaGiornate.get(i).setImportoTotale(guadagnoPosto);
                trovata = true;
                break;
            }
        }

        if (!trovata) {
            listaGiornate.add(new Giornata(dataGuadagno, guadagnoPosto));
        }
    }
}