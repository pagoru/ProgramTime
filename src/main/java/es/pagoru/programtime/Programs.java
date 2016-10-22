package es.pagoru.programtime;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pablo on 22/10/2016.
 */
public class Programs {

    private static final String PATH = System.getProperty("user.home") + "\\Documents\\";

    public static void incrementProgram(String programName){

        String filePath = PATH + "programtime.json";

        try{
            File f = new File(filePath);
            if(!f.exists()) {
                f.createNewFile();

                System.out.println("Creating file...");
                FileWriter w = new FileWriter(filePath);
                w.write("[]");
                w.close();
            }

            JsonArray array = new JsonParser().parse(new JsonReader(new FileReader(filePath))).getAsJsonArray();

            Iterator<JsonElement> iter = array.iterator();
            List<JsonElement> copy = new ArrayList<>();
            while (iter.hasNext()){
                copy.add(iter.next());
            }
            JsonElement program = copy.stream().filter(jsonElement -> jsonElement.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(programName)).findFirst().orElse(null);

            if(program != null){
                JsonObject programObj = program.getAsJsonObject();
                programObj.addProperty("time", programObj.get("time").getAsLong() + 1l);
            } else {
                JsonObject newProgramObj = new JsonObject();
                newProgramObj.addProperty("name", programName);
                newProgramObj.addProperty("time", 0l);
                array.add(newProgramObj);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(filePath);
            writer.write(gson.toJson(array));
            writer.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
