import java.io.*;

public class FileService {
    private static final String DATA_DIRECTORY = "survey_data";
    
    /* default constructor */
    public FileService() {
        File dataDir = new File(DATA_DIRECTORY);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }
    
    /* takes in survey and filename, saves survey */
    public void saveSurvey(Survey survey, String filename) {
        String filepath = DATA_DIRECTORY + File.separator + filename;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(survey);
            System.out.println("Survey saved successfully to " + filepath);
        } catch (IOException e) {
            System.err.println("Error saving survey: " + e.getMessage());
        }
    }
    
    /* takes in filename, loads and returns survey */
    public Survey loadSurvey(String filename) {
        String filepath = DATA_DIRECTORY + File.separator + filename;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            Survey survey = (Survey) ois.readObject();
            System.out.println("Survey loaded successfully from " + filepath);
            return survey;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading survey: " + e.getMessage());
            return null;
        }
    }
    
    /* takes in response and filename, saves response */
    public void saveResponse(SurveyResponse response, String filename) {
        String filepath = DATA_DIRECTORY + File.separator + filename;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(response);
            System.out.println("Response saved successfully to " + filepath);
        } catch (IOException e) {
            System.err.println("Error saving response: " + e.getMessage());
        }
    }
    
    /* takes in filename, loads and returns response */
    public SurveyResponse loadResponse(String filename) {
        String filepath = DATA_DIRECTORY + File.separator + filename;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            SurveyResponse response = (SurveyResponse) ois.readObject();
            System.out.println("Response loaded successfully from " + filepath);
            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading response: " + e.getMessage());
            return null;
        }
    }
    
    /* returns list of survey files only (excludes tests) */
    public String[] listSurveyFiles() {
        File dataDir = new File(DATA_DIRECTORY);
        if (!dataDir.exists()) {
            return new String[0];
        }
        
        // get all survey/test files
        File[] files = dataDir.listFiles((dir, name) -> 
            (name.toLowerCase().endsWith(".dat") || name.toLowerCase().endsWith(".ser"))
            && !name.toLowerCase().contains("response"));
        
        if (files == null) {
            return new String[0];
        }
        
        // filter to only include Survey instances (not Test)
        java.util.List<String> surveyFiles = new java.util.ArrayList<>();
        for (File file : files) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = ois.readObject();
                if (obj instanceof Survey && !(obj instanceof Test)) {
                    surveyFiles.add(file.getName());
                }
            } catch (IOException | ClassNotFoundException e) {
                // skip files that can't be read
            }
        }
        
        return surveyFiles.toArray(new String[0]);
    }
    
    /* returns list of response files */
    public String[] listResponseFiles() {
        File dataDir = new File(DATA_DIRECTORY);
        if (!dataDir.exists()) {
            return new String[0];
        }
        
        File[] files = dataDir.listFiles((dir, name) -> 
            name.toLowerCase().contains("response"));
        
        if (files == null) {
            return new String[0];
        }
        
        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getName();
        }
        
        return fileNames;
    }

    /* returns list of test files only */
    public String[] listTestFiles() {
        File dataDir = new File(DATA_DIRECTORY);
        if (!dataDir.exists()) {
            return new String[0];
        }
        
        // get all survey/test files
        File[] files = dataDir.listFiles((dir, name) -> 
            (name.toLowerCase().endsWith(".dat") || name.toLowerCase().endsWith(".ser"))
            && !name.toLowerCase().contains("response"));
        
        if (files == null) {
            return new String[0];
        }
        
        // filter to only include Test instances
        java.util.List<String> testFiles = new java.util.ArrayList<>();
        for (File file : files) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = ois.readObject();
                if (obj instanceof Test) {
                    testFiles.add(file.getName());
                }
            } catch (IOException | ClassNotFoundException e) {
                // skip files that can't be read
            }
        }
        
        return testFiles.toArray(new String[0]);
    }
}
