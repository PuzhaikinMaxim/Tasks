package task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    private static Scanner sc = new Scanner(System.in);

    private static Search searchStatus = Search.FINDING_ID;

    private static Integer currentId;

    public static void main(String[] args) {
        String valuesLocation = args[0];
        List<String> values = getLinesFromFile(valuesLocation);
        String testsLocation = args[1];
        List<String> tests = getLinesFromFile(testsLocation);
        Map<Integer, String> parsedValues = parseValues(values);
        for(int i = 0; i < tests.size(); i++) {
            char[] charArray = tests.get(i).toCharArray();
            for(int j = 0; j < charArray.length; j++){
                findValues(tests.get(i), j);
                if(searchStatus == Search.FINDING_ID){
                    j = findId(tests.get(i), j);
                }
                if(searchStatus == Search.FOUND_ID){
                    j = setTestValue(tests.get(i), j, parsedValues, tests, i);
                }
            }
            System.out.println(tests.get(i));
        }
    }

    private static Integer findId(String line, Integer startAt) {
        Pattern pattern =  Pattern.compile("\"id\": (.*?),");
        if(startAt > line.length()) return line.length();
        Matcher matcher = pattern.matcher(line).region(startAt, line.length());
        if(matcher.find()){
            searchStatus = Search.FOUND_ID;
            currentId = Integer.parseInt(matcher.group(1));
            return matcher.end();
        }
        return line.length();
    }

    private static Integer findValues(String line, Integer startAt) {
        Pattern pattern =  Pattern.compile("\"values\": ");
        if(startAt > line.length()) return line.length();
        Matcher matcher = pattern.matcher(line).region(startAt, line.length());
        if(matcher.find()){
            searchStatus = Search.FINDING_ID;
            return matcher.end();
        }
        return startAt;
    }

    private static Integer setTestValue(
            String line,
            Integer startAt,
            Map<Integer, String> parsedValues,
            List<String> tests,
            Integer linePosition
    ) {
        Pattern pattern =  Pattern.compile("\"value\": (\".*?\")");
        Matcher matcher = pattern.matcher(line).region(startAt, line.length());
        if(matcher.find()){
            String nextLine = matcher.replaceFirst("\"value\": \""+ parsedValues.get(currentId) +"\"");
            if(!nextLine.equals("")) {
                line = nextLine;
            }
            tests.set(linePosition,line);
            searchStatus = Search.FINDING_ID;
            return matcher.end();
        }
        return line.length();
    }

    private static List<String> getLinesFromFile(String location) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader file1Reader = new BufferedReader(new FileReader(location));
            String line = file1Reader.readLine();
            while (line != null){
                lines.add(line);
                line = file1Reader.readLine();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return lines;
    }

    private static Map<Integer, String> parseValues(List<String> values) {
        HashMap<Integer, String> map = new HashMap<>();
        String res = String.join("", values)
                .replaceAll("[\\[\\] ]","")
                .replace("values","")
                .replace("{\"\":","");
        String[] valueList = res
                .substring(0, res.length()-1)
                .replace("},{","|")
                .replace("{","")
                .split("\\|");
        for(int i = 0; i < valueList.length; i++){
            valueList[i] = valueList[i]
                    .replace("\"","")
                    .replace("id", "")
                    .replace("value", "")
                    .replace(":", "")
                    .replace("}","");
            String[] field = valueList[i].split(",");
            map.put(Integer.parseInt(field[0]),field[1]);
        }
        return map;
    }

    private enum Search {
        FINDING_ID,
        FOUND_ID
    }
}
