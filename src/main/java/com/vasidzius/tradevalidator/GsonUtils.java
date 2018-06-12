package com.vasidzius.tradevalidator;

import com.google.common.io.Files;
import com.google.gson.*;
import com.vasidzius.tradevalidator.model.TradeInfo;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The type Gson utils create Gson instance with ComplexMapKeySerialization
 * and LocalDate adapter
 */
public class GsonUtils {

    @Getter
    private static final Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    /**
     * Gets json as string from file.json under 'resource' folder
     *
     * @param z the Class
     * @param s the Name of json file
     * @return the json as string
     * @throws IOException the io exception
     */
    public static String getJsonAsString(Class z, String s) throws IOException {
        File file = new File(z.getResource(s).getFile());
        return Files.asCharSource(file, Charset.defaultCharset()).read();
    }

    /**
     * Gets json as string from file.json under 'resource' folder
     *
     * @param jsonName the json name
     * @return the json as string
     */
    @SneakyThrows
    public static String getJsonAsString(String jsonName) {
        URL jsonUrl = Objects.requireNonNull(GsonUtils.class.getClassLoader().getResource(jsonName));
        File file = new File(jsonUrl.getFile());
        return Files.asCharSource(file, Charset.defaultCharset()).read();
    }

    /**
     * Gets body with complex key as json.
     *
     * @param errorMap the error map
     * @return the body with complex key as json
     */
    public static String getBodyWithComplexKeyAsJson(Map<TradeInfo, List<String>> errorMap) {
        return gson.toJson(errorMap);
    }

    /**
     * The type Local date adapter.
     */
    static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
            return LocalDate.parse(json.getAsString());
        }
    }
}
