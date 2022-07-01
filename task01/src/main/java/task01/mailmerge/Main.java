package task01.mailmerge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    
    public static void main(String[] args) throws IOException {
        String rawFile = args[0];
        String temp = args[1];

        MailMerge mMerge = new MailMerge();

        Reader reader = new FileReader(rawFile);
        BufferedReader br = new BufferedReader(reader);

        String data = br.readLine();

        while(data!=null) {
            data = br.readLine();
            mMerge.read(data);
        }
        mMerge.generateData(temp);



        reader.close();

    }
}
