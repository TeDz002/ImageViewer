package application;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class ImageComponent extends JComponent {
	
	private BufferedImage image;
	private String image_name;
	private String image_path;
	private FileTime image_date;
	private Dimension image_dimensions;
	private BasicFileAttributes metadata;

	
	public void updateImage(File img) {
		try {
            this.image = ImageIO.read(img);
            this.image_dimensions = new Dimension(this.image.getWidth(), this.image.getHeight());
            JFrame masterWindow = (JFrame) JFrame.getFrames()[0];
            setMinimumSize(this.image_dimensions);
            setPreferredSize(this.image_dimensions);
            setMaximumSize(this.image_dimensions);
            masterWindow.setResizable(true);
            masterWindow.setSize(this.image_dimensions.width, this.image_dimensions.height + 110);
            masterWindow.setResizable(false);
            repaint();
        } catch (IOException x) {
            x.printStackTrace();
        }
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g = g.create();
        g.drawImage(this.image, 0, 0, this);
    }

	public void setImageName(String name) { this.image_name = name; }
	
	public void setImagePath(String path) { this.image_path = path; }
	
	public void setImageDate() throws IOException { 
		Path file = Paths.get(this.getImagePath());
        this.metadata = Files.readAttributes(file, BasicFileAttributes.class);
        this.image_date = this.metadata.creationTime();
	}

	public BufferedImage getBufferedImage() { return this.image; }

    public String getImageName() { return this.image_name; }

    public String getImagePath() { return this.image_path; }
    
    public Dimension getImageDimensions() { return this.image_dimensions; }

    public long getImageSize() { 
    	Path file = Paths.get(this.getImagePath());
    	return file.toFile().length();
    }
    
    public FileTime getImageDate() throws IOException {
        return this.image_date;
    }
}
