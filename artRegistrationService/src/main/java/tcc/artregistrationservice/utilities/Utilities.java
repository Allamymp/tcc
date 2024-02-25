package tcc.artregistrationservice.utilities;

public class Utilities {

    public static boolean notNullNotBlank(String value) {
        return value != null && !value.isBlank();
    }
}
