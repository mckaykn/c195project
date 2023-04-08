package nielson.c195projectmkn.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserActivityLogger {

    public static void logActivity(String username, boolean success) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String status = success ? "Success" : "Failure";
        String message = timestamp + " - " + username + " - " + status + "\n";
        String fileName = "login_activity.txt";

        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file, true);
            writer.write(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
