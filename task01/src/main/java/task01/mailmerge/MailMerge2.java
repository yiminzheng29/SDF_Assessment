package task01.mailmerge;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MailMerge2 {
    
    private Map<String, List<List<String>>> rawData = new HashMap<>();

    public void read(String data) {
        if ((data==null) || (data.trim().length()<=0)) {
            return;
        }
        String[] inputs = data.split(",");
        String salutations = removeQuotes(inputs[0]);
        String lName = removeQuotes(inputs[1]);
        String pkg = removeQuotes(inputs[2]);
        String price = removeQuotes(inputs[3]);

        addData(salutations, lName, pkg, price);
    }

    private String removeQuotes(String row) {
        return row.replace("\"", "");
    }

    private void addData(String salutations, String lName, String pkg, String price) {
        if (!rawData.containsKey(lName)) {
            rawData.put(lName, newEntry());
        }
        List<List<String>> entry = rawData.get(lName);
        entry.get(0).add(salutations);
        entry.get(1).add(pkg);
        entry.get(2).add(price);
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
            String line0 = "Dear %s".formatted(entry.get(0)).replace("[", "").replace("]", "");
            String linex = "%s,\n".formatted(key);
            String line1 = "Thank you for your interest in our new %s package!".formatted(entry.get(1)).replace("[", "");
            String line2 = "For the 2022 Spring promotion, you can sign up for the low low price of $%s!\n".formatted(entry.get(2)).replace("[", "").replace("]", "");
            String line3 = "\n";
            String line4 = "Safe travels!\n";


            contents.add(line0);
            contents.add(linex);
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
    
    


