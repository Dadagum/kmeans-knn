package kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 完成随机的生成点的策略
 */
public class RandomPointFactory implements PointFactory {

    private int ub;

    private int lb;

    private int dim;

    private int num;

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
        int size = ub - lb;
        for (int i = 0; i < num; i++) {
            List<Double> data = new ArrayList<>(dim);
            for (int j = 0; j < dim; j++) {
                data.add(random.nextDouble() + random.nextInt(size) + lb);
            }
            result.add(data);
        }
        return result;
    }
}
