package application;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class CheckBoxPanel extends JPanel {

	private JCheckBox chBox1 = new JCheckBox("Name");
	private JCheckBox chBox2 = new JCheckBox("Path");
	private JCheckBox chBox3 = new JCheckBox("Size");
	private JCheckBox chBox4 = new JCheckBox("Date");
	private JCheckBox chBox5 = new JCheckBox("Dimensions");
	private ActionHandler actionListener;

	public CheckBoxPanel(ImageComponent imgComp) {

		this.actionListener = new ActionHandler(imgComp);

		chBox1.addActionListener(actionListener);
		chBox2.addActionListener(actionListener);
		chBox3.addActionListener(actionListener);
		chBox4.addActionListener(actionListener);
		chBox5.addActionListener(actionListener);

		add(chBox1);
		add(chBox2);
		add(chBox3);
		add(chBox4);
		add(chBox5);
	}

	public ActionHandler getActionHandler() {
		return this.actionListener;
	}

	class ActionHandler implements ActionListener {

		private ImageComponent imgComp;
		private Graphics g;
		private boolean chBox1Flag;
		private boolean chBox2Flag;
		private boolean chBox3Flag;
		private boolean chBox4Flag;
		private boolean chBox5Flag;
		private int counter;

		public ActionHandler(ImageComponent imgComp) {
			this.imgComp = imgComp;
			this.counter = 20;
		}

		public void setGraphics() {
			this.g = this.imgComp.getBufferedImage().getGraphics();
			this.g.setFont(g.getFont().deriveFont(16f));
			this.g.setColor(java.awt.Color.red);
		}

		public void flagCheck() {

			this.counter = 20;

			if (this.chBox1Flag) {
				String imageName = this.imgComp.getImageName();
				setGraphics();
				this.g.drawString("Name: " + imageName, 10, this.counter);
				this.counter += 20;
				this.imgComp.repaint();
			}

			if (this.chBox2Flag) {
				String imagePath = this.imgComp.getImagePath();
				setGraphics();
				this.g.drawString("Path: " + imagePath, 10, this.counter);
				this.counter += 20;
				this.imgComp.repaint();
			}

			if (this.chBox3Flag) {
				long imageSize = this.imgComp.getImageSize();
				setGraphics();
				this.g.drawString("Size: " + imageSize + " Bytes", 10, this.counter);
				this.counter += 20;
				this.imgComp.repaint();
			}

			if (this.chBox4Flag) {
				try {
					String imageDate = this.imgComp.getImageDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
					setGraphics();
					this.g.drawString("Date: " + imageDate, 10, this.counter);
					this.counter += 20;
					this.imgComp.repaint();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}

			if (this.chBox5Flag) {
				Dimension imageDimensions = this.imgComp.getImageDimensions();
				setGraphics();
				this.g.drawString("Dimensions: " + imageDimensions.width + " x " + imageDimensions.height, 10,
						this.counter);
				this.counter += 20;
				this.imgComp.repaint();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JCheckBox checkbox = (JCheckBox) e.getSource();

			if (this.imgComp.getBufferedImage() != null) {

				if (checkbox == chBox1) {

					if (checkbox.isSelected()) {
						String imageName = this.imgComp.getImageName();
						setGraphics();
						this.g.drawString("Name: " + imageName, 10, this.counter);
						this.chBox1Flag = true;
						this.counter += 20;
					}

					else {
						this.chBox1Flag = false;
						this.imgComp.updateImage(new File(this.imgComp.getImagePath()));
						flagCheck();
					}

					this.imgComp.repaint();

				} else if (checkbox == chBox2) {

					if (checkbox.isSelected()) {
						String imagePath = this.imgComp.getImagePath();
						setGraphics();
						this.g.drawString("Path: " + imagePath, 10, this.counter);
						this.chBox2Flag = true;
						this.counter += 20;
					}

					else {
						this.chBox2Flag = false;
						this.imgComp.updateImage(new File(this.imgComp.getImagePath()));
						flagCheck();
					}

					this.imgComp.repaint();

				} else if (checkbox == chBox3) {

					if (checkbox.isSelected()) {
						long imageSize = this.imgComp.getImageSize();
						setGraphics();
						this.g.drawString("Size: " + imageSize + " Bytes", 10, this.counter);
						this.chBox3Flag = true;
						this.counter += 20;
					}

					else {
						this.chBox3Flag = false;
						this.imgComp.updateImage(new File(this.imgComp.getImagePath()));
						flagCheck();
					}

					this.imgComp.repaint();

				} else if (checkbox == chBox4) {

					if (checkbox.isSelected()) {

						try {
							String imageDate = this.imgComp.getImageDate().toInstant().atZone(ZoneId.systemDefault())
									.toLocalDateTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
							setGraphics();
							this.g.drawString("Date: " + imageDate, 10, this.counter);
							this.chBox4Flag = true;
							this.counter += 20;

						} catch (IOException ioException) {
							ioException.printStackTrace();
						}
					}

					else {
						this.chBox4Flag = false;
						this.imgComp.updateImage(new File(this.imgComp.getImagePath()));
						flagCheck();
					}
					this.imgComp.repaint();
				}

				if (checkbox == chBox5) {

					if (checkbox.isSelected()) {
						Dimension imageDimensions = this.imgComp.getImageDimensions();
						setGraphics();
						this.g.drawString("Dimensions: " + imageDimensions.width + " x " + imageDimensions.height, 10,
								this.counter);
						this.chBox5Flag = true;
						this.counter += 20;
					}

					else {
						this.chBox5Flag = false;
						this.imgComp.updateImage(new File(this.imgComp.getImagePath()));
						flagCheck();
					}

					this.imgComp.repaint();
				}

			} else {
				JFrame masterWindow = (JFrame) JFrame.getFrames()[0];
				JOptionPane.showMessageDialog(masterWindow, "Select a file before using checkboxes!");
			}

		}

	}

}
