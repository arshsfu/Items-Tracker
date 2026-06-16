package cmpt213.assignment.loanitemstracker.model;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.Duration;

/**
 * Gson adapter used to serialize and deserialize
 * Duration objects.
 * Stores Duration values as a total number of seconds
 * in JSON and reconstructs Duration objects when loading.
 */
public class DurationAdapter implements JsonSerializer<Duration>, JsonDeserializer<Duration> {

    /**
     * Converts a Duration object into a JSON value.
     *
     * @param duration The Duration to serialize.
     * @param type The actual type of the source object.
     * @param context Serialization context.
     * @return A JSON representation of the Duration.
     */
    @Override
    public JsonElement serialize(Duration duration,Type type,JsonSerializationContext context) {
        return new JsonPrimitive(duration.getSeconds());
    }

    /**
     * Converts JSON data back into a Duration object.
     *
     * @param json JSON data containing the duration in seconds.
     * @param type The type of object being deserialized.
     * @param context Deserialization context.
     * @return The reconstructed Duration.
     * @throws JsonParseException If the duration cannot be parsed.
     */
    @Override
    public Duration deserialize(JsonElement json,Type type,JsonDeserializationContext context) throws JsonParseException {
        return Duration.ofSeconds(json.getAsLong());
    }
}