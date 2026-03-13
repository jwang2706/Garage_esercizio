import java.time.LocalDateTime;

public class Posto {
    private boolean riservato;
    private Sosta sosta;

    public Posto(boolean riservato) {
        this.riservato = riservato;
        this.sosta = null;
    }

    public boolean isRiservato() {
        return riservato;
    }

    public boolean isOccupato() {
        return sosta != null;
    }

    public void inserisciVeicolo(String targa, String tipo, String tipoVeicoloRiservato, LocalDateTime inizioSosta) {
        Veicolo veicolo = null;

        if (riservato) {
            switch (tipo) {
                case "A":
                case "a":
                    veicolo = new Macchina(targa, tipoVeicoloRiservato);
                    break;
                case "M":
                case "m":
                    veicolo = new Motocicletta(targa, tipoVeicoloRiservato);
                    break;
                case "F":
                case "f":
                    veicolo = new Furgone(targa, tipoVeicoloRiservato);
                    break;
                default:
                    System.out.println("Veicolo inesistente!");
                    return;
            }
        } else {
            switch (tipo) {
                case "A":
                case "a":
                    veicolo = new Macchina(targa, null);
                    break;
                case "M":
                case "m":
                    veicolo = new Motocicletta(targa, null);
                    break;
                case "F":
                case "f":
                    veicolo = new Furgone(targa, null);
                    break;
                default:
                    System.out.println("Veicolo inesistente!");
                    return;
            }
        }

        sosta = new Sosta(veicolo, inizioSosta);
    }

    public double terminaSosta(LocalDateTime fineSosta) {
        if (sosta == null) {
            return -1;
        }

        double costo = sosta.calcolaCosto(fineSosta, riservato);
        sosta = null;
        return costo;
    }

    public String ottieniTarga() {
        if (sosta == null) {
            return null;
        }
        return sosta.getVeicolo().getTarga();
    }
}