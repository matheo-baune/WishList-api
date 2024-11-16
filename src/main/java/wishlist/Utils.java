package wishlist;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.Base64;

public class Utils {
    public static <D, E> void updateEntityFromDTO(@NotNull D dto, @NotNull E entity) {
        Field[] dtoFields = dto.getClass().getDeclaredFields();
        Field[] entityFields = entity.getClass().getDeclaredFields();

        for (Field dtoField : dtoFields) {
            dtoField.setAccessible(true);
            try {
                Object value = dtoField.get(dto);
                if (value != null) {
                    for (Field entityField : entityFields) {
                        if (entityField.getName().equals(dtoField.getName())) {
                            entityField.setAccessible(true);
                            entityField.set(entity, value);
                            break;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static String encodePassword(String rawPassword,String salt) {
        return BCrypt.hashpw(rawPassword, salt);
    }

    public static String generateSalt() {
        return BCrypt.gensalt();
    }

    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
