package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataSetUtil {

    private DataSetUtil(){}

    public static List<List<Double>> load(String fileName) throws IOException {
        List<List<Double>> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            List<Double> sample = new ArrayList<>();
            String[] features = line.split(",");
            for (int i = 0; i < features.length - 1; i++) {
                sample.add(Double.parseDouble(features[i]));
            }
            result.add(sample);
        }
        reader.close();
        return result;
    }

}
