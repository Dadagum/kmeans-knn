package test;

import common.bean.Sample;
import common.utils.DataSetUtil;
import knn.Knn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KnnTest {

    public static void main(String[] args) throws IOException {
        String fileName = "/home/hongda/Documents/lib/datamining/knn_kmeans/iris.data";
        List<Sample> samples = DataSetUtil.loadSamples(fileName);
        List<Sample> train = getTrainSet(samples);
        List<Sample> backup = new ArrayList<>(train); // 拷贝一份，以后拿来评估
        for (int i = 0; i < samples.size(); i++) {
            samples.get(i).setCategory(null); // 清除标记
        }
        Knn.run(train, samples, 20);
//        for (int i = 0, cnt = 0; i < samples.size(); i++, cnt = (cnt+1)%10) {
//            System.out.print(samples.get(i).getCategory() + " ");
//            if (cnt == 9) {
//                System.out.println();
//            }
//        }
        judge(backup, samples);
    }

    /**
     * 划分训练集，没有划分的后面将会置null(装作不知道)，每一个类随机抓n条
     * @param samples
     * @return
     */
    private static List<Sample> getTrainSet(List<Sample> samples) {
        List<Sample> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < samples.size(); i++) {
            if (random.nextInt(7) >= 3) {
                result.add(samples.get(i));
                samples.remove(samples.get(i));
            }
        }

        return result;
    }

    /**
     * 简单的对结果做个展示
     */
    private static void judge(List<Sample> backup, List<Sample> test) {
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
