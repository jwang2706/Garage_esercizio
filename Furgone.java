public class Furgone extends Veicolo {
    private static double tariffaOraria = 2.0;

    public Furgone(String targa, String veicoloRiservato) {
        super(targa, veicoloRiservato);
    }

    public double getTariffaOraria() {
        return tariffaOraria;
    }
}