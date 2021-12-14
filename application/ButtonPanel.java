package application;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {

	private JButton button = new JButton("Open");
	private final JFileChooser fc = new JFileChooser();
	private ImageComponent imgComp;

	public ButtonPanel(ImageComponent imgComp, CheckBoxPanel cbPanel) {
		this.imgComp = imgComp;
		this.fc.setFileFilter(new FileFilter()
		{
			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith(".png") || f.getName().endsWith(".jpg") || f.getName().endsWith(".jpeg")) {
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "Image file filter (png, jpg, jpeg)";
			}
		});
		add(this.button);
		// Requires try-catch due to setImageDate()
		this.button.addActionListener((ev) -> {
			try {
				updateImageComponent(fc, cbPanel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void buttonPress(JFileChooser fc) {
		JFrame masterWindow = (JFrame) JFrame.getFrames()[0];
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String fullPath = this.fc.getSelectedFile().getAbsolutePath();
			if (fullPath.endsWith(".jpg") || fullPath.endsWith(".jpeg") || fullPath.endsWith(".png")) { ; }
			else {
				JOptionPane.showMessageDialog(masterWindow, "File must be an image!");
			}
		}
	}

	public void updateImageComponent(JFileChooser fc, CheckBoxPanel cbPanel) throws IOException {
		this.buttonPress(fc);
		File file = new File(fc.getSelectedFile().getAbsolutePath());
		this.imgComp.updateImage(file);
		this.imgComp.setImageName(fc.getSelectedFile().getName());
		this.imgComp.setImagePath(fc.getSelectedFile().getAbsolutePath());
		this.imgComp.setImageDate();
		cbPanel.getActionHandler().flagCheck();
	}
}
