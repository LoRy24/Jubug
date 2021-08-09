package jubug.test;

import com.github.lory24.jubug.util.ProprietiesReader;

public class TestClass {
    public static void main(String[] args) {
        System.out.println(ProprietiesReader.parseProprietiesFromString("#ciao bambini\nname=hello\nnamewawa").getValues().toString());
    }
}
