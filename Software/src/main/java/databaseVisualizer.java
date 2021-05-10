import com.google.gson.Gson;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.json.stream.JsonLocation;
import javax.json.stream.JsonParser;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
/**
 * @author Crunchify.com
 * How to Read JSON Object From File in Java?
 */

public class databaseVisualizer {


    public static void databaseVisualizer() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("course.json"));
        JSONObject jsonObject = (JSONObject)obj;
        try {

            String name = (String)jsonObject.get("Products");
            String course = (String)jsonObject.get("Référence");

            System.out.println("Name: " + name);
            System.out.println("Course: " + course);
            System.out.println("Subjects:");

        } catch(Exception e) {
            e.printStackTrace();
        }
            }
        }


