package com.example.demo.uitl;

import com.example.demo.exception.InvalidParam;
import lombok.NonNull;

import java.util.UUID;

public class ValidateUtils {

    public static UUID UUIDFormat(@NonNull final String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException i) {
            throw new InvalidParam("ok");
        }
    }

}
