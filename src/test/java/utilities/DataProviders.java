package utilities;

import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProviders{

    //Method 1 - LoginData
    @DataProvider (name = "loginData")
    public static Object[][] getData(){
        //If the @DataProvider is in a different class than the test method, ensure that the @DataProvider method is static
        return new Object[][] {
                // Scenario 1: Valid username and password (should login successfully)
                { "TC_1.1", "standard_user", "secret_sauce", true, null },

                // Scenario 2: Invalid username and password (should fail)
                { "TC_1.2", "Anoja", "announymous", false, "Epic sadface: Username and password do not match any user in this service" },

                // Scenario 3: Valid username and invalid password
                { "TC_1.3", "standard_user", "announymous", false, "Epic sadface: Username and password do not match any user in this service"},

                // Scenario 4: Invalid username and valid password
                { "TC_1.4", "Anoja", "secret_sauce", false, "Epic sadface: Username and password do not match any user in this service" },

                // Scenario 5: Only username provided
                { "TC_1.5", "standard_user", null, false, "Epic sadface: Password is required"},

                // Scenario 6: Only password provided
                { "TC_1.6", null, "secret_sauce", false, "Epic sadface: Username is required" },

                // Scenario 7: No credentials provided
                { "TC_1.7", null, null, false, "Epic sadface: Username is required" }
        };
    }

    //Method 2 - loginDataForKeyBoardActions
    @DataProvider (name = "loginDataForKeyBoardActions")
    public static Object[][] getLoginData(){
        //If the @DataProvider is in a different class than the test method, ensure that the @DataProvider method is static
        return new Object[][] {
                // Scenario 1: Valid username and password (should login successfully)
                { "TC_1.8", "standard_user", "secret_sauce", true, null },

                // Scenario 2: Invalid username and password (should fail)
                { "TC_1.9", "Anoja", "announymous", false, "Epic sadface: Username and password do not match any user in this service" },

                // Scenario 3: Valid username and invalid password
                { "TC_1.10", "standard_user", "announymous", false, "Epic sadface: Username and password do not match any user in this service"},

                // Scenario 4: Invalid username and valid password
                { "TC_1.11", "Anoja", "secret_sauce", false, "Epic sadface: Username and password do not match any user in this service" },

                // Scenario 5: Only username provided
                { "TC_1.12", "standard_user", null, false, "Epic sadface: Password is required"},

                // Scenario 6: Only password provided
                { "TC_1.13", null, "secret_sauce", false, "Epic sadface: Username is required" },

                // Scenario 7: No credentials provided
                { "TC_1.14", null, null, false, "Epic sadface: Username is required" }
        };
    }

    //Method 3 - Login Data From Excel File

    @DataProvider (name = "loginDataFromExcelUtility")
    public static Object[][] getExcelLoginData() throws IOException {
        String filePath = ".//testData/testData.xlsx";
        excelUtility excelUtilityFile1 = new excelUtility(filePath);

        int totalRows = excelUtility.getTotalRowCount("Sheet1");
        int totalCols = excelUtility.getTotalColumnsCount("Sheet1",1);

        //create 2 dimensional array

        Object[][] loginData = new String [totalRows][totalCols];

        //raed data from excel and store into 2 dimensional array

        for(int i = 1; i<= totalRows; i++){ //i rows
            for(int j = 0 ; j< totalCols ; j++){ //j columns
                loginData [i-1][j] = excelUtility.getCellData("Sheet1",i,j); //1,0
            }
        }
        return loginData;
    }


    //method 4 - User Information for Order Checkout
    @DataProvider(name = "userCheckoutInfo")
    public static Object[][] getUserCheckoutInfo(){
        return new Object[][]{
                //provide first,last name and postal code
                {"TC_4.1","jone","jone","AD100023",true,null},
                //fist and last name , postal code not given
                {"TC_4.2","jone","jone",null,false,"Error: Postal Code is required"},
                //only first name and postal code provided
                {"TC_4.3","jone",null,"AD100023",false,"Error: Last Name is required"},
                //lastname and postal code provided
                {"TC_4.4",null,"jone","AD100023",false,"Error: First Name is required"},
                //first ,last names and postal code not provided
                {"TC_4.5",null,null,null,false,"Error: First Name is required"},
                //Empty strings
                {"TC_4.6","","","",false,"Error: First Name is required"}
        };
    }

    @DataProvider(name = "userCheckoutInfo1")
    public static Object[][] getUserCheckoutInfo1(){
        return new Object[][]{
                //provide first,last name and postal code
                {"TC_4.1","jone","jone","AD100023",true,null}
        };
    }

    //method 5
    @DataProvider(name = "productIndexes")
    public static Object[][] productIndexes() {
        return new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}
        };
    }



    }



