package TechArrow;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;
/*
 ---------->
 Tech Arrow
 ---------->
 */
public class CompareTwoJsons {

    public static void main(String[] args) {
       JsonElement Json1=getJson1();
        JsonElement Json2=getJson2();


        Gson g = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> firstMap = g.fromJson(Json1, mapType);
        Map<String, Object> secondMap = g.fromJson(Json2, mapType);
        MapDifference<String, Object> difference = Maps.difference(firstMap, secondMap);

        System.out.println("\n\nEntries are missing in Json2\n--------------------------\n");
        difference.entriesOnlyOnLeft().forEach((key, value) -> System.out.println("\n--> " + key + ": " + value));

        System.out.println("\n\nEntries are missing in Json1\n--------------------------\n");
        difference.entriesOnlyOnRight().forEach((key, value) -> System.out.println("\n--> " + key + ": " + value));

        System.out.println("\n\nEntries Mismatching:\n--------------------------\n");
        difference.entriesDiffering().forEach((key, value) -> System.out.println("\n--> " + key + ": " + value));

        System.out.println("\n\nEntries Common:\n--------------------------\n");
        difference.entriesInCommon().forEach((key, value) -> System.out.println("\n--> " + key + ": " + value));
    }
    public static JsonElement getJson1() {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement=null;
        try {
            FileReader fileReader = new FileReader("json1_fileName.json");//Json1 filePath
            jsonElement = jsonParser.parse(fileReader);
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return jsonElement;
    }
    public static JsonElement getJson2() {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement=null;
        try {
            FileReader fileReader = new FileReader("json2_fileName.json");//Json2 filePath
            jsonElement = jsonParser.parse(fileReader);
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return jsonElement;
    }
}
