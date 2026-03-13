import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class MainProgram {

    private static Garage garage = new Garage();

    public static void main(String[] args) {
        int scelta;

        do {
            scelta = menu();

            switch (scelta) {
                case 1:
                    ingresso();
                    break;
                case 2:
                    uscita();
                    break;
                case 3:
                    infoGiornata();
                    break;
                case 0:
                    System.out.println("Uscita dal programma in corso...");
                    break;
                default:
                    System.out.println("Opzione non valida!");
                    break;
            }
        } while (scelta != 0);
    }

    private static int menu() {
        int scelta;
        Scanner in = new Scanner(System.in);

        System.out.println("Menu");
        System.out.println("1) Ingresso");
        System.out.println("2) Uscita");
        System.out.println("3) Visualizza incassi di una giornata");
        System.out.print("Scelta: ");
        scelta = in.nextInt();

        return scelta;
    }

    private static void ingresso() {
        Scanner in = new Scanner(System.in);
        int posto;
        String targa, tipoDiVeicolo, tipoVeicoloRiservato, isRiservato, postoCheck;
        boolean riservato;

        System.out.print("Targa: ");
        targa = in.nextLine();

        System.out.print("Tipo di veicolo (A: Auto, M: Motocicletta, F: Furgone): ");
        tipoDiVeicolo = in.nextLine();

        System.out.print("Posto riservato? (Si/No): ");
        isRiservato = in.nextLine();

        if (isRiservato.equalsIgnoreCase("Si")) {
            riservato = true;
            System.out.print("Tipo veicolo riservato (Ambulanza, Polizia, ...): ");
            tipoVeicoloRiservato = in.nextLine();
        } else {
            riservato = false;
            tipoVeicoloRiservato = null;
        }

        System.out.println(garage.getPostiLiberi());
        System.out.print("Scegli un posto libero (vuoto per selezione automatica): ");
        postoCheck = in.nextLine();

        try {
            if (postoCheck.isEmpty()) {
                posto = -1;
            } else {
                posto = Integer.parseInt(postoCheck);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Inserimento non valido. Assegnazione automatica.");
            posto = -1;
        }

        garage.inserisciMacchina(targa, tipoDiVeicolo, tipoVeicoloRiservato, posto, riservato, LocalDateTime.now());
        System.out.println("Ingresso aggiunto correttamente!");
    }

    private static void uscita() {
        Scanner in = new Scanner(System.in);
        int posto = -1;
        String targa = "", checkPosto;

        System.out.print("Posto (vuoto se si vuole usare la targa del veicolo): ");
        checkPosto = in.nextLine();

        if (checkPosto.isEmpty()) {
            System.out.print("Targa: ");
            targa = in.nextLine();
        } else {
            try {
                posto = Integer.parseInt(checkPosto);
            } catch (NumberFormatException ex) {
                System.out.println("Posto non valido.");
                return;
            }
        }

        garage.rimuoviMacchina(targa, posto, LocalDateTime.now());
    }

    private static void infoGiornata() {
        Scanner in = new Scanner(System.in);
        LocalDate dataDaCercare;

        System.out.print("Inserisci data (AAAA-MM-GG): ");
        dataDaCercare = LocalDate.parse(in.nextLine());

        System.out.println("Guadagno del giorno " + dataDaCercare + ": €" + garage.getGuadagnoGiornata(dataDaCercare));
    }
}