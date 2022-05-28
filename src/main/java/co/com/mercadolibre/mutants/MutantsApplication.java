package co.com.mercadolibre.mutants;

import org.apache.log4j.Logger;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;

/**
 * Main Application.
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "MercadoLibre Mutants", version = "1.0", description = "Challenge MercadoLibre Mutants"))
public class MutantsApplication {

    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(MutantsApplication.class);

    /**
     * Metodo principal para iniciar la aplicacion.
     *
     * @param args - argunmentos.
     */
    public static void main(String[] args) {
        logger.info("Starting MutantsApplication...");
        Instant init = Instant.now();
        SpringApplication.run(MutantsApplication.class, args);
        logger.info(String.format("Started MutantsApplication in %s ms", Duration.between(init, Instant.now()).toMillis()));
    }

}
