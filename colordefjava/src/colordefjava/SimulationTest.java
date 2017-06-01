package colordefjava;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SimulationTest {

	public static void main(String[] args) throws IOException {
		SimulateColorBlindness simulation = new SimulateColorBlindness();
		
		try {
			File tree = new File("apples.jpg");
			BufferedImage image = ImageIO.read(tree);
			//simulation.readIMG(image,1);
			simulation.modifyIMG(image, 1, 0.22);
			System.out.println("success");
			;
		} catch (IOException e) {
			System.out.println(System.getProperty("user.dir"));
			System.err.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

}
