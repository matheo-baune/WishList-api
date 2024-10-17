package wishlist;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

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
}
