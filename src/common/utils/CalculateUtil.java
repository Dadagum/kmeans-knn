package common.utils;

import java.util.List;

public class CalculateUtil {

    private CalculateUtil(){}

    /**
     * 计算两个点的欧氏距离
     */
    public static Double getDistance(List<Double> v1, List<Double> v2) {
        double distance = 0;
        for (int n = 0; n < v1.size(); n++) { // 计算一个样本和一个中心点的距离
            distance += Math.pow(v1.get(n) - v2.get(n), 2);
        }
        return distance = Math.pow(distance, 0.5);
    }
}
