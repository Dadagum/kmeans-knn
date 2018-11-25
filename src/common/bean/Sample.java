package common.bean;

import java.util.List;

public class Sample<T> {

    private List<Double> features;

    private T category; // 如果不是String和Integer类型需要最好实现自己的hashCode和equal方法

    public List<Double> getFeatures() {
        return features;
    }

    public void setFeatures(List<Double> features) {
        this.features = features;
    }

    public T getCategory() {
        return category;
    }

    public void setCategory(T category) {
        this.category = category;
    }
}
