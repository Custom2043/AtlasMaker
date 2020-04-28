package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		File dir = new File(args[0]);
		int width = Integer.valueOf(args[1]);
		int dim = Integer.valueOf(args[2]);
		
		File[] fileList = dir.listFiles();
		int height = (fileList.length - 1) / width + 1;
		BufferedImage finalImage = new BufferedImage(width * dim, height * dim, BufferedImage.TYPE_INT_ARGB);
		int index = 0;
		for (File f : dir.listFiles())
		{
			BufferedImage img = ImageIO.read(f);
			int[] pixels = new int[dim * dim];
			img.getRGB(0, 0, dim, dim, pixels, 0, dim);
			finalImage.setRGB((index % width) * dim, (index / width) * dim, dim, dim, pixels, 0, dim);
			index ++;
		}
		ImageIO.write(finalImage, "png", new FileOutputStream(new File("Test.png")));
	}
}
