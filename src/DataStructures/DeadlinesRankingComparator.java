package DataStructures;

import java.util.Comparator;

public class DeadlinesRankingComparator implements Comparator<Activity>{
	public int compare(Activity a, Activity b)
	{
		// comparing Year first
		if(a.getDeadLine().isBefore(b.getDeadLine()))
			return -1;
		else if(a.getDeadLine().isAfter(b.getDeadLine()))
			return 1;
		else
			return 0;
	}
}
