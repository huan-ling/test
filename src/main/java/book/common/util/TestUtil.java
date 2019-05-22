package book.common.util;

import java.util.function.Function;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-21 12:38
 */
public class TestUtil {

    public static void main(String[] args) {

        // ��ʾ����������String�����������Integer
        // apply---��ʾ����������ĳ������ת��Ϊ�������
        // Function<String, Integer> f = s -> Integer.parseInt(s);
        // System.out.println(f.apply("34").getClass());

        // ����һ���ַ�����������ַ���ת��Ϊ����֮����п���
        Function<String, Double> f = s -> Double.parseDouble(s);
        Function<String, Double> f2 = f.andThen(Math::sqrt).andThen(d -> d * 10);
        System.out.println(f2.apply("2.25"));

    }
}
