package org;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NumberToText {

    private static final String[] units = {
            "", "один", "два", "три", "четыре", "пять",
            "шесть", "семь", "восемь", "девять"
    };

    private static final String[] unitsThous = {
            "", "одна", "две", "три", "четыре", "пять",
            "шесть", "семь", "восемь", "девять"
    };

    private static final String[] teens = {
            "десять", "одиннадцать", "двенадцать",
            "тринадцать", "четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать", "восемнадцать",
            "девятнадцать"
    };

    private static final String[] tens = {
            "", "", "двадцать", "тридцать",
            "сорок", "пятьдесят",
            "шестьдесят", "семьдесят",
            "восемьдесят", "девяносто"
    };

    private static final String[] hundreds = {
            "", "сто", "двести",
            "триста", "четыреста",
            "пятьсот", "шестьсот",
            "семьсот", "восемьсот",
            "девятьсот"
    };

    private static final ArrayList<String> Xllion = new ArrayList<>();

    private void fileReader(){
        try(BufferedReader reader = new BufferedReader(new FileReader("numberToText.txt")))
        {
            String word;
            while((word = reader.readLine()) != null){
                if(!word.isEmpty())
                    Xllion.add(word.trim());
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    };

    public String numberToText(long number) {
        if (number == 0) return "ноль";

        fileReader();

        StringBuilder text = new StringBuilder();
        int index = 1;

        while (number > 0) {
            int start = (int) (number % 1000);

            if (start != 0) {
            if (index == 2) {
                String word = "тысяча";

                if (number % 10 > 1 && number % 10 < 5) {
                    word = "тысячи";
                } else if (number % 10 > 4 || number % 10 == 0) {
                    word = "тысяч";
                }

                if (number % 100 > 10 && number % 100 < 15) {
                    word = "тысяч";
                }

                text.insert(0, word + ' ');
                text.insert(0, shortTraceThous(start) + ' ');
            } else {
                if (index > 2)  text.insert(0, Xllion.get(index - 3) + thousand(start) + ' ');
                text.insert(0, shortTrace(start) + ' ');
            }
            }

            number /= 1000;
            index++;
        }

        return text.toString().trim();
    }

    private String thousand(int number) {
        String word = "";

        if (number % 10 > 1 && number % 10 < 5) {
            word = "а";
        } else if (number % 10 > 4 || number % 10 == 0) {
            word = "ов";
        }

        if (number % 100 > 10 && number % 100 < 15) {
            word = "ов";
        }

        return word;
    }

    private String shortTrace(int number) {
        StringBuilder words = new StringBuilder();

        if (number >= 100) {
            words.append(hundreds[number / 100]).append(' ');
            number %= 100;
        }

        if (number >= 20) {
            words.append(tens[number / 10]).append(' ');
            number %= 10;
        } else if (number >= 10) {
            words.append(teens[number - 10]).append(' ');
            return words.toString().trim();
        }

        if (number > 0) {
            words.append(units[number]).append(' ');
        }

        return words.toString().trim();
    }

    private String shortTraceThous(int number) {
        StringBuilder words = new StringBuilder();

        if (number >= 100) {
            words.append(hundreds[number / 100]).append(' ');
            number %= 100;
        }

        if (number >= 20) {
            words.append(tens[number / 10]).append(' ');
            number %= 10;
        } else if (number >= 10) {
            words.append(teens[number - 10]).append(' ');
            return words.toString().trim();
        }

        if (number > 0) {
            words.append(unitsThous[number]).append(' ');
        }

        return words.toString().trim();
    }
}
