package kmeans;

import utils.DataSetUtil;

import java.io.IOException;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws IOException {
        String fileName = "/home/hongda/Documents/lib/datamining/knn_kmeans/iris.data";
        List<List<Double>> input = DataSetUtil.load(fileName);
        List<List<Double>> subList = input.subList(49,52);
        int centers = 3;
        int lb = 0;
        int up = 10;
        int[] result = kMeans.run(input, 15, 10, 0);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }

    }
}
