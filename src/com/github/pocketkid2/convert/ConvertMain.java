package com.github.pocketkid2.convert;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class ConvertMain {
	
	private JFrame frmConverter;
	private JTextField hexField;
	private JTextField binField;
	private JTextField octField;
	private JTextField decField;
	private int mode;
	private Long value;
	private int base;
	private JTextField baseField;
	private JLabel errorMessage;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ConvertMain window = new ConvertMain();
				window.frmConverter.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * Create the application.
	 *
	 * @wbp.parser.entryPoint
	 */
	public ConvertMain() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConverter = new JFrame();
		frmConverter.setTitle("Converter");
		frmConverter.setBounds(100, 100, 741, 295);
		frmConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConverter.getContentPane().setLayout(null);
		
		hexField = new JTextField();
		hexField.setFont(new Font("Consolas", Font.PLAIN, 14));
		hexField.setBounds(107, 11, 497, 20);
		frmConverter.getContentPane().add(hexField);
		hexField.setColumns(10);
		
		binField = new JTextField();
		binField.setFont(new Font("Consolas", Font.PLAIN, 14));
		binField.setBounds(107, 42, 497, 20);
		frmConverter.getContentPane().add(binField);
		binField.setColumns(10);
		
		octField = new JTextField();
		octField.setFont(new Font("Consolas", Font.PLAIN, 14));
		octField.setBounds(107, 73, 497, 20);
		frmConverter.getContentPane().add(octField);
		octField.setColumns(10);
		
		decField = new JTextField();
		decField.setFont(new Font("Consolas", Font.PLAIN, 14));
		decField.setBounds(107, 104, 497, 20);
		frmConverter.getContentPane().add(decField);
		decField.setColumns(10);
		
		JLabel lblHexadecimal = new JLabel("Hexadecimal");
		lblHexadecimal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHexadecimal.setBounds(10, 14, 87, 14);
		frmConverter.getContentPane().add(lblHexadecimal);
		
		JLabel lblBinary = new JLabel("Binary");
		lblBinary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBinary.setBounds(10, 45, 87, 14);
		frmConverter.getContentPane().add(lblBinary);
		
		JLabel lblOctal = new JLabel("Octal");
		lblOctal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOctal.setBounds(10, 76, 87, 14);
		frmConverter.getContentPane().add(lblOctal);
		
		JLabel lblDecimal = new JLabel("Decimal");
		lblDecimal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDecimal.setBounds(10, 107, 87, 14);
		frmConverter.getContentPane().add(lblDecimal);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(e -> convert());
		btnConvert.setBounds(309, 196, 89, 23);
		frmConverter.getContentPane().add(btnConvert);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(e -> clear());
		btnClear.setBounds(408, 196, 89, 23);
		frmConverter.getContentPane().add(btnClear);

		JLabel lblBaseN = new JLabel("Base N");
		lblBaseN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBaseN.setBounds(10, 138, 87, 14);
		frmConverter.getContentPane().add(lblBaseN);

		baseField = new JTextField();
		baseField.setFont(new Font("Consolas", Font.PLAIN, 14));
		baseField.setBounds(107, 135, 497, 20);
		frmConverter.getContentPane().add(baseField);
		baseField.setColumns(10);

		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(e -> base = (int) spinner.getValue());
		spinner.setModel(new SpinnerNumberModel(10, Character.MIN_RADIX, Character.MAX_RADIX, 1));
		spinner.setBounds(212, 196, 87, 23);
		frmConverter.getContentPane().add(spinner);

		JLabel lblN = new JLabel("N");
		lblN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblN.setBounds(156, 200, 46, 14);
		frmConverter.getContentPane().add(lblN);

		JRadioButton hexButton = new JRadioButton("");
		hexButton.addActionListener(e -> mode = 1);
		hexButton.setBounds(610, 11, 109, 23);
		frmConverter.getContentPane().add(hexButton);

		JRadioButton binButton = new JRadioButton("");
		binButton.addActionListener(e -> mode = 2);
		binButton.setBounds(610, 42, 109, 23);
		frmConverter.getContentPane().add(binButton);

		JRadioButton octButton = new JRadioButton("");
		octButton.addActionListener(e -> mode = 3);
		octButton.setBounds(610, 73, 109, 23);
		frmConverter.getContentPane().add(octButton);

		JRadioButton decButton = new JRadioButton("");
		decButton.addActionListener(e -> mode = 4);
		decButton.setBounds(610, 104, 109, 23);
		frmConverter.getContentPane().add(decButton);

		JRadioButton baseButton = new JRadioButton("");
		baseButton.addActionListener(e -> mode = 5);
		baseButton.setBounds(610, 135, 109, 23);
		frmConverter.getContentPane().add(baseButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(hexButton);
		group.add(binButton);
		group.add(octButton);
		group.add(decButton);
		group.add(baseButton);
		
		JLabel lblDisclaimerTopThree = new JLabel(
				"Disclaimer: Top three bases only support unsigned values of up to 63 binary bits");
		lblDisclaimerTopThree.setBounds(117, 166, 487, 14);
		frmConverter.getContentPane().add(lblDisclaimerTopThree);

		errorMessage = new JLabel("");
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage.setBounds(10, 231, 705, 14);
		frmConverter.getContentPane().add(errorMessage);
	}
	
	private void clear() {
		hexField.setText(null);
		binField.setText(null);
		octField.setText(null);
		decField.setText(null);
		baseField.setText(null);
		errorMessage.setText(null);
	}

	private void update() {
		hexField.setText(Long.toHexString(value));
		binField.setText(Long.toBinaryString(value));
		octField.setText(Long.toOctalString(value));
		decField.setText(Long.toString(value));
		baseField.setText(Long.toString(value, base));
		errorMessage.setText(null);
	}

	private void convert() {
		try {
			switch (mode) {
				case 1:
					value = Long.valueOf(hexField.getText().replaceAll("x", "0"), 16);
					break;
				case 2:
					value = Long.valueOf(binField.getText().replaceAll("b", "0"), 2);
					break;
				case 3:
					value = Long.valueOf(octField.getText(), 8);
					break;
				case 4:
					value = Long.valueOf(decField.getText());
					break;
				case 5:
					value = Long.valueOf(baseField.getText(), base);
					break;
				default:
					errorMessage.setText("Error: No field selected");
					return;
			}
			update();
		} catch (NumberFormatException e) {
			errorMessage.setText("Error: Selected field text is invalid or outside bounds (see disclaimer)");
			// System.out.println("Attempted to parse an invalid string: " + e.toString());
			// e.printStackTrace();
			return;
		}
	}
}
