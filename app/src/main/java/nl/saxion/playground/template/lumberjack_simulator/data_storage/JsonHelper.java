package nl.saxion.playground.template.lumberjack_simulator.data_storage;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Adomas Aleksandravicius, Michael Cornelisse
 */
public class JsonHelper {

    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    public String readJSON(String fileName) {
        FileReader reader = null;
        File file = new File(context.getFilesDir() + "/" + fileName);
        try {
            reader = new FileReader(file);
            Gson gson = new Gson();
            Constants constants = gson.fromJson(reader, Constants.class);

            return constants.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public void loadConstants() {
        FileReader reader = null;
        File file = new File(context.getFilesDir() + "/save.json");
        try {
            reader = new FileReader(file);
            Gson gson = new Gson();
            Save save = gson.fromJson(reader, Save.class);

            /**
             * Load Save into Constants
             */
            Constants.coins = save.getCoins();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveConstants() {
        Save save = new Save();
        String jsonString = new Gson().toJson(save, Save.class);
        FileOutputStream outputStream = null;
        File file = new File(context.getFilesDir() + "/save.json");
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(jsonString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
