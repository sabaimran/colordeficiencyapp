/**
 * Author : Chiara Orvati
 */

package math;

public class Math {

	/**sums the elements of a vector
	 * 
	 * @param vec
	 * @return
	 */
	public static double sum(double[] vec) {
		double sum = 0;

		for (int i = 0; i < vec.length; i++) {
			sum += vec[i];
		}
		return sum;
	}
	
	
	/**divides a vector by a scalar elementwise
	 * 
	 * @param vec 
	 * @param a
	 * @return
	 */
	public static double[] divide(double[] vec, double a) {

		double[] out = new double[vec.length];
		for (int i = 0; i < vec.length; i++) {
			out[i] = vec[i] / a;
		}

		return out;
	}

	/**computes the dot product between two double vectors
	 * 
	 * @param vec1
	 * @param vec2
	 * @return
	 */
	public static double dot(double[] vec1, double[] vec2) {
		if (vec1.length != vec2.length) {
			throw new IllegalArgumentException("vectors must have same size");
		}

		double[] v = new double[vec1.length];
		for (int i = 0; i < vec1.length; i++) {
			v[i] = vec1[i] * vec2[i];
		}

		return sum(v);
	}
	
	/**computes the dot product bewteen a double and an int vector
	 * 
	 * @param vec1
	 * @param vec2
	 * @return
	 */
	public static double dot(double[] vec1, int[] vec2) {
		if (vec1.length != vec2.length) {
			throw new IllegalArgumentException("vectors must have same size");
		}

		double[] v = new double[vec1.length];
		for (int i = 0; i < vec1.length; i++) {
			v[i] = vec1[i] * vec2[i];
		}

		return sum(v);
	}

}
