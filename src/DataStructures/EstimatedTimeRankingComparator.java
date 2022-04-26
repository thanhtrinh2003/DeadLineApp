package DataStructures;

import java.util.Comparator;

public class EstimatedTimeRankingComparator implements Comparator<Activity>{
	public int compare(Activity a, Activity b)
	{
		return Integer.compare(a.getEstimatedHour(), b.getEstimatedHour());
	}
}
