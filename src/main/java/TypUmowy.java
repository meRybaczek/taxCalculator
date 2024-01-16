import java.util.NoSuchElementException;

public enum TypUmowy {
    UOP(1, "Umowa o pracę"),
    UZ(2, "Umowa zlecenie");
    private final String typUmowy;
    private final int numer;

    TypUmowy(int numer, String typUmowy) {
        this.numer = numer;
        this.typUmowy = typUmowy;
    }

    public String getTypUmowy() {
        return typUmowy;
    }

    public int getNumer() {
        return numer;
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

