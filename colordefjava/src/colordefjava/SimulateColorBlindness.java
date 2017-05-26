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
	
	public void readIMG(BufferedImage image) throws IOException{
		int w = image.getWidth();
		int h = image.getHeight();
		//create new image of same height and width. populate it with the transformed pixels
		BufferedImage modifiedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);

		//in the for loop, retrieve the projections of pixels onto a colorblind plane
		for(int row = 0; row < h; row++){
			for(int col = 0; col < w; col++) {
				
				Color c = new Color(image.getRGB(col, row));
				
				//get original RGB values
				int R = c.getRed();
				int G = c.getGreen();
				int B = c.getBlue();
				
				//System.out.println("original RGB: "+R+" "+G+" "+B);
				
				//get modified rgb values
				Matrix RGBPixel = new Matrix(new double[][] {{R},{G},{B}});
				
				Matrix swag = deuteranope(RGBPixel);
				
				double[][] newRGB = swag.getArray();
				
				int red = Math.abs((int)newRGB[0][0]);
				int green = Math.abs((int)(newRGB[1][0]));
				int blue = Math.abs((int)(newRGB[2][0]));
							
				int rgb = new Color(red, green, blue).getRGB();
								
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
}
