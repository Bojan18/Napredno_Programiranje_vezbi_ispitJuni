package vlezna;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

class TextProcessor {

    public List<Integer> mostFreq;

    public TextProcessor() {
        mostFreq = new ArrayList<>();
    }

    public void countFreq(List<String> list){
        String line = list.get(0);
        char [] charArr = line.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        char selectedChar = 0;
        int charCounter = 0;

        for (int i = 0; i < charArr.length; i++) {
            selectedChar = charArr[i];

            for (int j = 0; j < charArr.length; j++) {
                if(Character.isAlphabetic(selectedChar)) {
                    charArr[i] = Character.toLowerCase(charArr[i]);
                    selectedChar = Character.toLowerCase(selectedChar);
                    if (selectedChar == charArr[j]) {
                        charCounter++;
                    }
                }
            }
            hashMap.put(selectedChar, charCounter);
            charCounter = 0;
        }

        Map.Entry<Character, Integer> maxVal = null;
        for (Map.Entry<Character, Integer> i : hashMap.entrySet()){
            if(maxVal == null || i.getValue().compareTo(maxVal.getValue()) > 0){
                maxVal = i;
            }
        }
        mostFreq.add(maxVal.getValue());
    }

    public void process(InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        List<String> stringList = new ArrayList<>();
        //Ova e prviot tekst                  - output 3 bukva 't'
        //Najfrekventnaaa bukva e bukvataa a  - output 9 bukva 'a'

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            stringList.add(line);
            countFreq(stringList);
            stringList = new ArrayList<>();
        }

        PrintStream pw = new PrintStream(out);
        for (int i = 0; i < mostFreq.size(); i++) {
            pw.println(mostFreq.get(i));
        }
        pw.flush();

    }
}

public class EntryTask {
    public static void main(String[] args) {
        TextProcessor textProcessor = new TextProcessor();
        textProcessor.process(System.in, System.out);
    }
}