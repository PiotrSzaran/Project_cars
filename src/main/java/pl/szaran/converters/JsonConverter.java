package pl.szaran.converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.szaran.exceptions.MyException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public class JsonConverter<T> {

    private final String jsonFilename;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected JsonConverter(String jsonFilename) {
        this.jsonFilename = jsonFilename;
    }

    // conversion from json to object
    public Optional<T> fromJson() {
        try (FileReader fileReader = new FileReader(jsonFilename)) {
            return Optional.of(gson.fromJson(fileReader, type));
        } catch (IOException e) {
            throw new MyException("FROM JSON - JSON FILENAME EXCEPTION");
        }
    }
}