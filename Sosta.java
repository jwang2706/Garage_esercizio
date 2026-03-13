import java.time.Duration;
import java.time.LocalDateTime;

public class Sosta {
    private Veicolo veicolo;
    private LocalDateTime inizioSosta;

    public Sosta(Veicolo veicolo, LocalDateTime inizioSosta) {
        this.veicolo = veicolo;
        this.inizioSosta = inizioSosta;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public LocalDateTime getInizioSosta() {
        return inizioSosta;
    }

    public double calcolaCosto(LocalDateTime fineSosta, boolean postoRiservato) {
        if (postoRiservato) {
            return 0;
        }

        Duration durata = Duration.between(inizioSosta, fineSosta);
        long ore = durata.toHours();

        if (ore == 0) {
            ore = 1; // minimo 1 ora
        }

        return veicolo.getTariffaOraria() * ore;
    }
}