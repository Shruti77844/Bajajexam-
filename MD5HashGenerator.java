import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MD5HashGenerator {

    public static void main(String[] args) {
        try {
            // Read and parse the JSON file
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(new FileReader("input.json"));
            JSONObject student = (JSONObject) obj.get("student");

            // Get first_name and roll_number
            String fname = ((String) student.get("first_name")).toLowerCase();
            String roll = ((String) student.get("roll_number")).toLowerCase();

            // Concatenate
            String data = fname + roll;

            // Create MD5 hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] hashBytes = md.digest();

            // Convert bytes to hex string
            StringBuilder hash = new StringBuilder();
            for (byte b : hashBytes) {
                hash.append(String.format("%02x", b));
            }

            // Save to output.txt
            FileWriter fw = new FileWriter("output.txt");
            fw.write(hash.toString());
            fw.close();

            // Print to console also
            System.out.println("MD5 Hash: " + hash);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}