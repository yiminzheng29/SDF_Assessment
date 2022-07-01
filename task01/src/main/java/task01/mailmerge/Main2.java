package task01.mailmerge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * Hello world!
 *
 */
public class Main2 
{
    public static void main( String[] args ) throws Exception
    {
        String rawFile = args[0];
        String temp = args[1];

        MailMerge2 mMerge2 = new MailMerge2();

        Reader reader = new FileReader(rawFile);
        BufferedReader br = new BufferedReader(reader);

        String data = br.readLine();

        while(data!=null) {
            data = br.readLine();
            mMerge2.read(data);
        }
        mMerge2.generateData(temp);



        reader.close();
    }
}
