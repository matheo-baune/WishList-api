package wishlist;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;


@OpenAPIDefinition(
        info = @Info(
                title = "Christmas Gift List API",
                version = "0.0.1",
                description = "API for managing Christmas gift lists, groups, and reservations",
                contact = @Contact(
                        name = "Baune Mathéo",
                        email = "matheo.baune-pro@gmail.com"
                ), license = @License(
                name = "Apache 2.0",
                url = "http://springdoc.org"
        )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Server")
        }
)
@SpringBootApplication
public class ProjectWishListApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        //TODO - Add a check to see if the environment variables are set
        // Set environment variables
        System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
        System.setProperty("DB_USERNAME", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
        System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
        System.setProperty("JWT_SECRET", Objects.requireNonNull(dotenv.get("JWT_SECRET")));
        System.setProperty("JWT_EXPIRATION", Objects.requireNonNull(dotenv.get("JWT_EXPIRATION")));
        SpringApplication.run(ProjectWishListApplication.class, args);
    }

}
