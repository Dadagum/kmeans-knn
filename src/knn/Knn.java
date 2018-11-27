package knn;

import common.bean.Sample;
import common.utils.CalculateUtil;

import java.util.*;
import java.util.stream.Collectors;


class IndexDistanceRecord<T>{

     private T category;

     private double distance;

     IndexDistanceRecord(T category, double distance) {
        this.category = category;
        this.distance = distance;
    }

    T getCategory() {
        return category;
    }

    double getDistance() {
        return distance;
    }
}

public class Knn {


    public static <T> void run(List<Sample<T>> train, List<Sample<T>> test, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k must be bigger than zero");
        }

        for (int s = 0; s < test.size(); s++) {
            List<IndexDistanceRecord<T>> records = new ArrayList<>();

            for (int i = 0; i < train.size(); i++) {
                double distance = CalculateUtil.getDistance(test.get(s).getFeatures(), train.get(i).getFeatures());
                records.add(new IndexDistanceRecord<>(train.get(i).getCategory(), distance));
            }

            T category = records
                    .stream()
                    .sorted(Comparator.comparingDouble(IndexDistanceRecord::getDistance))
                    .limit(k)
                    .collect(Collectors.groupingBy(IndexDistanceRecord::getCategory, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Comparator.comparingLong(Map.Entry::getValue))
                    .get()
                    .getKey();

            test.get(s).setCategory(category);

        }
    }

}
