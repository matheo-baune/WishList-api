package wishlist;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectWishListApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        //TODO - Add a check to see if the environment variables are set
        // Set environment variables
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        SpringApplication.run(ProjectWishListApplication.class, args);
    }

}
