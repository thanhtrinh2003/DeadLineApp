package GUI;

import DataStructures.Activity;

import DataStructures.NameRankingComparator;
import DataStructures.ActivityList;
import DataStructures.DeadlinesRankingComparator;
import DataStructures.EstimatedTimeRankingComparator;
import StaticClasses.InfoTableModel;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import java.util.concurrent.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainFrame extends javax.swing.JFrame {
	
	//variable
	private ActivityList list;
	
	//JFrame variable
	public JFrame frame;
	private JTable activityTable;
	private GridBagLayout gridBagLayout;
	private JLabel lblActivityList; 
	private JScrollPane activityPane;
	private JButton btnAddActivity;
	private JButton btnDeleteActivity;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmNewMenuItem;
	private JMenu mnEdit;
	private JMenuItem mntmRankByName;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	
	
	public MainFrame() {
		frame = new JFrame("DeadLineApp");
		list = new ActivityList();
		frame.setSize(700, 390);
		
		
		//frame format
		jframe();
		load();
		updatePanel();
		frame.setVisible(true);
		
		
		//table format
		TableColumnModel columnModel = activityTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(110);
		columnModel.getColumn(3).setPreferredWidth(300);
		columnModel.getColumn(4).setPreferredWidth(200);
		// running
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(activityTable.getSelectionModel().isSelectionEmpty() == true)
			{
				updatePanel();
				columnModel.getColumn(0).setPreferredWidth(200);
				columnModel.getColumn(1).setPreferredWidth(200);
				columnModel.getColumn(2).setPreferredWidth(110);
				columnModel.getColumn(3).setPreferredWidth(300);
				columnModel.getColumn(4).setPreferredWidth(200);
			}
		}
		
		
	} 
	
	// *************************** INPUT & OUTPUT **********************************//
	// *****************************************************************************//
	
	private String dir = System.getProperty("user.dir");
	private String filePath = dir + "/DATA/default.txt";
	private JButton btnClearSelection;
	
	public void load()
	{
		try {
            // Load file and read info to RAM from file
            BufferedReader loadFile;
            loadFile = new BufferedReader(new FileReader(filePath));

            // Create a string to read in data from the file
            String input;
            
            /** Read in from text file 2 lines for each athlete while there are 
             * still lines to be read in
             * First line is their personal info (first, last, number, position)
             * Second line is their goal data (0, 3, 1, 0, 1, etc.)
             */ 
            while ((input = loadFile.readLine()) != null) {
                String[] values = input.split(",");
                String name = values[0];
                int year = Integer.parseInt(values[1]);
                int month = Integer.parseInt(values[2]);
                int day = Integer.parseInt(values[3]);
                int hour = Integer.parseInt(values[4]);
                int minute = Integer.parseInt(values[5]);
                int second = Integer.parseInt(values[6]);
                int expectedTime = Integer.parseInt(values[7]);
                
                LocalDateTime a = LocalDateTime.of(year, month, day, hour, minute, second);
                
                list.add(new Activity(name, a, expectedTime));

            }
		} catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error trying to "
                    + "load file: " + ex,
                    "Load Error",
                    JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void save()
	{
		// Output the data as text file
		try (PrintWriter writer = new PrintWriter(new File(filePath))) {
        	
            // Loop through lists and write their information to the file
            // Each line is a activity, containing
            for (int n = 0; n < list.getSize(); n++) {
                writer.println(list.get(n).getName() + ","
                        + list.get(n).getDeadLine().getYear() + ","
                        + list.get(n).getDeadLine().getMonthValue() + ","
                        + list.get(n).getDeadLine().getDayOfMonth() + ","
                        + list.get(n).getDeadLine().getHour() + ","
                        + list.get(n).getDeadLine().getMinute() + ","
                        + list.get(n).getDeadLine().getSecond() + ","
                        + list.get(n).getEstimatedHour());
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error trying to load file: " + ex,
                    "Load Error",
                    JOptionPane.ERROR_MESSAGE);
        }
	}
	
	
	
	
	// *************************** JFRAME STUFF **********************************//
	// ***************************************************************************//
	
	
	private void jframe()
	{
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{34, 239, 102, 223, 17, 0};
		gridBagLayout.rowHeights = new int[]{30, 216, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		lblActivityList = new JLabel("Activity List");
		GridBagConstraints gbc_lblActivityList = new GridBagConstraints();
		gbc_lblActivityList.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivityList.gridx = 2;
		gbc_lblActivityList.gridy = 0;
		frame.getContentPane().add(lblActivityList, gbc_lblActivityList);
		
		activityPane = new JScrollPane();
		GridBagConstraints gbc_activityPane = new GridBagConstraints();
		gbc_activityPane.insets = new Insets(0, 0, 5, 5);
		gbc_activityPane.gridwidth = 3;
		gbc_activityPane.fill = GridBagConstraints.BOTH;
		gbc_activityPane.gridx = 1;
		gbc_activityPane.gridy = 1;
		frame.getContentPane().add(activityPane, gbc_activityPane);
		
		activityTable = new JTable();
		activityPane.setViewportView(activityTable);
		
		btnAddActivity = new JButton("Add Activity");
		btnAddActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActivityButtonActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnAddActivity = new GridBagConstraints();
		gbc_btnAddActivity.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddActivity.gridx = 1;
		gbc_btnAddActivity.gridy = 2;
		frame.getContentPane().add(btnAddActivity, gbc_btnAddActivity);
		
		btnDeleteActivity = new JButton("Delete Activity");
		btnDeleteActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActivityButtonActionPerformed(e);
			}
		});
		
		btnClearSelection = new JButton("Clear Selection");
		btnClearSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSelectionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnClearSelection = new GridBagConstraints();
		gbc_btnClearSelection.insets = new Insets(0, 0, 0, 5);
		gbc_btnClearSelection.gridx = 2;
		gbc_btnClearSelection.gridy = 2;
		frame.getContentPane().add(btnClearSelection, gbc_btnClearSelection);
		GridBagConstraints gbc_btnDeleteActivity = new GridBagConstraints();
		gbc_btnDeleteActivity.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteActivity.gridx = 3;
		gbc_btnDeleteActivity.gridy = 2;
		frame.getContentPane().add(btnDeleteActivity, gbc_btnDeleteActivity);
		
		menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNewMenuItem = new JMenuItem("Save");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		mnFile.add(mntmNewMenuItem);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmRankByName = new JMenuItem("Rank By Name");
		mntmRankByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameRankingActionPerformed(e);
			}
		});
		mnEdit.add(mntmRankByName);
		
		mntmNewMenuItem_2 = new JMenuItem("Rank By Deadlines");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deadlineTimeRankingActionPerformed(e);
			}
		});
		mnEdit.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_3 = new JMenuItem("Rank By Estimated Time");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estimatedTimeRankingActionPerformed(e);
			}
		});
		mnEdit.add(mntmNewMenuItem_3);
	
	}
	
	
	
	private void updatePanel()
	{
		drawActivityPanel();
	}
	
	private void drawActivityPanel()
	{
		String[] header = {"Name", "Deadline", "Estimated Time (in hour)", "Time-Left", "Status"};
		String[][] activityData = new String[list.getSize()][header.length];
		
		
		for(int i=0; i< activityData.length; i++)
		{
			//breaking down Local Date Time
			LocalDateTime datetime = list.get(i).getDeadLine();
			long Year = datetime.getYear();
			long Month = datetime.getMonthValue();
			long Day = datetime.getDayOfMonth();
			long Hour = datetime.getHour();
			long HourTo;
			long Minute = datetime.getMinute();
			long Second = datetime.getSecond();
			String current = "" + Month + "/" + Day + "/" + Year + " " + Hour + ":" + Minute + ":" +Second;

			
			LocalDateTime tempDateTime = LocalDateTime.now();
			LocalDateTime toDateTime = LocalDateTime.from(datetime);
			
			HourTo = tempDateTime.until( toDateTime, ChronoUnit.HOURS );
			
			Year = tempDateTime.until( toDateTime, ChronoUnit.YEARS );
			tempDateTime = tempDateTime.plusYears(Year);

			Month = tempDateTime.until( toDateTime, ChronoUnit.MONTHS );
			tempDateTime = tempDateTime.plusMonths(Month);

			Day = tempDateTime.until( toDateTime, ChronoUnit.DAYS );
			tempDateTime = tempDateTime.plusDays(Day);

			Hour = tempDateTime.until( toDateTime, ChronoUnit.HOURS );
			tempDateTime = tempDateTime.plusHours(Hour);

			Minute = tempDateTime.until( toDateTime, ChronoUnit.MINUTES );
			tempDateTime = tempDateTime.plusMinutes(Minute);

			Second = tempDateTime.until( toDateTime, ChronoUnit.SECONDS );
			
			String left = "";
			
			boolean check = false;
			
			if(Year > 0)
			{
				check = true;
				left += Year + " year ";
			}
			if(Month > 0)
			{
				check = true;
				left += Month + " month ";
			}
			if(Day > 0)
			{
				check = true;
				left += Day + " day ";
			}
			if(Hour > 0)
			{
				check = true;
				left += Hour + " hour ";
			}
			if(Minute > 0)
			{
				check = true;
				left += Minute + " minute ";
			}
			if(Second > 0)
			{
				check = true;
				left += Second + " second ";
			}
			if(check == false)
			{
				left = "ALREADY PASSED DEADLINE!";
			}
			
			long estimatedHour = list.get(i).getEstimatedHour();
			
			// filling in data
			activityData[i][0] = list.get(i).getName();
			activityData[i][1] = current;
			activityData[i][2] = String.valueOf(list.get(i).getEstimatedHour());
			activityData[i][3] = left;
			
			if(HourTo - estimatedHour > 2)
				activityData[i][4] = "Chill! You have tons of time!";
			else if(HourTo -estimatedHour > -1)
				activityData[i][4] = "Start it now!";
			else 
				activityData[i][4] = "Rush! Rush! Rush!";
			
			if(estimatedHour<0)
				activityData[i][4] = "~";
			
		}
		
//		DefaultTableModel model = new DefaultTableModel();
//		activityTable = new JTable(model);
//		model.addColumn("Name");
//		model.addColumn("Deadline");
//		model.addColumn("Time left");
//		model.addColumn("time requires");
//		model.addColumn("Note");
//
//		model.addRow(activityData[0]);
//		
//		
//		
//		//activityTable.setModel(new InfoTableModel(activityData2, header));
		
		activityTable.setModel(new InfoTableModel(activityData, header));
		
	}
	
	// *************************** JFRAME DATA  **********************************//
	// ***************************************************************************//
	
	public void addActivity(String name, LocalDateTime dt, int hour)
	{
		list.add(new Activity(name, dt, hour));
	}
	
	public void deleteActivity(int index)
	{
		list.remove(index);
	}
	
	private void addActivityButtonActionPerformed(ActionEvent evt)
	{
		JDialog dialog = new JDialog(this, true);
		AddPanel add = new AddPanel(this);
		add.setBorder(new EmptyBorder(10,10,10,10));
		dialog.getContentPane().add(add);
		dialog.pack();
		dialog.setVisible(true);
		
		updatePanel();
	}
	
	private void deleteActivityButtonActionPerformed(ActionEvent evt)
	{
		int index = activityTable.getSelectedRow();
        if(index == -1)
        {
        	JOptionPane.showMessageDialog(this, "Please select the player you want to delete");
        }
        else
        {
        	deleteActivity(index);
            updatePanel();
        }
	}
	
	public void clearSelectionPerformed(ActionEvent evt)
	{
		activityTable.getSelectionModel().clearSelection();
	}
	
	public void nameRankingActionPerformed(ActionEvent evt)
	{
		NameRankingComparator nameRank = new NameRankingComparator();
		list.sort(nameRank);
	}
	
	public void estimatedTimeRankingActionPerformed(ActionEvent evt)
	{
		EstimatedTimeRankingComparator etRank = new EstimatedTimeRankingComparator();
		list.sort(etRank);
	}
	
	public void deadlineTimeRankingActionPerformed(ActionEvent evt)
	{
		DeadlinesRankingComparator dlRank = new DeadlinesRankingComparator();
		list.sort(dlRank);
	}
	
}
