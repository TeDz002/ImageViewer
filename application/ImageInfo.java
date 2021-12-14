package application;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class ImageInfo extends JFrame {
	
	private ImagePanel imgPanel = new ImagePanel();
	private ImageComponent imgComp = imgPanel.getImageComponent();
	private CheckBoxPanel cbPanel = new CheckBoxPanel(imgComp);
	private ButtonPanel bPanel = new ButtonPanel(imgComp, cbPanel);

	public ImageInfo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Image Information");

		setMinimumSize(new Dimension(400, 100));
		setLayout(new BorderLayout());
		setResizable(false);

		add(cbPanel, BorderLayout.NORTH);
		add(imgPanel, BorderLayout.CENTER);
		add(bPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	public static void launch() {
		new ImageInfo();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> launch());
	}

}
