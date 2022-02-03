import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String text;
        List<String> bigWords = new ArrayList<>();

        try(FileReader fr = new FileReader("test.txt");
            FileWriter fw = new FileWriter("test2.txt")){
            char[]buffer = new char[256];
            int c;
            while((c = fr.read(buffer)) != -1){
                if(c < 128){
                    buffer = Arrays.copyOf(buffer, c);
                    text = String.valueOf(buffer);
                    bigWords = Arrays.stream(text.split("[ ,.!]")).filter(p -> p.length()>5).toList();
                    StringBuilder sb = new StringBuilder();
                    sb.append(text + "\n");

                    for(String word : bigWords){
                            if (bigWords.indexOf(word) == (bigWords.size()-1)) {
                                sb.append(word);
                            } else {
                                sb.append(word + ", ");
                            }
                    }
                    fw.write(sb.toString());
                }
                else{
                    System.out.println("Длина файла больше 128 символов!");
                    break;
                }
            }
        }
        catch (Exception ex ){
            ex.printStackTrace();
        }
    }
}
