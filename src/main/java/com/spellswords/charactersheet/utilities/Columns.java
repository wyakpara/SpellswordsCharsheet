package com.spellswords.charactersheet.utilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Columns {

    List<List<String>> lines = new ArrayList<>();
    List<Integer> maxLengths = new ArrayList<>();
    int numColumns = -1;

    public Columns addLine(String... line) {

        if (numColumns == -1){
            numColumns = line.length;
            for(int column = 0; column < numColumns; column++) {
                maxLengths.add(0);
            }
        }

        if (numColumns != line.length) {
            throw new IllegalArgumentException();
        }

        for(int column = 0; column < numColumns; column++) {
            int length = Math
                    .max(
                            maxLengths.get(column),
                            line[column].length()
                    )
                    ;
            maxLengths.set( column, length );
        }

        lines.add( Arrays.asList(line) );

        return this;
    }

    public void print(){
        System.out.println( toString() );
    }

    public String toString(){
        String result = "";
        for(List<String> line : lines) {
            for(int i = 0; i < numColumns; i++) {
                result += pad( line.get(i), maxLengths.get(i) + 1 );
            }
            result += System.lineSeparator();
        }
        return result;
    }

    private String pad(String word, int newLength){
        while (word.length() < newLength) {
            word += " ";
        }
        return word;
    }
}

