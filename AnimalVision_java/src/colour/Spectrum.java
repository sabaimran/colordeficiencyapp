/**
 * Author : Chiara Orvati
 */

package colour;

//using version 3.6.1 of Apache Commons Math
import org.apache.commons.math3.analysis.interpolation.*;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class Spectrum {

	private static final Spectrum WHITESPECTRUM = new Spectrum(
			new double[] { 1.0000, 1.0000, 0.9999, 0.9993, 0.9992, 0.9998, 1.0000, 1.0000, 1.0000, 1.0000 });
	private static final Spectrum CYANSPECTRUM = new Spectrum(
			new double[] { 0.9710, 0.9426, 1.0007, 1.0007, 1.0007, 1.0007, 0.1564, 0.0000, 0.0000, 0.0000 });
	private static final Spectrum MAGENTASPECTRUM = new Spectrum(
			new double[] { 1.0000, 1.0000, 0.9685, 0.2229, 0.0000, 0.0458, 0.8369, 1.0000, 1.0000, 0.9959 });
	private static final Spectrum YELLOWSPECTRUM = new Spectrum(
			new double[] { 0.0001, 0.0000, 0.1088, 0.6651, 1.0000, 1.0000, 0.9996, 0.9586, 0.9685, 0.9840 });
	private static final Spectrum REDSPECTRUM = new Spectrum(
			new double[] { 0.1012, 0.0515, 0.0000, 0.0000, 0.0000, 0.0000, 0.8325, 1.0149, 1.0149, 1.0149 });
	private static final Spectrum GREENSPECTRUM = new Spectrum(
			new double[] { 0.0000, 0.0000, 0.0273, 0.7937, 1.0000, 0.9418, 0.1719, 0.0000, 0.0000, 0.0025 });
	private static final Spectrum BLUESPECTRUM = new Spectrum(
			new double[] { 1.0000, 1.0000, 0.8916, 0.3323, 0.0000, 0.0000, 0.0003, 0.0369, 0.0483, 0.0496 });

	double[] spectrum;

	public Spectrum(double[] spectrum) {
		if (spectrum.length != 10) {
			throw new IllegalArgumentException("a spectrum must have 10 bins");
		}
		this.spectrum = spectrum;
	}

	/**
	 * converts an rgb triplet to a spectrum with 10 bins, ranging from 380nm to
	 * 720 nm (380 414 448 482 516 550 584 618 652 686 720) every bin has 34
	 * wavelengths in it ressource: "RGB to spectrum for reflectances" by Brian
	 * Smits
	 * 
	 * @param red
	 *            R value of RGB triplet in double
	 * @param green
	 *            G value of RGB triplet in double
	 * @param blue
	 *            B value of RGB triplet in double
	 * @return the spectrum representation of the RGB triplet
	 */
	public static Spectrum rgb2spectrum(double red, double green, double blue) {

		if (!(0 <= red && red <= 1 && 0 <= green && green <= 1 && 0 <= blue && blue <= 1)) {
			throw new IllegalArgumentException("values of red, green blue must be between 0 and 1");
		}
		Spectrum spectrum = new Spectrum(new double[10]);

		if (red <= green && red <= blue) {
			spectrum = add(spectrum, multiply(red, WHITESPECTRUM));
			if (green <= blue) {
				spectrum = add(spectrum, multiply((green - red), CYANSPECTRUM));
				spectrum = add(spectrum, multiply((blue - green), BLUESPECTRUM));
			} else {
				spectrum = add(spectrum, multiply((blue - red), CYANSPECTRUM));
				spectrum = add(spectrum, multiply((green - blue), GREENSPECTRUM));
			}

		} else if (green <= red && green <= blue) {
			spectrum = add(spectrum, multiply(green, WHITESPECTRUM));
			if (red <= blue) {
				spectrum = add(spectrum, multiply((red - green), MAGENTASPECTRUM));
				spectrum = add(spectrum, multiply((blue - red), BLUESPECTRUM));
			} else {
				spectrum = add(spectrum, multiply((blue - green), MAGENTASPECTRUM));
				spectrum = add(spectrum, multiply((red - blue), REDSPECTRUM));
			}

		} else {// blue <= red && blue <= green
			spectrum = add(spectrum, multiply(blue, WHITESPECTRUM));
			if (red <= green) {
				spectrum = add(spectrum, multiply((red - blue), YELLOWSPECTRUM));
				spectrum = add(spectrum, multiply((green - red), GREENSPECTRUM));
			} else {
				spectrum = add(spectrum, multiply((green - blue), YELLOWSPECTRUM));
				spectrum = add(spectrum, multiply((red - green), REDSPECTRUM));
			}
		}

		return spectrum;
	}

	/**
	 * returns the the value of the spectrum at wavelength lambda using
	 * interpolation
	 * 
	 * @param samplePoints
	 *            positions where the spectrum is known
	 * @param s
	 *            the known (discrete) values of the spectrum
	 * @param lambda
	 *            the wavelength at which we want to tkno the value of the
	 *            spectrum
	 * @return the value of the spectrum at wavelength lambda
	 */
	public static double getInterpolated(double[] samplePoints, Spectrum s, int lambda) {
		if (!(320 <= lambda && lambda <= 720)) {
			throw new IllegalArgumentException("x must be between 320 and 720 nm");
		}
		if (!(samplePoints.length == s.spectrum.length)) {
			throw new IllegalArgumentException("samplePoints and spectrum must have the same length");
		}

		// as the sample points are {397, 431, 465, 499, 533, 567, 601, 635,
		// 669, 703} it is possible to have lambda outside this range. In that case interpolate wouldn't work
		double l;
		double maxV = samplePoints[samplePoints.length - 1];
		double minV = samplePoints[0];
		if (lambda < minV) {
			l = minV;
		} else if (lambda > maxV) {
			l = maxV;
		} else {
			l = lambda;
		}

		LinearInterpolator li = new LinearInterpolator();
		PolynomialSplineFunction psf = li.interpolate(samplePoints, s.spectrum);
		double interpolV = psf.value(l);

		return interpolV;
	}

	/**
	 * Adds two spectra element-wise
	 * 
	 * @param s1
	 *            first spectrum
	 * @param s2
	 *            second spectrum
	 * @return sum of s1 and s2
	 */
	private static Spectrum add(Spectrum s1, Spectrum s2) {
		if (s1.spectrum.length != s2.spectrum.length) {
			throw new IllegalArgumentException("the spectra must have the same size");
		}
		int N = s1.spectrum.length;

		Spectrum s = new Spectrum(new double[N]);
		for (int i = 0; i < N; i++) {
			s.spectrum[i] = s1.spectrum[i] + s2.spectrum[i];
		}

		return s;
	}

	/**
	 * Multiplies a spectrum by a scalar
	 * 
	 * @param a
	 *            scalar (scaling factor)
	 * @param s1
	 *            spectrum
	 * @return the spectrum scaled by a
	 */
	private static Spectrum multiply(double a, Spectrum s1) {

		int N = s1.spectrum.length;
		Spectrum s = new Spectrum(new double[N]);
		for (int i = 0; i < N; i++) {
			s.spectrum[i] = a * s1.spectrum[i];
		}

		return s;
	}
}
