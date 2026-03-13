public class Moto extends Veicolo{
    private static double tariffaOraria = 1;

    public Moto(String targa,String veicoloRiservato){
        super(targa, veicoloRiservato);
    }

    public double getTariffaOraria(){
        return tariffaOraria;
    }
}