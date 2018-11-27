package kmeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 针对这个数据集的生成中心点策略
 */
public class CenterPointFactory implements PointFactory{


    private List<List<Double>> input;

    private int dim;

    public CenterPointFactory(List<List<Double>> input, int dim) {
        this.input = input;
        this.dim = dim;
    }

    /**
     * 就特定生成三个点
     */
    @Override
    public List<List<Double>> generateCenters() {
        List<List<Double>> result = new ArrayList<>();
        Double[] p1 = new Double[dim];
        Double[] p2 = new Double[dim];
        Double[] p3 = new Double[dim];

        for (int i = 0; i < dim; i++) {

            p3[i] = 0d; // 初始化
            for (List<Double> sample : input) {
                p3[i] += sample.get(i);
            }
            p3[i] /= input.size();
            p1[i] = p3[i] - 2;
            p2[i] = p3[i] + 2;
        }
        result.add(Arrays.asList(p1));
        result.add(Arrays.asList(p2));
        result.add(Arrays.asList(p3));

        return result;
    }
}
