package test;

import common.bean.Sample;
import common.utils.DataSetUtil;
import knn.Knn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class KnnTest {

    public static void main(String[] args) throws IOException {
        String fileName = "/home/hongda/Documents/lib/datamining/knn_kmeans/iris.data";
        List<Sample<String>> train = DataSetUtil.loadSamples(fileName);
        List<Sample<String>> test = getTestSet(train);
        List<Sample<String>> backup = new ArrayList<>(test); // 拷贝一份，以后拿来评估
        for (int i = 0; i < test.size(); i++) {
            test.get(i).setCategory(null); // 清除类别信息
        }
        Knn.run(train, test, 5);
        for (int i = 0, cnt = 0; i < test.size(); i++, cnt = (cnt+1)%10) {
            System.out.print(test.get(i).getCategory() + " ");
            if (cnt == 9) {
                System.out.println();
            }
        }
        System.out.println();
        judge(backup, test);
    }

    /**
     * 划分得到测试集
     * @param samples 数据集
     * @return
     */
    private static List<Sample<String>> getTestSet(List<Sample<String>> samples) {
        List<Sample<String>> result = new ArrayList<>();
        Random random = new Random();
        int testSize = 15;
        List<List<Integer>> trainIndex = new ArrayList<>();
        for (int i = 150; i > 0; i -= 50) {
            trainIndex.add(random.ints(testSize, i-50, i).boxed().sorted((a, b) -> -Integer.compare(a, b)).collect(Collectors.toList()));
        }
        System.out.println(trainIndex);
        for (var list : trainIndex) {
            for (var index : list) {
                result.add(samples.get(index));
                samples.remove(samples.get(index));
            }
        }
        return result;
    }

    /**
     * 简单的对结果做个展示
     */
    private static void judge(List<Sample<String>> backup, List<Sample<String>> test) {
        int cnt = 0;
        int[] sign = new int[backup.size()];
        for (int i = 0, j = 0; i < backup.size(); i++, j = (j+1) % 10) {
            if (backup.get(i).getCategory().equals(test.get(i).getCategory())) {
                cnt++;
                sign[i] = 1;
            }
            System.out.print(sign[i] + " ");
            if (j == 9) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Accuracy : " + (double)cnt / backup.size());
    }
}
