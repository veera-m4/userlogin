package com.Project.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
public class generalControl {
    @GetMapping("/getquestions")
    public List<String> getQuestions() throws FileNotFoundException, ParseException {
        JSONParser parser = new JSONParser(new FileReader("output.json"));
        Map<String,String> obj = (Map<String, String>) parser.parse();
        System.out.println(obj);

        Set<String> keys = obj.keySet();
        return new ArrayList<>(keys);
    }

    @GetMapping("/initiallize")
    public void questions() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("What is your favorite color", "Blue");
        jsonObject.put("What is your favorite food", "Biriyani");
        jsonObject.put("What is your pet's name", "Vinoth");
        jsonObject.put("What is your favorite hobby?", "Coding");
        jsonObject.put("Where is your favorite place to travel?", "Goa");
        try {
            FileWriter file = new FileWriter("output.json");
            file.write(jsonObject.toString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(path ="/check",method = RequestMethod.POST)
    public boolean check(HttpServletRequest request,@RequestBody Map<String, Object> payload) throws FileNotFoundException, ParseException, JSONException {
        JSONParser parser = new JSONParser(new FileReader("output.json"));
        Map<String,String> obj = (Map<String, String>) parser.parse();
        System.out.println(obj);
        System.out.println(payload.get("answer"));
        System.out.println(payload.get("question"));
        System.out.println(obj.get(payload.get("question")));
        System.out.println(obj.get(((String)payload.get("question")).trim()).equals(((String)payload.get("answer")).trim()));
        return obj.get(((String)payload.get("question")).trim()).equals(((String)payload.get("answer")).trim());
    }
}
