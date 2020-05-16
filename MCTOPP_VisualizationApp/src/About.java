import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class About {

	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					About window = new About();
					window.frame.setVisible(true);
					frame.setTitle("About");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public About() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 200, 800, 600);
		frame.getContentPane().setLayout(null);
		ImageIcon icon = new ImageIcon("img/about1.jpg");

		frame.setIconImage(icon.getImage());

		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("uni.PNG")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));

		lblNewLabel.setBounds(286, 123, 225, 296);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"Visualization of instances for MCTOPP application was developed by Ermira Xhelili, for bachelor's diploma ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(124, 430, 580, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("thesis. This project was mentored by Prof. Dr. Asoc. Kadri Sylejmani.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(209, 455, 464, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Prishtina, Kosovo 2020");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(320, 503, 182, 25);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(
				"Development of a tool for visualizing instances for tourists's itinerary problem");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(108, 43, 636, 25);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("University of Prishtina \"Hasan Prishtina\"");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(261, 99, 288, 25);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(
				"Faculty of Electrical and Computer Engineering, programme: Computer Engineering");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(149, 123, 547, 25);
		frame.getContentPane().add(lblNewLabel_6);
	}
}
