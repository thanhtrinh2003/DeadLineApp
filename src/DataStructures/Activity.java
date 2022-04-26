package DataStructures;

import java.time.LocalDateTime;


public class Activity {
	private String name;
	private LocalDateTime dt;
	private int estimatedHour;
	
	//Constructor 
	public Activity(String name, LocalDateTime dt, int estimatedHour)
	{
		this.name = name;
		this.dt = dt;
		this.estimatedHour = estimatedHour;

	}
	
	//getter methods
	public String getName()
	{
		return name;
	}
	
	public LocalDateTime getDeadLine()
	{
		return dt;
	}
	
	public int getEstimatedHour()
	{
		return estimatedHour;
	}

	
	//setter methods
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDeadLine(LocalDateTime dt)
	{
		this.dt = dt;
	}
	
	public void setEstimatedHour(int estimatedHour)
	{
		this.estimatedHour = estimatedHour;
	}
	 
}
