package knn;

import common.bean.Sample;
import common.utils.CalculateUtil;

import java.util.*;


class IndexDistanceRecord{

    public String category;

    public double distance;

    public IndexDistanceRecord(String category, double distance) {
        this.category = category;
        this.distance = distance;
    }
}

public class Knn {


    public static void run(List<Sample> train, List<Sample> test, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k must be bigger than zero");
        }

        for (int s = 0; s < test.size(); s++) {
            IndexDistanceRecord[] records = new IndexDistanceRecord[train.size()];
            for (int i = 0; i < train.size(); i++) {
                double distance = CalculateUtil.getDistance(test.get(s).getFeatures(), train.get(i).getFeatures());
                records[i] = new IndexDistanceRecord(train.get(i).getCategory(), distance);
            }

            Arrays.sort(records, (IndexDistanceRecord o1, IndexDistanceRecord o2) -> {
                    if (o1.distance > o2.distance) {
                        return 1;
                    } else if (o1.distance == o2.distance) {
                        return 0;
                    }
                    return -1;
                }
            );

            // 统计类别出现的次数
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < k; i++) {
                IndexDistanceRecord record = records[i];
                if (!map.containsKey(record.category)){
                    map.put(record.category, 0);
                } else {
                    Integer cnt = map.get(record.category);
                    cnt++;
                    map.put(record.category, cnt);
                }
            }
            int cnt = 0;
            String category = null;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() > cnt) {
                    category = entry.getKey();
                }
            }
            test.get(s).setCategory(category);
        }
    }

}
