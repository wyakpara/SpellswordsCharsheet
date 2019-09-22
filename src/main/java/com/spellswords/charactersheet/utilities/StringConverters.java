package com.spellswords.charactersheet.utilities;

import javafx.util.StringConverter;
import java.lang.Number;

public class StringConverters {

    public static StringIntConverter<Number> StringIntConverter = new StringIntConverter<>();

    public static class StringIntConverter<T> extends StringConverter<Number> {
        @Override
        public String toString(Number object) {
            return String.valueOf((int) object);
        }

        @Override
        public Number fromString(String string) {
            return Integer.parseInt(string);
        }
    }
}
