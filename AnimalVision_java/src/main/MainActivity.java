/**
 * Author : Chiara Orvati
 */

package main;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import animals.Animal;
import colour.Colour;

public class MainActivity {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat image = Imgcodecs.imread(
				"/Users/chiara/Documents/EPFL/Master/Ma2/CompPhotography/project/javaImplementation/AnimalVision/src/Main/apples.jpg");

		// transform image to double
		// NOT SURE WHETHER THIS DOES WHAT WE WANT => WANT EQUIVALENT OF
		// im2double IN MATLAB
		Mat imDouble = null;
		// use convertTo(image, CvType.CV_64FC3) ?
		image.convertTo(image, CvType.CV_64FC3);
		Core.normalize(image, imDouble, 0.0, 1.0, Core.NORM_MINMAX);

		// define sensitivities of animal
		// BIRD: [380 445 508 565]
		// BUMBLEBEE : [380 424 539]
		// DEER : [455 537]
		// FANTASY: [550 700]
		int[] sensitivities = new int[] { 450, 550 };
		Animal someAnimal = new Animal(sensitivities);

		// TODO convert image to animal vision using rgb2Animal
		// loop through all pixels (or use GPU) to do this to all pixels:
		// Colour c = someAnimal.rgb2Animal(r, g, b);
		//

		// TODO transform original image and simulated image to CIELAB space
		// (probably a function in opencv that does this)

		// TODO create final image by taking L- component of original image and
		// a,b-components of simulated image and transform the assembled image
		// back to RGB

		// TODO displayImage(Mat2BufferedImage(image));

	}

	public static BufferedImage Mat2BufferedImage(Mat m) {
		// source:
		// http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
		// Fastest code
		// The output can be assigned either to a BufferedImage or to an Image

		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels() * m.cols() * m.rows();
		byte[] b = new byte[bufferSize];
		m.get(0, 0, b); // get all the pixels
		BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);
		return image;

	}

	public static void displayImage(Image img2) {
		ImageIcon icon = new ImageIcon(img2);
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(img2.getWidth(null) + 50, img2.getHeight(null) + 50);
		JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
