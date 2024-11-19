package utilities;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestCaseMetadata {
    private static JSONObject loginMetaData;
    private static JSONObject inventoryPageMetaData;


    static{
        try{
           String loginTestMetaDataPath = "src/test/resources/testMetadata/loginTestMetaData.json";
           String inventoryPageTestMetaDataPath = "src/test/resources/testMetadata/inventoryPageTestMetaData.json";

           String loginContent = new String(Files.readAllBytes(Paths.get(loginTestMetaDataPath)));
           String inventoryContent = new String(Files.readAllBytes(Paths.get(inventoryPageTestMetaDataPath)));

            loginMetaData = new JSONObject(loginContent);
            inventoryPageMetaData = new JSONObject(inventoryContent);

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to load testMetaData files:" +e.getMessage());
        }
    }

    public static String getDescription(String testCaseID, String module){
        JSONObject metadata = getMetadataByModule(module);
        return metadata.getJSONObject(testCaseID).getString("description");
    }

    public static String getScenario(String testCaseID, String module){
        JSONObject metadata = getMetadataByModule(module);
        return metadata.getJSONObject(testCaseID).getString("scenario");
    }

    public static String getPriority(String testCaseID, String module) {
        JSONObject metadata = getMetadataByModule(module);
        return metadata.getJSONObject(testCaseID).getString("priority");
    }

    public static String getSeverity(String testCaseID, String module) {
        JSONObject metadata = getMetadataByModule(module);
        return metadata.getJSONObject(testCaseID).getString("severity");
    }

    public static String getAuthor(String testCaseID, String module) {
        JSONObject metadata = getMetadataByModule(module);
        return metadata.getJSONObject(testCaseID).getString("author");
    }

    private static JSONObject getMetadataByModule(String module) {
        switch (module.toLowerCase()) {
            case "login":
                return loginMetaData;
            case "inventory":
                return inventoryPageMetaData;
            default:
                throw new IllegalArgumentException("Invalid module name: " + module);
        }
    }
}
