
public class HealthUpgrade {
	// Data structure for health upgrade
	// Health starts at 100. 
	// append + 10, max health 250.
	// [0-100, 1-110, 2-120,.. 14-250] array max length of 15

	
	private int [] upgradeHealth = new int [15]; // fixed size to cap at 250 for health. 15 length array. 
	private int top = -1;
	
	private final int BASE_HEALTH = 100;
	private final int INCREMENT = 10;
	private final int MAX_HEALTH = 250;
	
	
	public HealthUpgrade() 
	{
		push(BASE_HEALTH); //Health starts at 100
		
	}
	
	public void push(int base) 
	{
		if (!isFull()) 
		{
			upgradeHealth[++top] = base;
		}
	}
	
	public void pushHealthUpgrade() {
	    if (isFull()) {
	        System.out.println("Health is maxed out");
	        return;
	    }

	    int upgradedHealth = top() + INCREMENT;

	    if (upgradedHealth > MAX_HEALTH) {
	        System.out.println("Health is maxed out");
	        return;
	    }

	    upgradeHealth[++top] = upgradedHealth;
	}
	
	public int top() 
	{
		return upgradeHealth[top];
	}
	
	public boolean isEmpty() 
	{
		return top == -1;
	}
	
	public boolean isFull() 
	{
		return top == upgradeHealth.length -1;
	}
	
	
	//Helper functions for GUI 
	public int getCurrentHealth() {
	    return top();
	}
	
	public int getHealthLevel() {
	    return top; // 0 means base 100, 14 means 250
	}
	
	
}
