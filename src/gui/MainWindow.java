package gui;

import model.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainWindow implements ActionListener {

	public static final String CALCULAR = "CALCULAR";
	public static final String GENERAR = "GENERAR";

	private JFrame frame;
	private JTable table;
	private JRadioButton Moore;
	private JRadioButton Mealy;
	private JButton Ctable;
	private JTextArea R;// Alfabeto
	private JTextArea S;// Alfabeto de salida
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextArea States;
	private ButtonGroup grupo;
	private JButton CalcularB;
	private Automata logicAutomata;
	private JTable tablaResul;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		grupo = new ButtonGroup();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.setTitle("Automata Equivalente");
		frame.setResizable(false);
		frame.setSize((int) dim.getWidth() / 2, (int) dim.getHeight() / 2);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblNewLabel = new JLabel("Ingrese el alfabeto");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		Ctable = new JButton("Ingresar valores a la Tabla");
		Ctable.addActionListener(this);
		Ctable.setActionCommand(GENERAR);

		GridBagConstraints gbc_Ctable = new GridBagConstraints();
		gbc_Ctable.insets = new Insets(0, 0, 5, 5);
		gbc_Ctable.gridx = 0;
		gbc_Ctable.gridy = 2;
		panel.add(Ctable, gbc_Ctable);

		S = new JTextArea();
		GridBagConstraints gbc_S = new GridBagConstraints();
		gbc_S.insets = new Insets(0, 0, 5, 5);
		gbc_S.fill = GridBagConstraints.BOTH;
		gbc_S.gridx = 1;
		gbc_S.gridy = 2;
		panel.add(S, gbc_S);

		lblNewLabel_1 = new JLabel("Ingrese el Alfabeto de salida");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		CalcularB = new JButton("Calcular Minimo");
		CalcularB.addActionListener(this);
		CalcularB.setActionCommand(CALCULAR);

		GridBagConstraints gbc_CalcularB = new GridBagConstraints();
		gbc_CalcularB.insets = new Insets(0, 0, 5, 5);
		gbc_CalcularB.gridx = 0;
		gbc_CalcularB.gridy = 4;
		panel.add(CalcularB, gbc_CalcularB);

		R = new JTextArea();
		GridBagConstraints gbc_R = new GridBagConstraints();
		gbc_R.insets = new Insets(0, 0, 5, 5);
		gbc_R.fill = GridBagConstraints.BOTH;
		gbc_R.gridx = 1;
		gbc_R.gridy = 4;
		panel.add(R, gbc_R);

		lblNewLabel_2 = new JLabel("Ingrese la cantidad de estados(numero)");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 5;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		States = new JTextArea();
		GridBagConstraints gbc_States = new GridBagConstraints();
		gbc_States.insets = new Insets(0, 0, 5, 5);
		gbc_States.fill = GridBagConstraints.BOTH;
		gbc_States.gridx = 1;
		gbc_States.gridy = 6;
		panel.add(States, gbc_States);
		Mealy = new JRadioButton("Mealy");
		grupo.add(Mealy);

		GridBagConstraints gbc_Mealy = new GridBagConstraints();
		gbc_Mealy.insets = new Insets(0, 0, 5, 5);
		gbc_Mealy.gridx = 1;
		gbc_Mealy.gridy = 7;
		panel.add(Mealy, gbc_Mealy);

		Moore = new JRadioButton("Moore");
		grupo.add(Moore);

		Moore.setSelected(true);
		GridBagConstraints gbc_Moore = new GridBagConstraints();
		gbc_Moore.insets = new Insets(0, 0, 5, 5);
		gbc_Moore.gridx = 1;
		gbc_Moore.gridy = 8;
		panel.add(Moore, gbc_Moore);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (States.getText().isEmpty() || S.getText().isEmpty() || R.getText().isEmpty()) {
			// Mostrar mensaje emergente
			System.out.println("Incompleto");
		} else if (e.getActionCommand().equals(GENERAR)) {
			String delims = "\\W+"; // split any non word
			String[] tokensS = S.getText().split(delims);
			int states = 0;
			try {
				states = Integer.parseInt(States.getText());
			} catch (NumberFormatException e2) {
				states = -1;
				e2.printStackTrace();
			}

			if (table != null) {// mirar luego
			}
			if (validateButton() == 0) {
				String colum[] = new String[tokensS.length + 2];
				Arrays.fill(colum, "");
				for (int i = 1; i < colum.length - 1; i++) {
					colum[i] = tokensS[i - 1];
				}
				String data[][] = new String[states][tokensS.length + 2];

				for (int i = 0; i < data.length; i++) {
					Arrays.fill(data[i], "");
					data[i][0] = "Q" + (i);
				}

				table = new JTable(data, colum);
				frame.getContentPane().add(table, BorderLayout.CENTER);
				JScrollPane js = new JScrollPane(table);
				js.setVisible(true);
				frame.getContentPane().add(js);

			} else {
				String colum[] = new String[tokensS.length + 1];
				Arrays.fill(colum, "");
				for (int i = 1; i < colum.length; i++) {
					colum[i] = tokensS[i - 1];
				}
				String data[][] = new String[states][tokensS.length + 1];

				for (int i = 0; i < data.length; i++) {
					Arrays.fill(data[i], "");
					data[i][0] = "Q" + (i);
				}

				table = new JTable(data, colum);
				frame.getContentPane().add(table, BorderLayout.CENTER);
				JScrollPane js = new JScrollPane(table);
				js.setVisible(true);
				frame.getContentPane().add(js);
			}

		} else {
			String delims = "\\W+"; // split any non word
			String[] tokensS = S.getText().split(delims);
			String[] tokensR = R.getText().split(delims);
			int states = 0;
			try {
				states = Integer.parseInt(States.getText());
			} catch (NumberFormatException e2) {
				states = -1;
				e2.printStackTrace();
			}

			String[] sta = new String[states];

			if (validateButton() == 0) { // moore
				logicAutomata = new Moore(tokensS, tokensR, states, table.getModel().getValueAt(0, 0) + "");
				TableModel tMd = table.getModel();
				for (int i = 0; i < states; i++) {
					for (int j = 1; j < tokensS.length + 1; j++) {
						logicAutomata.add(tMd.getValueAt(i, 0) + "", tokensS[j - 1], tMd.getValueAt(i, j) + "",
								tMd.getValueAt(i, tokensS.length + 1) + "");
					}
					sta[i] = tMd.getValueAt(i, 0) + "";
				}

			} else { // Mealy
				logicAutomata = new Mealy(tokensS, tokensR, states, table.getModel().getValueAt(0, 0) + "");
				TableModel tMd = table.getModel();
				for (int i = 0; i < states; i++) {
					for (int j = 1; j < tokensS.length + 1; j++) {
						String arr[] = (tMd.getValueAt(i, j) + "").split(delims);
						logicAutomata.add(tMd.getValueAt(i, 0) + "", tokensS[j - 1], arr[0], arr[1]);
					}
					sta[i] = tMd.getValueAt(i, 0) + "";
				}
			}

			logicAutomata.setStates(sta);
			logicAutomata.generateEquivalentMinimum();

			tablaResul = new JTable(logicAutomata.data(), logicAutomata.column());
			frame.getContentPane().add(tablaResul, BorderLayout.SOUTH);
			JScrollPane js = new JScrollPane(tablaResul);
			js.setVisible(true);
			frame.getContentPane().add(js);
		}

		frame.pack();
	}

	public int validateButton() {
		if (Moore.isSelected()) {
			return 0; // moore
		} else {
			return 1;// mealy
		}

	}

}
