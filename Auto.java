public class Auto extends Veicolo{
    private static double tariffaOraria = 1.5;

    public Auto(String targa, String veicoloRiservato){
        super(targa, veicoloRiservato);
    }

    public double getTariffaOraria(){
        return tariffaOraria;
    }
}