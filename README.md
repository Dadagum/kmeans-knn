# java实现K-means算法和Knn算法

## 数据集

1. 本次实验使用的数据集为uci iris。

## K-means

1. 静态调用`int[] run(List<List<Double>> input, int centers, PointFactory pointFactory)`
   - input：数据集的特征集合，例如[[1.1, 3], [2, 5.6]]。
   - centers：撒放中心点的个数。
   - PointFactory：生成中心点的工厂，RandomPointFactory是随机生成中心点的工厂，而CenterPointFactory为本次数据集中聚类效果不错的中心点。可以通过实现PointFactory工厂提供更多的生成中心点的策略。

## Knn

1. 静态调用`void run(List<Sample<T>> train, List<Sample<T>> test, int k)`
   - train：训练集。
   - test：测试集。
   - k：knn算法的k参数。
   - 函数没有返回值，分类的结果记录在test列表的Sample元素的category属性中。Sample类有泛型参数T，这是考虑到实际的类别可以是String(如a,b,c)，Integer(如1,2,3)或者其他自己定义类(由于run方法中使用到了HashMap，category属性为key值，因此自己定义的类最好重写hashCode和equal方法)。
