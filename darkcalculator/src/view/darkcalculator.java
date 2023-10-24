package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class darkcalculator extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField textFieldDisplay;
	private JPanel contentPane;
	private Color buttonColor;
	private Color backgroundColor;
	private Color textColor;
	private Color lineColor;
	private int x;
	private int y;
	private boolean blackMode = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					darkcalculator frame = new darkcalculator();
					frame.setLocationRelativeTo(frame);
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setOpacity((float)0.99);
					frame.setTitle("Calculadora");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public darkcalculator() {
		
		buttonColor = new Color( 70, 71, 74);
		textColor = Color.white;
		backgroundColor = new Color( 53, 53, 53);
		lineColor = new Color( 53, 53, 53);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 490);
		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		moveScreen();
		
		JLabel btnMode = new JLabel();
		btnMode.setIcon(new ImageIcon(darkcalculator.class.getResource("/image/whiteMode.png")));
		btnMode.setBounds(0,0,40,30);
		contentPane.add(btnMode);
		
		JButton btnClose = createButton("x", 280, 0, 40, 30);
		btnClose.addActionListener(this);
		contentPane.add(btnClose);
		
		textFieldDisplay = new JTextField();
		textFieldDisplay.setBorder(null);
		textFieldDisplay.setEditable(false);
		textFieldDisplay.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textFieldDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldDisplay.setText("0");
		textFieldDisplay.setForeground(Color.white);
		textFieldDisplay.setBounds(0, 32, 320, 60);
		textFieldDisplay.setBackground(new Color(53, 53, 53));
		contentPane.add(textFieldDisplay);
		textFieldDisplay.setColumns(10);
		
		JButton btnClear = createButton("C", 0, 90, 160, 80);
		btnClear.addActionListener(this);
		contentPane.add(btnClear);
		
		JButton btnMultiplication = createButton("*", 160, 90, 80, 80);
		btnMultiplication.addActionListener(this);
		contentPane.add(btnMultiplication);
		
		JButton btnDivision = createButton("/", 240, 90, 80, 80);
		btnDivision.addActionListener(this);
		contentPane.add(btnDivision);
		
		JButton btnSubtraction = createButton("-", 240, 170, 80, 80);
		btnSubtraction.addActionListener(this);
		contentPane.add(btnSubtraction);
		
		JButton btnAddition = createButton("+", 240, 250, 80, 80);
		btnAddition.addActionListener(this);
		contentPane.add(btnAddition);
		
		JButton btnEqual = createButton("=", 240, 330, 80, 160);
		btnEqual.addActionListener(this);
		contentPane.add(btnEqual);
		
		JButton btnZero = createButton("0", 0, 410, 160, 80);
		btnZero.addActionListener(this);
		contentPane.add(btnZero);
		
		JButton btnPoint = createButton(".", 160, 410, 80, 80);
		btnPoint.addActionListener(this);
		contentPane.add(btnPoint);
		
		JPanel panelNumericButtons = new JPanel();
		panelNumericButtons.setBounds(0, 170, 240, 320);
		panelNumericButtons.setLayout(new GridLayout(4,3));
		panelNumericButtons.setBorder(null);
		panelNumericButtons.setBackground(backgroundColor);
		panelNumericButtons.setOpaque(true);
		
		String[] numericButtons = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};
		for(String button : numericButtons) {
			JButton btn = new JButton(button);
			btn.addActionListener(this);
			btn.setHorizontalAlignment(SwingConstants.CENTER);
			btn.setFont(new Font("Tahoma", Font.PLAIN, 36));
			btn.setForeground(textColor);
			btn.setFocusPainted(false);
			btn.setBackground(backgroundColor);
			btn.setBorder(new LineBorder(lineColor, 2));
			panelNumericButtons.add(btn);
		}
		contentPane.add(panelNumericButtons);
		
		for(Component component: contentPane.getComponents()) {
			if(component instanceof JButton) {
				JButton btn = (JButton)component;
				changeColorButton(btn);
			}
			if(component instanceof JPanel) {
				for(Component buttonsPanel: ((JPanel)component).getComponents()) {
					if(buttonsPanel instanceof JButton) {
						JButton btn = (JButton)buttonsPanel;
						changeColorButton(btn);
					}
				}
			}		
		}
		
		btnMode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				blackMode = !blackMode;
				if(blackMode) {
					btnMode.setIcon(new ImageIcon(darkcalculator.class.getResource("/image/whiteMode.png")));
					backgroundColor = new Color(53, 53, 53);
					buttonColor = new Color(70, 71, 74);
					textColor = Color.white;
					lineColor = new Color(53, 53, 53);
					
					contentPane.setBackground(Color.white);
					panelNumericButtons.setBackground(Color.white);
					textFieldDisplay.setBackground(backgroundColor);
					textFieldDisplay.setForeground(Color.white);
					
					for(Component component: contentPane.getComponents()) {
						if(component instanceof JButton) {
							JButton btn = (JButton)component;
							
							btn.setForeground(textColor);
							btn.setBackground(buttonColor);
							btn.setBorder(new LineBorder(lineColor, 2));
							contentPane.setBackground(backgroundColor);
						}
						if(component instanceof JPanel) {
							for(Component buttonsPanel: ((JPanel)component).getComponents()) {
								if(buttonsPanel instanceof JButton) {
									JButton btn = (JButton)buttonsPanel;
//									changeColorButton(btn);
									btn.setForeground(textColor);
									btn.setBackground(buttonColor);
									btn.setBorder(new LineBorder(lineColor, 2));
									contentPane.setBackground(backgroundColor);
								}
							}
						}		
					}
					
				} else {
					btnMode.setIcon(new ImageIcon(darkcalculator.class.getResource("/image/blackMode.png")));
					backgroundColor = Color.white;
					buttonColor = new Color(204, 204, 204);
					textColor = new Color(77, 77, 77);
					lineColor = Color.white;
					contentPane.setBackground(Color.white);
					panelNumericButtons.setBackground(Color.white);
					textFieldDisplay.setBackground(Color.white);
					textFieldDisplay.setForeground(textColor);
					for(Component component: contentPane.getComponents()) {
						if(component instanceof JButton) {
							JButton btn = (JButton)component;
							
							btn.setForeground(textColor);
							btn.setBackground(buttonColor);
							btn.setBorder(new LineBorder(lineColor, 2));
							contentPane.setBackground(backgroundColor);
						}
						if(component instanceof JPanel) {
							for(Component buttonsPanel: ((JPanel)component).getComponents()) {
								if(buttonsPanel instanceof JButton) {
									JButton btn = (JButton)buttonsPanel;
//									changeColorButton(btn);
									btn.setForeground(textColor);
									btn.setBackground(buttonColor);
									btn.setBorder(new LineBorder(lineColor, 2));
									contentPane.setBackground(backgroundColor);
								}
							}
						}		
					}
				}
			}
		});
	}
	
	private void changeColorButton(JButton jbutton) {
		jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jbutton.setBackground(backgroundColor);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				jbutton.setBackground(buttonColor);
			}
		});
	}
	
	private JButton createButton(String textButton, int x, int y, int width, int height) {
		JButton jbutton = new JButton(textButton);
		jbutton.setHorizontalAlignment(SwingConstants.CENTER);
		jbutton.setFont(new Font("Tahoma", Font.PLAIN, textButton.equalsIgnoreCase("x") ? 20 : 36));
		jbutton.setForeground(textColor);
		jbutton.setFocusPainted(false);
		jbutton.setBackground(backgroundColor);
		jbutton.setBorder(new LineBorder(lineColor, 2));
		jbutton.setBounds(x, y, width, height);
		
		return jbutton;
	}

	private void moveScreen() {
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - x, e.getXOnScreen() - y);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if("=".equals(command)) {
			
			String expression = textFieldDisplay.getText();
			double result = checkExpression(expression);
			textFieldDisplay.setText(Double.toString(result));
		} else if("c".equalsIgnoreCase(command)) {
			textFieldDisplay.setText("0");
		} else if("x".equalsIgnoreCase(command)) {
			dispose();
		}else {
			
			String textDisplay = textFieldDisplay.getText();
			char lastChar = textDisplay.charAt(textDisplay.length()-1);
			
			if(isOperator(lastChar) && isOperator(command.charAt(command.length()-1))) {
				
				textDisplay = textDisplay.substring(0, textDisplay.length()-1);
				textFieldDisplay.setText(textDisplay+command);
			} else {
				if(textFieldDisplay.getText().equalsIgnoreCase("0")) {
					textFieldDisplay.setText("");
				}
				
				textFieldDisplay.setText(textFieldDisplay.getText()+command);		
			}
		}
		
	}

	private boolean isOperator(char operator) {
		return operator == '+' || operator == '-' || operator == '*' || operator == '/';
	}
	
	private double checkExpression(String expression) {
		try {
			expression = expression.replaceAll("\\s", "");
			
			if(expression.isEmpty()) {
				return 0;
			}
			
			Pattern pattern = Pattern.compile("[+\\-*/]");
			Matcher matcher = pattern.matcher(expression);
			String[] expressionParts = pattern.split(expression);
			
			double result = Double.parseDouble(expressionParts[0]);
			int i=1;
			
			while(matcher.find()) {
				String operator = matcher.group();
				double value = Double.parseDouble(expressionParts[i++]);
				
				switch(operator) {
				case "+":
					result += value;
					break;
				case "-":
					result -= value;
					break;
				case "*":
					result *= value;
					break;
				case "/":
					result /= value;
					break;
				}
			}
			
			return result;
			
		}catch (NumberFormatException | ArithmeticException e) {
			return 0;
		}
	}
}
