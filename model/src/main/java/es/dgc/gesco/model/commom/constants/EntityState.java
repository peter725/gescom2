package es.dgc.gesco.model.commom.constants;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EntityState {
    ON(1),
    OFF(2);

    private final Integer value;

    EntityState(Integer value) {
        this.value = value;
    }

    public static boolean contains(Integer value) {
        if (value == null || value < EntityState.ON.getValue()) {
            return false;
        }
        return Arrays.stream(EntityState.values()).anyMatch(v -> v.getValue().equals(value));
    }
}

