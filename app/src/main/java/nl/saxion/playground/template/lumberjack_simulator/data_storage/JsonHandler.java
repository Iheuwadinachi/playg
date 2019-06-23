package nl.saxion.playground.template.lumberjack_simulator.data_storage;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Adomas Aleksandravicius, Michael Cornelisse
 */
public class JsonHandler {

    private Context context;

    public JsonHandler(Context context) {
        this.context = context;
    }

    public void loadConstants() {
        FileReader reader = null;
        File file = new File(context.getFilesDir() + "/save.json");
        try {
            reader = new FileReader(file);
            Gson gson = new Gson();
            Save.setInstance(gson.fromJson(reader, Save.class));

            Save save = Save.getInstance();

            /**
             * Load Save into Constants
             */
            Constants.coins = save.getCoins();
            Constants.prices = save.getPrices();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(reader).close();
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveConstants() {
        Save save = Save.getInstance();
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
                Objects.requireNonNull(outputStream).close();
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
