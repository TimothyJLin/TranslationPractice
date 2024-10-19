import java.util.*;
import java.io.*;
public class Spanish {
    public static void main(String[] args) {
        Spanish spanish=new Spanish();
        ArrayList<SpanishEnglishWords> wordDict=new ArrayList<SpanishEnglishWords>();
        
        try {
            wordDict=spanish.placeTextFileInDict();
            
            //System.out.println(wordDict.get(0).getSpanishWord()+" BOZO CLOWN BOZO");
            char caseType;
            boolean inputSuccess=false;
            while (inputSuccess==false) {
                String input=System.console().readLine("Input the character 's' if you want to practice translating from Spanish to English.\nInput the character 'e' if you want to practice translating from English to Spanish.\nInput the character 'r' if you want to practice doing it randomly.\n");
                switch(input.toLowerCase()) {
                    case "s":
                        caseType='s';
                        inputSuccess=true;
                        break;
                    case "e":
                        englishToSpanishTest(wordDict);
                        inputSuccess=true;
                        break;
                    case "r":
                        caseType='r';
                        inputSuccess=true;
                        break;
                    default:
                        System.out.println("Command not understood. Please input either 's', 'e', or 'r'.");
                }
            }
        } catch (Exception e) {
            System.out.println("I hate exceptions");
            e.getStackTrace();
        }

    }

    public static void englishToSpanishTest(ArrayList<SpanishEnglishWords> wordDictionary) {    
        Collections.shuffle(wordDictionary);
        String englishWord;
        String spanishWord;
        boolean correctAnswer;
        ArrayList<SpanishEnglishWords> wrongWords=new ArrayList<SpanishEnglishWords>();
        for (int i=0; i<wordDictionary.size(); i++) {
            correctAnswer=false;
            englishWord=wordDictionary.get(i).getEnglishWord();
            spanishWord=wordDictionary.get(i).getSpanishWord();
            while (correctAnswer==false) {
                String input=System.console().readLine("What is \""+englishWord+ "\" in Spanish?\n");
                if (input.toLowerCase().equals(spanishWord.toLowerCase())) {
                    System.out.println("Correct!");
                    correctAnswer=true;
                } else if (input.toLowerCase().equals("idk")) {
                    System.out.println("The correct answer is "+spanishWord);
                    if (wrongWords.contains(wordDictionary.get(i))==false) {
                        wrongWords.add(wordDictionary.get(i));
                    }
                    correctAnswer=true;
                } else {
                    if (wrongWords.contains(wordDictionary.get(i))==false) {
                        wrongWords.add(wordDictionary.get(i));
                    }
                    System.out.println("Incorrect :(");
                }
            }
        }
        if (wrongWords.size()>0) {
            System.out.println("You finished a round! Here are the words you got wrong. [to be implemented later]. Let's try to fix our mistakes!");
            englishToSpanishTest(wrongWords);
        } else {
            System.out.println("NICE JOB! YOU GOT THEM ALL!!!!");
            System.exit(-1);
        }
    }


    public ArrayList<SpanishEnglishWords> placeTextFileInDict() throws Exception {
        Spanish spanish=new Spanish();
        ArrayList<SpanishEnglishWords> words=new ArrayList<SpanishEnglishWords>();
        try {
            File dictionaryText=new File("./SpanishNumbers.txt");
            //File dictionaryText=new File("./dictionary.txt");    
            FileReader fr=new FileReader(dictionaryText);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while ((line=br.readLine())!=null) {
                words.add(spanish.getSpanishEnglishWord(line));

             //  dict.put(spanish.parseColon(line)[0], spanish.parseColon(line)[1]); 
            }
            return words;
        } catch (Exception e) {
            throw new Exception("frick you");
        }
    }

    public SpanishEnglishWords getSpanishEnglishWord(String line) {
        SpanishEnglishWords sew=new SpanishEnglishWords();
        String spanishWord="";
        String englishWord="";
        boolean isSpanish=true;
        for (char character:line.toCharArray()) {
            if (character!=':') {
                if (isSpanish) {
                    spanishWord+=character;
                } else {
                    englishWord+=character;
                }
            } else {
                isSpanish=false;
            }
        }
        sew.setSpanishWord(spanishWord);
        sew.setEnglishWord(englishWord);
        return sew;
    }
}

