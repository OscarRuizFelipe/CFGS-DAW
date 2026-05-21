package modelo;

public enum Rol {
    TOP, JUNGLE, MID, ADC, SUPPORT;

    public static Rol fromString(String s) {
        switch (s.toUpperCase()) {
            case "TOP":     return TOP;
            case "JUNGLE":  return JUNGLE;
            case "MID":     return MID;
            case "ADC":     return ADC;
            case "SUPPORT": return SUPPORT;
            default: throw new IllegalArgumentException("Rol no valido: " + s);
        }
    }
}
