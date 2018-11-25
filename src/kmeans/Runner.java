package kmeans;

import utils.DataSetUtil;

import java.io.IOException;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws IOException {
        String fileName = "/home/hongda/Documents/lib/datamining/knn_kmeans/iris.data";
        List<List<Double>> input = DataSetUtil.load(fileName);
        // List<List<Double>> subList = input.subList(49,52);
        // int[] result = randomMethod(input);
         int[] result = centerPointMethod(input);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }

    }

    private static int[] randomMethod(List<List<Double>> input) {
        // 直接取随机的效果十分不好,撒个12点
        int centers = 12;
        int lb = 0;
        int ub = 10;
        int dim = input.get(0).size();
        PointFactory pointFactory = new RandomPointFactory(ub, lb, dim, centers);
        return kMeans.run(input, centers, pointFactory);
    }

    private static int[] centerPointMethod(List<List<Double>> input) {
        int centers = 3;
        int dim = input.get(0).size();
        PointFactory pointFactory = new CenterPointFactory(input, dim);
        return kMeans.run(input, centers, pointFactory);
    }
}
