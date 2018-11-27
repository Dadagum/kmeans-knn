package kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 随机的生成点的策略
 */
public class RandomPointFactory implements PointFactory {

    private int ub; // 特征量上界

    private int lb; // 特征量下界

    private int dim; // 样本维度即样本特征量的数量

    private int num; // 需要生成的中心点个数

    private Random random = new Random();

    public RandomPointFactory(int ub, int lb, int dim, int num) {
        this.ub = ub;
        this.lb = lb;
        this.dim = dim;
        this.num = num;
    }

    @Override
    public List<List<Double>> generateCenters() {
        // 检查入参
        if (ub < lb) {
            throw new IllegalArgumentException("upper bounce should be bigger than lower bounce");
        }
        List<List<Double>> result = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            result.add(random
                    .doubles(dim, lb, ub)
                    .boxed()
                    .collect(Collectors.toList()));
        }
        return result;
    }
}
