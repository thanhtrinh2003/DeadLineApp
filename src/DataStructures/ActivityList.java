package DataStructures;

import java.util.*;

public class ActivityList {
	
	private ArrayList<Activity>  activityList;
	
	public ActivityList()
	{
		activityList = new ArrayList<Activity>();
	}
	
	public void add(Activity a)
	{
		activityList.add(a);
	}
	
	public void remove(int index)
	{
		activityList.remove(index);
	}
	
	public void deleteActivity(int index)
	{
		activityList.remove(index);
	}
	
	public int getSize()
	{
		return activityList.size();
	}
	
	public Activity get(int index)
	{
		return activityList.get(index);
	}
	
	public void sort(Comparator<Activity> compare)
	{
		Collections.sort(activityList, compare);
	}
	
}

