import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class MainFrame {

	private static JFrame frmVisualizationOfInstances;
	private JTextField txtName;
	private JTextField txtNodes;
	private JTextField txtBudget;
	private JTextField txtAverage;
	private JTextField txtAvgCost;
	private JTextField txtAvlb;
	private JTextField txtAvgType;
	private JTextField txtAvgVisit;
	private JTextField txtAvgSat;
	private List<Double>[] Matrix;
	private int nrOfRowsBeforePOIs;
	private int nrOfDays;
	private double avgCosts;
	private double avgRatings;
	private double avgDuration;
	private double avgAvailability;
	private double typeAvg;
	private JTextField txtDays;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();

					window.frmVisualizationOfInstances.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */

	public MainFrame() throws IOException {

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */

	public void initialize() throws IOException {

		frmVisualizationOfInstances = new JFrame();
		frmVisualizationOfInstances.getContentPane().setEnabled(false);
		frmVisualizationOfInstances.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frmVisualizationOfInstances.setFont(new Font("Dialog", Font.PLAIN, 12));
		frmVisualizationOfInstances.setTitle("Visualization of instances for MCTOPP");
		frmVisualizationOfInstances.setBounds(50, 50, 1480, 750);
		frmVisualizationOfInstances.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVisualizationOfInstances.setResizable(false);
		frmVisualizationOfInstances.setUndecorated(false);
		frmVisualizationOfInstances.setVisible(true);
		ImageIcon icon = new ImageIcon("img/datavisualization.png");
		frmVisualizationOfInstances.setIconImage(icon.getImage());

		txtName = new JTextField();
		txtName.setBounds(97, 30, 343, 20);
		txtName.setColumns(10);

		JLabel lblNewLabel = new JLabel("Name/Path:");
		lblNewLabel.setBounds(30, 33, 67, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		frmVisualizationOfInstances.getContentPane().setLayout(null);
		frmVisualizationOfInstances.getContentPane().add(lblNewLabel);
		frmVisualizationOfInstances.getContentPane().add(txtName);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Summary",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 10, 445, 660);
		panel_1.setBackground(new Color(220, 217, 252));
		panel_1.setForeground(Color.BLACK);
		panel_1.setFocusable(false);
		panel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		frmVisualizationOfInstances.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Number of nodes:");
		lblNewLabel_2.setBounds(20, 215, 166, 14);
		panel_1.add(lblNewLabel_2);

		txtNodes = new JTextField();
		txtNodes.setBounds(20, 230, 160, 20);
		panel_1.add(txtNodes);
		txtNodes.setEditable(false);
		txtNodes.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Costumers budget:");
		lblNewLabel_3.setBounds(20, 270, 177, 14);
		panel_1.add(lblNewLabel_3);

		txtBudget = new JTextField();
		txtBudget.setBounds(20, 285, 160, 20);
		panel_1.add(txtBudget);
		txtBudget.setEditable(false);
		txtBudget.setColumns(10);

		JLabel lbl1 = new JLabel("Number of days:");
		lbl1.setBounds(20, 160, 136, 14);
		panel_1.add(lbl1);

		JLabel lblNewLabel_5 = new JLabel("Average cost:");
		lblNewLabel_5.setBounds(20, 380, 177, 14);
		panel_1.add(lblNewLabel_5);

		txtAvgCost = new JTextField();
		txtAvgCost.setBounds(20, 395, 160, 20);
		panel_1.add(txtAvgCost);
		txtAvgCost.setEditable(false);
		txtAvgCost.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Availability of POIs:");
		lblNewLabel_1.setBounds(20, 435, 166, 14);
		panel_1.add(lblNewLabel_1);

		txtAvlb = new JTextField();
		txtAvlb.setBounds(20, 450, 160, 20);
		panel_1.add(txtAvlb);
		txtAvlb.setEditable(false);
		txtAvlb.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Average of visit duration:");
		lblNewLabel_8.setBounds(20, 545, 177, 14);
		panel_1.add(lblNewLabel_8);

		txtAvgVisit = new JTextField();
		txtAvgVisit.setBounds(20, 560, 160, 20);
		panel_1.add(txtAvgVisit);
		txtAvgVisit.setEditable(false);
		txtAvgVisit.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Average of cotumers's satisfaction:");
		lblNewLabel_9.setBounds(20, 600, 208, 14);
		panel_1.add(lblNewLabel_9);

		txtAvgSat = new JTextField();
		txtAvgSat.setBounds(20, 615, 160, 20);
		panel_1.add(txtAvgSat);
		txtAvgSat.setEditable(false);
		txtAvgSat.setColumns(10);

		txtDays = new JTextField();
		txtDays.setEditable(false);
		txtDays.setBounds(20, 175, 160, 20);
		panel_1.add(txtDays);
		txtDays.setColumns(10);

		txtAverage = new JTextField();
		txtAverage.setBounds(20, 340, 160, 20);
		panel_1.add(txtAverage);
		txtAverage.setEditable(false);
		txtAverage.setColumns(10);

		txtAvgType = new JTextField();
		txtAvgType.setBounds(20, 505, 160, 20);
		panel_1.add(txtAvgType);
		txtAvgType.setEditable(false);
		txtAvgType.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Pattern average:");
		lblNewLabel_4.setBounds(20, 325, 145, 14);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_7 = new JLabel("Average number of types for POIs:");
		lblNewLabel_7.setBounds(20, 490, 208, 14);
		panel_1.add(lblNewLabel_7);

		JLabel lblNewLabel_6 = new JLabel("After you have chosen the instance, click the \"Show info\" button:");
		lblNewLabel_6.setBounds(24, 99, 408, 14);
		panel_1.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnShowInfo = new JButton("Show info");
		btnShowInfo.setBounds(20, 128, 109, 23);
		panel_1.add(btnShowInfo);
		btnShowInfo.setBackground(new Color(177, 172, 252));
		btnShowInfo.setForeground(Color.BLACK);
		btnShowInfo.setFocusPainted(false);
		btnShowInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		JButton btnChooseInstance = new JButton("Choose Instance");
		btnChooseInstance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChooseInstance.setBounds(20, 52, 136, 23);
		panel_1.add(btnChooseInstance);
		btnChooseInstance.setBackground(new Color(177, 172, 252));
		btnChooseInstance.setForeground(Color.BLACK);
		btnChooseInstance.setFocusPainted(false);
		btnChooseInstance.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnChooseInstance.addMouseListener(new MouseAdapter() {

			public String fileName;

			@Override
			// open file
			public void mouseClicked(MouseEvent e) {

				final JFileChooser fc = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter("text files", "txt");
				fc.setFileFilter(filter);
				Component parent = null;
				int returnVal = fc.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					txtName.setText(fc.getSelectedFile().toString());
					fileName = fc.getSelectedFile().toString();

				}
			}
		});
		btnShowInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String textFieldValue = txtName.getText();
				readFiles(textFieldValue);

			}

			public String[] readFiles(String file) {
				// validation for chosen files
				if (file.equalsIgnoreCase("")) {

					JOptionPane.showMessageDialog(null,
							"Please select a text file before you click the 'Show Info' button");
					return null;

				} else if (!file.endsWith("txt")) {

					JOptionPane.showMessageDialog(null, "Please select a text file");
					return null;
				}

				int counter = 0;
				try {
					BufferedReader bR = new BufferedReader(new FileReader(file));
					while (bR.readLine() != null) {
						counter = counter + 1;
					}

					String[] numbers = new String[counter];

					Matrix = new List[counter];

					BufferedReader bR1 = new BufferedReader(new FileReader(file));
					for (int i = 0; i < counter; i = i + 1) {
						numbers[i] = bR1.readLine();
						Matrix[i] = convertStringListToDoubleList(Arrays.asList(numbers[i].split(" ")),
								Double::parseDouble);

					}

					// Number of days, nodes and budget
					nrOfDays = (int) Math.round(Matrix[0].get(0));
					double nodes = Matrix[0].get(1);
					double budget = Matrix[0].get(2);

					nrOfRowsBeforePOIs = (int) nrOfDays + 4;
					int nrOfRowsOfMatrix = (counter) - nrOfRowsBeforePOIs;

					// Average number of patterns per day
					double sumOfPatterns = 0;
					for (int i = 0; i < Matrix[2].size(); i++) {
						sumOfPatterns += Matrix[2].get(i);
					}

					double avgPatterns = sumOfPatterns / nrOfDays;

					// Cost average of POIs
					double sumOfCosts = 0;
					for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
						sumOfCosts += Matrix[i].get(7);
					}

					avgCosts = sumOfCosts / nrOfRowsOfMatrix;

					// Availability average of POIs
					double sumAvgAvailability = 0;
					for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
						sumAvgAvailability += (Matrix[i].get(6) - Matrix[i].get(5));
					}

					avgAvailability = sumAvgAvailability / nrOfRowsOfMatrix;

					// Type number average per POI
					int typeCounter = 0;
					for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {

						for (int j = 8; j < Matrix[i].size(); j++) {
							if (Matrix[i].get(j) == 1) {
								typeCounter++;
							}
						}

					}
					typeAvg = (double) typeCounter / nrOfRowsOfMatrix;

					// Visit duration average per POI
					double sumOfDurations = 0;
					for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
						sumOfDurations += Matrix[i].get(3);
					}
					avgDuration = sumOfDurations / nrOfRowsOfMatrix;

					// Rating average per POI
					double sumOfRatings = 0;
					for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
						sumOfRatings += Matrix[i].get(4);
					}

					avgRatings = sumOfRatings / nrOfRowsOfMatrix;

					DecimalFormat df = new DecimalFormat("0.###");

					txtDays.setText(df.format(nrOfDays));
					txtNodes.setText(df.format(nodes));
					txtBudget.setText(df.format(budget));
					txtAverage.setText(df.format(avgPatterns));
					txtAvgCost.setText(df.format(avgCosts));
					txtAvlb.setText(df.format(avgAvailability));
					txtAvgType.setText(df.format(typeAvg));
					txtAvgVisit.setText(df.format(avgDuration));
					txtAvgSat.setText(df.format(avgRatings));

				} catch (FileNotFoundException e) {

				} catch (IOException e) {

					e.printStackTrace();
				}
				return null;

			}

			private void showMessageDialog(Object object, String string) {
				// TODO Auto-generated method stub

			}
		});

		JPanel panel_2 = new JPanel();

		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Charts",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(461, 10, 995, 660);
		panel_2.setBackground(new Color(220, 217, 252));
		panel_2.setForeground(Color.BLACK);
		panel_2.setFocusable(false);
		panel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		frmVisualizationOfInstances.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JPanel panelChart = new JPanel();
		panelChart.setBounds(43, 94, 906, 519);
		panel_2.add(panelChart);
		panelChart.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JComboBox<String> listOfCharts = new JComboBox<String>();
		listOfCharts.setBounds(616, 47, 333, 22);
		panel_2.add(listOfCharts);
		listOfCharts.setToolTipText("");
		listOfCharts.setBackground(new Color(220, 217, 252));
		listOfCharts.setForeground(Color.BLACK);
		listOfCharts.setFocusable(false);
		listOfCharts.setFont(new Font("Tahoma", Font.BOLD, 12));

		listOfCharts.addItem("Choose chart");
		listOfCharts.addItem("Number of types per instance");
		listOfCharts.addItem("Number of types in the pattern");
		listOfCharts.addItem("Number of types per POI");
		listOfCharts.addItem("Availability of POIs");
		listOfCharts.addItem("Visit duration per POI");
		listOfCharts.addItem("Costumers's evaluation per POI");
		listOfCharts.addItem("Cost per POI");

		listOfCharts.addActionListener(new ActionListener() {
			public Map<Integer, Integer> countFrequencies(ArrayList<Integer> list) {

				// bar chart 2hashmap to store the frequency of element

				Map<Integer, Integer> hm = new HashMap<Integer, Integer>();

				for (Integer i : list) {

					Integer j = hm.get(i);
					hm.put(i, (j == null) ? 1 : j + 1);
				}

				return hm;

			}

			public void actionPerformed(ActionEvent e) {
				// Execute when a selection has been made
				if (Matrix == null) {
					JOptionPane.showMessageDialog(null, "Choose an instance first");

				} else {

					Object selected = listOfCharts.getSelectedItem();
					if (selected == "Number of types per instance") {

						// BAR CHART 1, Number of types per instance

						DefaultCategoryDataset dataset = new DefaultCategoryDataset();

						int[] numberOfOnesPerType = new int[10];

						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {

							for (int j = 8; j < Matrix[i].size(); j++) {
								if (Matrix[i].get(j) == 1) {
									numberOfOnesPerType[j - 8]++;
								}
							}
						}

						for (int i = 0; i < numberOfOnesPerType.length; i++) {
							dataset.setValue(numberOfOnesPerType[i], "Number of POIs with this type", "" + (i + 1));

						}
						JFreeChart chart = ChartFactory.createBarChart3D("Number of types per instance", "Type",
								"Occurence of each type", dataset, PlotOrientation.VERTICAL, false, true, false);
						chart.setBackgroundPaint(new Color(177, 172, 252));

						ChartPanel chartPanel = new ChartPanel(chart);
						panelChart.removeAll();
						panelChart.add(chartPanel, BorderLayout.CENTER);
						panelChart.validate();
						chartPanel.setBounds(10, 10, 887, 500);

					} else if (selected == "Number of types in the pattern") {

						// BAR CHART 2, number of types in pattern

						ArrayList<Integer> AllNumbers = new ArrayList<Integer>();
						for (int i = 3; i < nrOfDays + 3; i++) {
							for (int j = 0; j < Matrix[i].size(); j++) {
								Integer numri = (int) Math.round(Matrix[i].get(j));
								AllNumbers.add(numri);
							}
						}

						Map<Integer, Integer> nrOfOccurrencesPerNumber = countFrequencies(AllNumbers);

						DefaultCategoryDataset dataset = new DefaultCategoryDataset();

						for (Map.Entry<Integer, Integer> entry : nrOfOccurrencesPerNumber.entrySet())
							dataset.setValue(entry.getValue(), "Number of POIs with this type", "" + entry.getKey());

						JFreeChart chart = ChartFactory.createBarChart3D("Number of POIs with this type", "Type",
								"Occurrence of types in pattern", dataset, PlotOrientation.VERTICAL, false, true,
								false);
						chart.setBackgroundPaint(new Color(177, 172, 252));
						CategoryPlot plot = chart.getCategoryPlot();
						ValueAxis yAxis = plot.getRangeAxis();
						yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
						plot.setRangeGridlinePaint(Color.black);

						ChartPanel chartPanel = new ChartPanel(chart);
						panelChart.removeAll();
						panelChart.add(chartPanel, BorderLayout.CENTER);
						panelChart.validate();
						chartPanel.setBounds(10, 10, 887, 500);

					} else if (selected == "Number of types per POI") {

						// line chart number of types per POI

						DefaultCategoryDataset dataset = new DefaultCategoryDataset();
						String average = "Average number of types per POI";

						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							double pikaD = Matrix[i].get(0);
							int pika = (int) pikaD;
							dataset.setValue(typeAvg, average, "P" + pika);
						}
						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							int typeCounter = 0;
							for (int j = 8; j < Matrix[i].size(); j++) {
								if (Matrix[i].get(j) == 1) {
									typeCounter++;
								}
								double pikaD = Matrix[i].get(0);
								int pika = (int) pikaD;
								dataset.setValue(typeCounter, "Number of types per POI", "P" + pika);
							}
						}
						JFreeChart line = ChartFactory.createLineChart("Number of types per POI", "POIs",
								"Number of types for each POI", dataset, PlotOrientation.VERTICAL, true, true, true);
						line.setBackgroundPaint(new Color(177, 172, 252));
						CategoryPlot plot = line.getCategoryPlot();
						ValueAxis yAxis = plot.getRangeAxis();
						yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
						LineAndShapeRenderer renderer = new LineAndShapeRenderer();
						renderer.setSeriesStroke(0, new BasicStroke(1.0f));
						plot.setRenderer(renderer);

						ChartPanel chartPanel = new ChartPanel(line);
						panelChart.removeAll();
						panelChart.add(chartPanel, BorderLayout.CENTER);
						panelChart.validate();
						chartPanel.setBounds(10, 10, 887, 500);

					} else if (selected == "Availability of POIs") {

						// line chart POI availability

						DefaultCategoryDataset dataset = new DefaultCategoryDataset();
						String average = "Average availability of POIs";

						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							double pikaD = Matrix[i].get(0);
							int pika = (int) pikaD;
							dataset.setValue(avgAvailability, average, "P" + pika);
						}
						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							double pikaD = Matrix[i].get(0);
							int pika = (int) pikaD;
							dataset.setValue((Matrix[i].get(6) - Matrix[i].get(5)), "Availability per each POI",
									"P" + pika);
						}

						JFreeChart line = ChartFactory.createLineChart("Availability of POIs", "POIs",
								"Availability per each POI", dataset, PlotOrientation.VERTICAL, true, true, true);
						line.setBackgroundPaint(new Color(177, 172, 252));
						CategoryPlot plot = line.getCategoryPlot();
						LineAndShapeRenderer renderer = new LineAndShapeRenderer();
						renderer.setSeriesStroke(0, new BasicStroke(1.0f));
						plot.setRenderer(renderer);

						ChartPanel chartPanel = new ChartPanel(line);
						panelChart.removeAll();
						panelChart.add(chartPanel, BorderLayout.CENTER);
						panelChart.validate();
						chartPanel.setBounds(10, 10, 887, 500);

					} else if (selected == "Visit duration per POI") {

						// Line chart visit duration

						DefaultCategoryDataset dataset = new DefaultCategoryDataset();
						String average = "Average visit duration of POIs";

						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							double pikaD = Matrix[i].get(0);
							int pika = (int) pikaD;
							dataset.setValue(avgDuration, average, "P" + pika);
						}
						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							double pikaD = Matrix[i].get(0);
							int pika = (int) pikaD;
							dataset.setValue(Matrix[i].get(3), "Visit duration per POI", "P" + pika);
						}
						JFreeChart line = ChartFactory.createLineChart("Visit duration of POIs", "POIs",
								"Visit duration per each POI", dataset, PlotOrientation.VERTICAL, true, true, true);
						line.setBackgroundPaint(new Color(177, 172, 252));

						CategoryPlot plot = line.getCategoryPlot();
						LineAndShapeRenderer renderer = new LineAndShapeRenderer();
						renderer.setSeriesStroke(0, new BasicStroke(1.0f));
						plot.setRenderer(renderer);

						ChartPanel chartPanel = new ChartPanel(line);
						panelChart.removeAll();
						panelChart.add(chartPanel, BorderLayout.CENTER);
						panelChart.validate();
						chartPanel.setBounds(10, 10, 887, 500);

					} else if (selected == "Costumers's evaluation per POI") {

						// Line chart costumer's satisfaction

						DefaultCategoryDataset dataset = new DefaultCategoryDataset();
						String average = "Average Costumers's satisfaction";

						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							double pikaD = Matrix[i].get(0);
							int pika = (int) pikaD;
							dataset.setValue(avgRatings, average, "P" + pika);
						}
						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							double pikaD = Matrix[i].get(0);
							int pika = (int) pikaD;
							dataset.setValue(Matrix[i].get(4), "Costumer's satisfaction per POI", "P" + pika);
						}
						JFreeChart line = ChartFactory.createLineChart("Costumers's evaluation per POI", "POIs",
								"Costumers's evaluation per each POI", dataset, PlotOrientation.VERTICAL, true, true,
								true);
						line.setBackgroundPaint(new Color(177, 172, 252));

						CategoryPlot plot = line.getCategoryPlot();
						LineAndShapeRenderer renderer = new LineAndShapeRenderer();
						renderer.setSeriesStroke(0, new BasicStroke(1.0f));
						plot.setRenderer(renderer);

						ChartPanel chartPanel = new ChartPanel(line);
						panelChart.removeAll();
						panelChart.add(chartPanel, BorderLayout.CENTER);
						panelChart.validate();
						chartPanel.setBounds(10, 10, 887, 500);

					} else if (selected == "Cost per POI") {

						// Line chart per cost

						DefaultCategoryDataset dataset = new DefaultCategoryDataset();

						String average = "Average Cost";

						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							double pikaD = Matrix[i].get(0);
							int pika = (int) pikaD;
							dataset.setValue(avgCosts, average, "P" + pika);

						}

						for (int i = nrOfRowsBeforePOIs; i < Matrix.length; i++) {
							double pikaD = Matrix[i].get(0);
							int pika = (int) pikaD;
							dataset.setValue(Matrix[i].get(7), "Cost per each POI", "P" + pika);

						}

						JFreeChart line = ChartFactory.createLineChart("Cost per POI", "POIs", "Cost per each POI",
								dataset, PlotOrientation.VERTICAL, true, true, true);
						line.setBackgroundPaint(new Color(177, 172, 252));
						CategoryPlot plot = line.getCategoryPlot();
						LineAndShapeRenderer renderer = new LineAndShapeRenderer();
						renderer.setSeriesStroke(0, new BasicStroke(1.0f));
						plot.setRenderer(renderer);

						ChartPanel chartPanel = new ChartPanel(line);
						panelChart.removeAll();
						panelChart.add(chartPanel, BorderLayout.CENTER);
						panelChart.validate();
						chartPanel.setBounds(10, 10, 887, 500);

					}
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(177, 172, 252));
		menuBar.setForeground(Color.WHITE);
		menuBar.setBorderPainted(false);

		frmVisualizationOfInstances.setJMenuBar(menuBar);

		JButton btnAbout = new JButton();
		btnAbout.setText("ABOUT");
		Image img = ImageIO.read(getClass().getResource("about1.jpg"));
		btnAbout.setIcon(new ImageIcon(img));
		btnAbout.setBackground(new Color(177, 172, 252));
		btnAbout.setForeground(Color.BLACK);
		btnAbout.setFocusPainted(false);
		btnAbout.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				About nw = new About();
				nw.NewScreen();
			}
		});
		menuBar.add(btnAbout);

		JButton btnRefresh = new JButton();
		btnRefresh.setText("REFRESH");
		Image img1 = ImageIO.read(getClass().getResource("refresh.jpg"));
		btnRefresh.setIcon(new ImageIcon(img1));
		btnRefresh.setBackground(new Color(177, 172, 252));
		btnRefresh.setForeground(Color.BLACK);
		btnRefresh.setFocusPainted(false);
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// refreeesh
				Matrix = null;
				txtDays.setText(null);
				txtName.setText(null);
				txtAvgSat.setText(null);
				txtAvgVisit.setText(null);
				txtAvgType.setText(null);
				txtAvlb.setText(null);
				txtAvgCost.setText(null);
				txtBudget.setText(null);
				txtDays.setText(null);
				txtNodes.setText(null);
				txtAverage.setText(null);
				panelChart.removeAll();
				panelChart.repaint();
				// listOfCharts.setSelectedItem("choose chart");
			}
		});
		menuBar.add(btnRefresh);

		JButton btnExit = new JButton("EXIT");
		Image img2 = ImageIO.read(getClass().getResource("exit.jpg"));
		btnExit.setIcon(new ImageIcon(img2));
		btnExit.setBackground(new Color(177, 172, 252));
		btnExit.setForeground(Color.BLACK);
		btnExit.setFocusPainted(false);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					System.exit(0);
				}

			}
		});
		menuBar.add(btnExit);

	}

	public static <T, U> List<U> convertStringListToDoubleList(List<T> listOfString, Function<T, U> function) {
		return listOfString.stream().map(function).collect(Collectors.toList());
	}
}
