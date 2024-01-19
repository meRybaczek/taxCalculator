package Umowa;

public enum TypUmowy {
    UOP(1, "Umowa o pracę"),
    UZ(2, "Umowa zlecenie");
    private final String typUmowy;
    private final int numer;

    TypUmowy(int numer, String typUmowy) {
        this.numer = numer;
        this.typUmowy = typUmowy;
    }

    @Override
    public String toString() {
        return numer + " --> " + typUmowy;
    }

    public static boolean isTypUmowyExists(int kodUmowy){
        for (TypUmowy value : values()) {
            if (value.numer == kodUmowy)
                return true;
        }
        return false;
    }
}

