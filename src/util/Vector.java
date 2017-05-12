package util;

public class Vector {

    /**
     * Normalise un vecteur 2D de float
     * 
     * @param src
     *            Le vecteur 2D source
     * @return Le vecteur 2D normalise
     */
    public static float[] normalize2f(float[] src) {
	float[] res = new float[2];
	float length = Vector.length2f(src);
	res[0] = src[0] / length;
	res[1] = src[1] / length;
	return res;
    }

    /**
     * Retourne la norme d'un vecteur 2D de float
     * 
     * @param src
     *            Le vetceur 2D soucre
     * @return La norme du vecteur source
     */
    public static float length2f(float[] src) {
	return (float) Math.sqrt(Math.pow(src[0], 2d) + Math.pow(src[1], 2d));
    }
}
