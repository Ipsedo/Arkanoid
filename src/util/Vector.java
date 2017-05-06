package util;

public class Vector {

    public static float[] normalize2f(float[] src) {
	float[] res = new float[2];
	float length = Vector.length2f(src);
	res[0] = src[0] / length;
	res[1] = src[1] / length;
	return res;
    }

    public static float length2f(float[] src) {
	return (float) Math.sqrt(Math.pow(src[0], 2d) + Math.pow(src[1], 2d));
    }
}
