package cmpt213.assignment.loanitemstracker.model;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * Gson adapter used to serialize and deserialize
 * LocalDate objects.
 * Converts LocalDate values to strings when saving
 * and reconstructs LocalDate objects when loading.
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>,JsonDeserializer<LocalDate> {

    /**
     * Converts a LocalDate object into a JSON string.
     *
     * @param date The LocalDate to serialize.
     * @param type The actual type of the source object.
     * @param context Serialization context.
     * @return A JSON representation of the LocalDate.
     */
    @Override
    public JsonElement serialize(LocalDate date,Type type,JsonSerializationContext context) {
        return new JsonPrimitive(date.toString());
    }

    /**
     * Converts a JSON string back into a LocalDate object.
     *
     * @param json JSON data containing a date string.
     * @param type The type of object being deserialized.
     * @param context Deserialization context.
     * @return The reconstructed LocalDate.
     * @throws JsonParseException If the date cannot be parsed.
     */
    @Override
    public LocalDate deserialize(JsonElement json,Type type,JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsString());
    }
}