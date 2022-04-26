package GUI;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class AddPanel extends JPanel{
	private final MainFrame main;
	private JTextField NameTextField;
	private JTextField DeadLineTextField;
	private JTextField EstimatedTimeTextField;
	private JButton cancelButton;
	
	public AddPanel(MainFrame m)
	{
		main = m;
		jframe();
	}
	
	private void jframe() 
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 226, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel NameLabel = new JLabel("Name");
		GridBagConstraints gbc_NameLabel = new GridBagConstraints();
		gbc_NameLabel.anchor = GridBagConstraints.WEST;
		gbc_NameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_NameLabel.gridx = 1;
		gbc_NameLabel.gridy = 1;
		add(NameLabel, gbc_NameLabel);
		
		NameTextField = new JTextField();
		GridBagConstraints gbc_NameTextField = new GridBagConstraints();
		gbc_NameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_NameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_NameTextField.gridx = 4;
		gbc_NameTextField.gridy = 1;
		add(NameTextField, gbc_NameTextField);
		NameTextField.setColumns(10);
		
		JLabel DeadLineLabel = new JLabel("Deadline");
		GridBagConstraints gbc_DeadLineLabel = new GridBagConstraints();
		gbc_DeadLineLabel.anchor = GridBagConstraints.WEST;
		gbc_DeadLineLabel.insets = new Insets(0, 0, 5, 5);
		gbc_DeadLineLabel.gridx = 1;
		gbc_DeadLineLabel.gridy = 2;
		add(DeadLineLabel, gbc_DeadLineLabel);
		
		DeadLineTextField = new JTextField();
		
		//setting current time for the textfield
		LocalDateTime now = LocalDateTime.now();
		
		
		DeadLineTextField.setText(now.getYear() + "," + now.getMonthValue() + "," + now.getDayOfMonth() + "," + now.getHour() + ","
				+ now.getMinute() + "," + now.getSecond());
		
		
		GridBagConstraints gbc_DeadLineTextField = new GridBagConstraints();
		gbc_DeadLineTextField.insets = new Insets(0, 0, 5, 0);
		gbc_DeadLineTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_DeadLineTextField.gridx = 4;
		gbc_DeadLineTextField.gridy = 2;
		add(DeadLineTextField, gbc_DeadLineTextField);
		DeadLineTextField.setColumns(10);
		
		JLabel EstimatedTimeLabel = new JLabel("Estimated Time");
		GridBagConstraints gbc_EstimatedTimeLabel = new GridBagConstraints();
		gbc_EstimatedTimeLabel.anchor = GridBagConstraints.WEST;
		gbc_EstimatedTimeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_EstimatedTimeLabel.gridx = 1;
		gbc_EstimatedTimeLabel.gridy = 3;
		add(EstimatedTimeLabel, gbc_EstimatedTimeLabel);
		
		EstimatedTimeTextField = new JTextField();
		
		
		GridBagConstraints gbc_EstimatedTimeTextField = new GridBagConstraints();
		gbc_EstimatedTimeTextField.insets = new Insets(0, 0, 5, 0);
		gbc_EstimatedTimeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_EstimatedTimeTextField.gridx = 4;
		gbc_EstimatedTimeTextField.gridy = 3;
		add(EstimatedTimeTextField, gbc_EstimatedTimeTextField);
		EstimatedTimeTextField.setColumns(10);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitButtonActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Estimated Time is in hours");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 6;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Deadline is in the following format: (Year),(Month),(Day),(Hour),(Minute),(Second)");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 4;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 7;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		GridBagConstraints gbc_submitButton = new GridBagConstraints();
		gbc_submitButton.anchor = GridBagConstraints.WEST;
		gbc_submitButton.insets = new Insets(0, 0, 0, 5);
		gbc_submitButton.gridx = 1;
		gbc_submitButton.gridy = 9;
		add(submitButton, gbc_submitButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.EAST;
		gbc_cancelButton.gridx = 4;
		gbc_cancelButton.gridy = 9;
		add(cancelButton, gbc_cancelButton);
	}
	
	private void submitButtonActionPerformed(ActionEvent evt)
	{
		try{
            String name = NameTextField.getText();
            String deadline = DeadLineTextField.getText();
            String[] values = deadline.split(",");
            boolean check = true;
            
            if(!isNumeric(EstimatedTimeTextField.getText()))
            	check = false;
            for(int i=0; i<=5; i++)
            {
            	if(!isNumeric(values[i]))
            		check = false;
            }
            
            if(check)
            {
				int time = Integer.parseInt(EstimatedTimeTextField.getText());
				int year = Integer.parseInt(values[0]);
				int month = Integer.parseInt(values[1]);
				int day = Integer.parseInt(values[2]);
				int hour = Integer.parseInt(values[3]);
				int minute = Integer.parseInt(values[4]);
				int second = Integer.parseInt(values[5]);
            	if(goodFormat(time, year, month, day, hour, minute, second))
            	{
            		LocalDateTime dt = LocalDateTime.of(year, month, day, hour, minute, second);
                    main.addActivity(name, dt, time);
            	}
            	else
            	{
            		JOptionPane.showMessageDialog(main, "Please check your format!");
            	}
            }
            else
            {
            	JOptionPane.showMessageDialog(null, "Please check your input!");
            }
            

           
            
            
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Error trying to add athlete: " + ex,
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        ((JDialog) this.getTopLevelAncestor()).dispose();
    }//GEN-LAST:event_submitButtonActionPerformed
	
	
	public boolean goodFormat(int time, int year, int month, int day, int hour, int minute, int second)
	{
		if(time <= 0)
			return false;
		
		LocalDateTime now = LocalDateTime.now();
		if(month < 1 || month >12)
			return false;
		if(day<1 || day>31)
			return false;
		if(hour<0 || hour > 24)
			return false;
		if(minute<0 || minute >60)
			return false;
		if(second<0 || second >60)
			return false;
		
		return true;
		
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

	
	private void cancelButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        ((JDialog) this.getTopLevelAncestor()).dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
	
}
