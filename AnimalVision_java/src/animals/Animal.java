package animals;

import colour.Colour;
import colour.Spectrum;

import static colour.Spectrum.*;
import static math.Math.*;
import static colour.Colour.wavelength2rgb;

public class Animal {

	int[] sensitivities;

	/**
	 * An Animal, characterized by its peak cone sensitivities
	 * 
	 * @param sensitivities
	 *            the wavelengths at which the animal has peak cone
	 *            sensitivities
	 */
	public Animal(int[] sensitivities) {
		this.sensitivities = sensitivities;
	}

	/**
	 * transforms an RGB input triplet to a simulated rgb triplet in the animal
	 * vision using the specified cone sensitivities
	 * 
	 * @param r
	 *            input amount of red
	 * @param g
	 *            input amount of green
	 * @param b
	 *            input amount of blue
	 * @param sensitivities
	 *            peak cone sensitivities of the animal
	 * @return the colour perceived by the animal
	 */
	public Colour rgb2Animal(double r, double g, double b) {
		//lets say we sampled the spectrum at wavelengths linspace(380, 686, 10) + 17,
		//which corresponds to the middle of each bin
		double[] samplePoints = new double[] {397, 431, 465, 499, 533, 567, 601, 635, 669, 703};
		Spectrum spectrum = rgb2spectrum(r,g,b); // spectrum of the specified colour
		
		//get values of the spectrum at the cone sensitivities
		double[] spectralValues = new double[sensitivities.length];
		for(int i = 0; i< sensitivities.length; i++) {
			spectralValues[i] = getInterpolated(samplePoints, spectrum, sensitivities[i]);
		}
		
		//need to get the "center of gravity", i.e combine wavelength and
		//spectral values so as to get a single wavelength
		spectralValues = divide(spectralValues, sum(spectralValues));
		int finalWavelength = (int) dot(spectralValues,  sensitivities);
		Colour c = wavelength2rgb(finalWavelength);
		
		return c;

	}

}
