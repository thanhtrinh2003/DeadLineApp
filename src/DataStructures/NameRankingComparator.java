package DataStructures;

import java.util.Comparator;

public class NameRankingComparator implements Comparator<Activity>{
	public int compare(Activity a, Activity b)
	{
		return a.getName().compareToIgnoreCase(b.getName());
	}
}
