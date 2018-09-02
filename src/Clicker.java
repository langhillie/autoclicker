import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clicker extends JFrame {
	
	public static boolean isActive;
	
	private JLabel delayL, varianceL;
	private JTextField delayTF, varianceTF;
	private JButton startB, stopB;
	
	public Clicker() {
		
		isActive = false;
		
		delayL = new JLabel("Min Delay (ms): ", SwingConstants.CENTER);
		varianceL = new JLabel("Max Delay (ms): ", SwingConstants.CENTER);
		delayTF = new JTextField("2000", 10);
		varianceTF = new JTextField("1000", 10);
        
		startB = new JButton("Start");
		stopB = new JButton("Stop");
		
		Container pane = getContentPane();

		pane.setLayout(new GridLayout(3, 2));			       
		pane.add(delayL);
		pane.add(delayTF);
		pane.add(varianceL);
		pane.add(varianceTF);
		pane.add(startB);
		pane.add(stopB);

		setTitle("Clicker");
        setSize(200, 100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
		startB.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
					if (!isActive) {
						isActive = true;
						// Ensuring only integers are used
						try {
							// Initializing new thread for the click loop
							int delay = Integer.parseInt(delayTF.getText());
							int variance = Integer.parseInt(varianceTF.getText());
							LoopThread clickLoop = new LoopThread(delay, variance);
							startB.setText("Running...");
							clickLoop.start();
						} catch (NumberFormatException e) {
							System.out.println("Non integer detected!");
						}
					}
			}          
		});
		
		stopB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					isActive = false;
					startB.setText("Start");
			}          
		});
	}         
	
	public static void main(String[] args) {
		Clicker gui = new Clicker();
	}
}
