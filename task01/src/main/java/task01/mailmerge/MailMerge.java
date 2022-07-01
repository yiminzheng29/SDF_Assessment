package task01.mailmerge;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MailMerge {
    
    private Map<String, List<List<String>>> rawData = new HashMap<>();

    public void read(String data) {
        if ((data==null) || (data.trim().length()<=0)) {
            return;
        }
        String[] inputs = data.split(",");
        String fName = removeQuotes(inputs[0]);
        String lName = removeQuotes(inputs[1]);
        String st = removeQuotes(inputs[2]);
        String yrs = removeQuotes(inputs[3]);

        addData(fName, lName, st, yrs);
    }

    private String removeQuotes(String row) {
        return row.replace("\"", "");
    }

    private void addData(String firstName, String lastName, String street, String years) {
        if (!rawData.containsKey(firstName)) {
            rawData.put(firstName, newEntry());
        }
        List<List<String>> entry = rawData.get(firstName);
        entry.get(0).add(lastName);
        entry.get(1).add(street);
        entry.get(2).add(years);
    }
    
    private List<List<String>> newEntry() {
        List<List<String>> maiLList = new LinkedList<>();
        for (int i=0; i<3; i++) {
            maiLList.add(new LinkedList<String>());
        }
        return maiLList;
    }

    public void printList() {
        System.out.println(rawData);
    }


    public void generateData(String output) throws IOException{
        List<String> contents = new LinkedList<>();
        for (String key:rawData.keySet()) {
            List<List<String>> entry = rawData.get(key);
            String line0 = "%s %s".formatted(key, entry.get(0)).replace("[", "").replace("]", "");
            String line1 = "%s\n".formatted(entry.get(1)).replace("[", "").replace("]", "").replaceAll("\\\\n", "\n");
            String line2 = "Dear %s,\n".formatted(key).replace("[", "").replace("]", "");
            String line3 = "Thank you for staying with us over these %s years.".formatted(entry.get(2)).replace("[", "").replace("]", "");
            String line4 = "\n";


            contents.add(line0);
            contents.add(line1);
            contents.add(line2);
            contents.add(line3);
            contents.add(line4);
        }
        

        FileWriter writer = new FileWriter(output);
        for (String st:contents) {
            writer.write(st + System.lineSeparator());
        }
        writer.flush();
        writer.close();
    }    

}
    
    


