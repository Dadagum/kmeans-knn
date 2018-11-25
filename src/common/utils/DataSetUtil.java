package common.utils;

import common.bean.Sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataSetUtil {

    private DataSetUtil(){}

    public static List<List<Double>> loadFeatures(String fileName) throws IOException {
        List<List<Double>> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            List<Double> featureList = new ArrayList<>();
            String[] features = line.split(",");
            for (int i = 0; i < features.length - 1; i++) {
                featureList.add(Double.parseDouble(features[i]));
            }
            result.add(featureList);
        }
        reader.close();
        return result;
    }

    public static List<Sample> loadSamples(String fileName) throws IOException {
        List<Sample> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            List<Double> featureList = new ArrayList<>();
            Sample sample = new Sample();
            String[] features = line.split(",");
            for (int i = 0; i < features.length - 1; i++) {
                featureList.add(Double.parseDouble(features[i]));
            }
            sample.setFeatures(featureList);
            sample.setCategory(features[features.length-1]);
            result.add(sample);
        }
        reader.close();
        return result;
    }

}
