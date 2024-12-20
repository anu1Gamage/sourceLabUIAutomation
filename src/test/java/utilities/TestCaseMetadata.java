package utilities;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestCaseMetadata {
    private static JSONObject loginMetaData;
    private static JSONObject inventoryPageMetaData;
    private static JSONObject dataDrivenLoginMetaData;
    private  static JSONObject cartPageMetaData;
    private static JSONObject e2eTestMetaData;

    static {
        try {
            loginMetaData = loadMetadata("src/test/resources/testMetadata/loginTestMetaData.json");
            inventoryPageMetaData = loadMetadata("src/test/resources/testMetadata/inventoryPageTestMetaData.json");
            dataDrivenLoginMetaData = loadMetadata("src/test/resources/testMetadata/dataDrivenLoginTestMetaData.json");
            cartPageMetaData = loadMetadata("src/test/resources/testMetadata/cartPageTestMetaData.json");
        } catch (Exception e) {
            // Log the error or throw a more descriptive exception
            e.printStackTrace();
            throw new RuntimeException("Failed to load testMetaData files: " + e.getMessage());
        }
    }

    private static JSONObject loadMetadata(String filePath) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONObject(content);
    }

    public static String getDescription(String testCaseID, String module) {
        return getMetadataValue(testCaseID, module, "description");
    }

    public static String getScenario(String testCaseID, String module) {
        return getMetadataValue(testCaseID, module, "scenario");
    }

    public static String getPriority(String testCaseID, String module) {
        return getMetadataValue(testCaseID, module, "priority");
    }

    public static String getSeverity(String testCaseID, String module) {
        return getMetadataValue(testCaseID, module, "severity");
    }

    public static String getAuthor(String testCaseID, String module) {
        return getMetadataValue(testCaseID, module, "author");
    }

    private static String getMetadataValue(String testCaseID, String module, String key) {
        JSONObject metadata = getMetadataByModule(module);
        return metadata.optJSONObject(testCaseID) != null ? metadata.getJSONObject(testCaseID).optString(key, "N/A") : "N/A";
    }

    private static JSONObject getMetadataByModule(String module) {
        switch (module.toLowerCase()) {
            case "login":
                return loginMetaData;
            case "inventory":
                return inventoryPageMetaData;
            case "dataDrivenLogin":
                return dataDrivenLoginMetaData;
            case "cartPage":
            return cartPageMetaData;
            default:
                System.err.println("Invalid module name: " + module + ". Returning empty metadata.");
                return new JSONObject(); // Return empty JSON to prevent crashes
        }
    }
}
