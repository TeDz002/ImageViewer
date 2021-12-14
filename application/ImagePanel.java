package application;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private ImageComponent imgComp;
	
	public ImagePanel() {
		this.imgComp = new ImageComponent();
		add(imgComp);
	}

	public ImageComponent getImageComponent() {
		return this.imgComp;
	}
}
