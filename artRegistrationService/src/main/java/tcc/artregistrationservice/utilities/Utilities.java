package tcc.artregistrationservice.utilities;

import tcc.artregistrationservice.models.Art;

public class Utilities {

    public static boolean notNullNotBlank(String value) {
        return value != null && !value.isBlank();
    }
}
