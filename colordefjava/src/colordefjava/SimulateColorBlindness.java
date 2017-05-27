package colordefjava;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import Jama.*;

public class SimulateColorBlindness {
	
	private static Matrix RGBtoLMS = new Matrix(new double[][] { 
		{17.884, 43.5161, 4.1193},
		{3.4557, 27.1554, 3.8671},
		{0.02996, 0.18431, 1.4670} }
	);
	
	private static Matrix LMStoRGB = RGBtoLMS.inverse();
		
	private static Matrix PROT = new Matrix(new double[][] {
		{0, 2.02344, -2.5281},
		{0,1,0},
		{0,0,1} }
	);
	
	private static Matrix DEUT = new Matrix(new double[][] {
		{1,0,0},
		{0.494207, 0, 1.24827},
		{0,0,1} }
	);
		
	private static Matrix TRIT = new Matrix(new double[][] {
		{1,0,0},
		{0,1,0},
		{-0.012245, 0.072035, 0} }
	);
	
	public void readIMG(BufferedImage image, int typeOfDeficiency) throws IOException{
		int w = image.getWidth();
		int h = image.getHeight();
		//create new image of same height and width. populate it with the transformed pixels
		BufferedImage modifiedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);

		//in the for loop, retrieve the projections of pixels onto a colorblind plane
		for(int row = 0; row < h; row++){
			for(int col = 0; col < w; col++) {
				
				int init_rgb = image.getRGB(col, row);
				
				//get original RGB values
                int R = init_rgb >> 16 & 0xff;
                int G = init_rgb >> 8 & 0xff;
                int B = init_rgb & 0xff;
				
				//get modified rgb values
				Matrix RGBPixel = new Matrix(new double[][] {{R},{G},{B}});
				Matrix swag = null;
				switch(typeOfDeficiency) {
					case 0:
						swag = protanope(RGBPixel);
						break;
					case 1:
						swag = deuteranope(RGBPixel);
						break;
					case 2:
						swag = tritanope(RGBPixel);
						break;
					default: 
						swag = protanope(RGBPixel);
						break;
				}
				
				double[][] newRGB = swag.getArray();
				
				int red = (newRGB[0][0] > 255) ? 255 : (Math.abs((int)newRGB[0][0]) & 0xff);
				int green = (newRGB[1][0] > 255) ? 255 : (Math.abs((int)newRGB[1][0]) & 0xff);
				int blue = (newRGB[2][0] > 255) ? 255 : (Math.abs((int)newRGB[2][0]) & 0xff);
				
				int rgb = 255;
				rgb = (rgb << 8) + red;
				rgb = (rgb << 8) + green;
				rgb = (rgb << 8) + blue;
								
				//insert in new image
				modifiedImage.setRGB(col, row, rgb);
			}
		}
		
		try {
			File example = new File("newt.png");
			ImageIO.write(modifiedImage, "png", example);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	public Matrix protanope(Matrix RGBPixel){
		Matrix inLMS = RGBtoLMS.times(RGBPixel);
		Matrix newPixel = PROT.times(inLMS);
		Matrix inRGB = LMStoRGB.times(newPixel);
		return inRGB;
		
	}
	public Matrix deuteranope(Matrix RGBPixel){
		
		Matrix inLMS = RGBtoLMS.times(RGBPixel);
		Matrix newPixel = DEUT.times(inLMS);
		Matrix inRGB = LMStoRGB.times(newPixel);
		return inRGB;
		
	}
	public Matrix tritanope(Matrix RGBPixel){
		
		Matrix inLMS = RGBtoLMS.times(RGBPixel);
		Matrix newPixel = TRIT.times(inLMS);
		Matrix inRGB = LMStoRGB.times(newPixel);
		return inRGB;
		
	}
	
	/*input: 0 for PROT
	1 for DEUT
	2 for TRIT
	
	output: a modified coefficient matrix
	*/
	public Matrix changeCoefficients(int typeOfDeficiency) {
		double delta = -0.1;
		Matrix temp = null;
		
		switch(typeOfDeficiency) {
			case 0:
				temp = PROT.copy();
				
				//less M wavelength
				temp.set(0, 1, PROT.get(0, 1)-delta);
				//more S wavelength
				temp.set(0, 2, PROT.get(0, 2)+delta);
				break;
			case 1:
				temp = DEUT.copy();
				
				//less L wavelength
				temp.set(1, 0, DEUT.get(1, 0)-delta);
				//more S wavelength
				temp.set(1, 2, DEUT.get(1, 2)+delta);
				break;
			case 2:
				temp = TRIT.copy();
				
				//less L wavelength
				temp.set(2, 0, TRIT.get(2, 0)-delta);
				//more M wavelength
				temp.set(2, 1, TRIT.get(2, 1)+delta);
				break;
			default:
				return PROT;
				
		}
				
		
		return temp;
	}
	
	public Matrix projectionSlider(Matrix RGBPixel, Matrix Coefficients) {
		Matrix inLMS = RGBtoLMS.times(RGBPixel);
		Matrix newPixel = Coefficients.times(inLMS);
		Matrix inRGB = LMStoRGB.times(newPixel);
		return inRGB;		
	}
	public void modifyIMG(BufferedImage image, int typeOfDeficiency) throws IOException{
		int w = image.getWidth();
		int h = image.getHeight();
		//create new image of same height and width. populate it with the transformed pixels
		BufferedImage modifiedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);

		//in the for loop, retrieve the projections of pixels onto a colorblind plane
		for(int row = 0; row < h; row++){
			for(int col = 0; col < w; col++) {
				
				int init_rgb = image.getRGB(col, row);
				
				//get original RGB values
                int R = init_rgb >> 16 & 0xff;
                int G = init_rgb >> 8 & 0xff;
                int B = init_rgb & 0xff;
				
				//get modified rgb values
				Matrix RGBPixel = new Matrix(new double[][] {{R},{G},{B}});
				Matrix swag = null;
				
				Matrix coefficients = changeCoefficients(typeOfDeficiency);
				swag = projectionSlider(RGBPixel, coefficients);
				
				double[][] newRGB = swag.getArray();
				
				int red = (newRGB[0][0] > 255) ? 255 : (Math.abs((int)newRGB[0][0]) & 0xff);
				int green = (newRGB[1][0] > 255) ? 255 : (Math.abs((int)newRGB[1][0]) & 0xff);
				int blue = (newRGB[2][0] > 255) ? 255 : (Math.abs((int)newRGB[2][0]) & 0xff);
				
				int rgb = 255;
				rgb = (rgb << 8) + red;
				rgb = (rgb << 8) + green;
				rgb = (rgb << 8) + blue;
								
				//insert in new image
				modifiedImage.setRGB(col, row, rgb);
			}
		}
		
		try {
			File example = new File("projtest.png");
			ImageIO.write(modifiedImage, "png", example);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
}
