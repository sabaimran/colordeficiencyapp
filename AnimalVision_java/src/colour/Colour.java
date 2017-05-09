/**
 * 
 */
package colour;

/**
 * @author chiara
 *
 */
public class Colour {

	double r, g, b;

	/**A colour represented by its RGB values lying in the range [0,1]
	 * 
	 * @param r amount of red
	 * @param g amount of green
	 * @param b amount of blue
	 */
	public Colour(double r, double g, double b) {
		if (!(0.0 <= r && r <= 1.0 && 0.0 <= g && g <= 1.0 && 0.0 <= b && b <= 1.0)) {
			throw new IllegalArgumentException("rgb values must lie between 0 and 1!");
		}

		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * 
	 * maps a wavelength in the range [380, 720] nm to the correpsonding RGB triplet
	 * using the values provided by
	 * "https://academo.org/demos/wavelength-to-colour-relationship/"
	 * Discretization: to every 10 nm one rgb value is associated.
	 * 
	 * @param lambda the wavelength in nm and ranges from 380 to 720 nm
	 * @return the RGB color corresponding to lambda
	 */
	public static Colour wavelength2rgb(int lambda) {
		if (!(380 <= lambda && lambda <= 720)) {
			throw new IllegalArgumentException("lambda must lie between 380 and 720 nm");
		}

		double r, g, b = 0;
		int l = (int) Math.floor(lambda / 10.);
		// for every bin of 10 values the value at wavelength xx5 nm has been
		// taken
		// ex: for the bin 380-389nm, the value at 385 nm was taken
		switch (l) {
		// wavelength from 380-389 nm
		case 38:
			r = 111;
			g = 0;
			b = 119;
			break;
		case 39:
			r = 128;
			g = 0;
			b = 161;
			break;
		case 40:
			r = 130;
			g = 0;
			b = 200;
			break;
		case 41:
			r = 118;
			g = 0;
			b = 237;
			break;
		case 42:
			r = 84;
			g = 0;
			b = 255;
			break;
		case 43:
			r = 35;
			g = 0;
			b = 255;
			break;
		case 44:
			r = 0;
			g = 40;
			b = 255;
			break;
		case 45:
			r = 0;
			g = 97;
			b = 255;
			break;
		case 46:
			r = 0;
			g = 146;
			b = 255;
			break;
		case 47:
			r = 0;
			g = 192;
			b = 255;
			break;
		case 48:
			r = 0;
			g = 234;
			b = 255;
			break;
		case 49:
			r = 0;
			g = 255;
			b = 203;
			break;
		case 50:
			r = 0;
			g = 255;
			b = 84;
			break;
		case 51:
			r = 31;
			g = 255;
			b = 0;
			break;
		case 52:
			r = 74;
			g = 255;
			b = 0;
			break;
		case 53:
			r = 112;
			g = 255;
			b = 0;
			break;
		case 54:
			r = 146;
			g = 255;
			b = 0;
			break;
		case 55:
			r = 179;
			g = 255;
			b = 0;
			break;
		case 56:
			r = 210;
			g = 255;
			b = 0;
			break;
		case 57:
			r = 240;
			g = 255;
			b = 0;
			break;
		case 58:
			r = 255;
			g = 239;
			b = 0;
			break;
		case 59:
			r = 255;
			g = 207;
			b = 0;
			break;
		case 60:
			r = 255;
			g = 173;
			b = 0;
			break;
		case 61:
			r = 255;
			g = 137;
			b = 0;
			break;
		case 62:
			r = 255;
			g = 99;
			b = 0;
			break;
		case 63:
			r = 255;
			g = 57;
			b = 0;
			break;
		case 64:
			r = 255;
			g = 0;
			b = 0;
			break;
		case 65:
			r = 255;
			g = 0;
			b = 0;
			break;
		case 66:
			r = 255;
			g = 0;
			b = 0;
			break;
		case 67:
			r = 255;
			g = 0;
			b = 0;
			break;
		case 68:
			r = 255;
			g = 0;
			b = 0;
			break;
		case 69:
			r = 255;
			g = 0;
			b = 0;
			break;
		case 70:
			r = 246;
			g = 0;
			b = 0;
			break;
		case 71:
			r = 228;
			g = 0;
			b = 0;
			break;
		case 72:
			// this represents only the value 720 nm (otherwise it would be left
			// out, and we'd need to stop at 719nnm)
			r = 219;
			g = 0;
			b = 0;
			break;
		default:
			throw new IllegalArgumentException("no bin could be assigned");
		}

		return new Colour(r / 255., g / 255., b / 255.);
	}

}
