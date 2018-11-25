package knn;

import common.bean.Sample;
import common.utils.CalculateUtil;

import java.util.*;


class IndexDistanceRecord<T>{

     T category;

     double distance;

     IndexDistanceRecord(T category, double distance) {
        this.category = category;
        this.distance = distance;
    }
}

public class Knn {


    public static <T> void run(List<Sample<T>> train, List<Sample<T>> test, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k must be bigger than zero");
        }

        for (int s = 0; s < test.size(); s++) {
            // IndexDistanceRecord<T>[] records = new IndexDistanceRecord<T>[train.size()];
            List<IndexDistanceRecord<T>> records = new ArrayList<>();

            for (int i = 0; i < train.size(); i++) {
                double distance = CalculateUtil.getDistance(test.get(s).getFeatures(), train.get(i).getFeatures());
                records.add(new IndexDistanceRecord<>(train.get(i).getCategory(), distance));
                // records[i] = new IndexDistanceRecord<>(train.get(i).getCategory(), distance);
            }

            Collections.sort(records, (IndexDistanceRecord o1, IndexDistanceRecord o2) -> {
                    if (o1.distance > o2.distance) {
                        return 1;
                    } else if (o1.distance == o2.distance) {
                        return 0;
                    }
                    return -1;
                }
            );

            // 统计类别出现的次数
            Map<T, Integer> map = new HashMap<>();
            for (int i = 0; i < k; i++) {
                IndexDistanceRecord<T> record = records.get(i);
                if (!map.containsKey(record.category)){
                    map.put(record.category, 0);
                } else {
                    Integer cnt = map.get(record.category);
                    cnt++;
                    map.put(record.category, cnt);
                }
            }
            int cnt = 0;
            T category = null;
            for (Map.Entry<T, Integer> entry : map.entrySet()) {
                if (entry.getValue() > cnt) {
                    category = entry.getKey();
                }
            }
            test.get(s).setCategory(category);
        }
    }

}
