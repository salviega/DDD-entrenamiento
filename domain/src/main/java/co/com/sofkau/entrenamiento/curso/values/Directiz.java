package co.com.sofkau.entrenamiento.curso.values;

import co.com.sofka.domain.generic.ValueObject;

public class Directiz implements ValueObject<String> {

    private final String description;

    public  Directiz(String description) {
        this.description = description;
    }
    @Override
    public String value() {
        return this.description;
    }
}
