
public class StrengthUpgrade {
	// Data structure for health upgrade
	// Defense starts at 5. 
	// append + 1, max defense 20.
	// [0-5, 1-6, 2-7, ... 14-20] array max length of 15
	
	private int [] upgradeStrength = new int [15]; // 15 length. 
	int top = -1;
	
	private final int BASE_STRENGTH = 5;
	private final int INCREMENT_STRENGTH = 1;
	private final int MAX_STRENGTH = 20;
	
	
	public StrengthUpgrade() 
	{
		push(BASE_STRENGTH);
		
	}
	
	public void push(int base) 
	{
		if(!isStrengthFull()) {
			upgradeStrength[++top] = base;
		}
	}
	
	public void pushStrengthUpgrade() {
		if(isStrengthFull()) {
			System.out.println("Strength is maxed out");
			return;
		}
		int upgradedStrength = top() + INCREMENT_STRENGTH;
		
		if(upgradedStrength > MAX_STRENGTH) {
			System.out.println("Strength is maxed out");
			return;
		}
		upgradeStrength[++top] = upgradedStrength;
	}
	
	
	
	public int top() 
	{
		return upgradeStrength[top];
	}
	
	public boolean isEmpty() 
	{
		return top == -1;
	}
	
	public boolean isStrengthFull() {
		return top == upgradeStrength.length -1;
	}
	
	//Helper functions for GUI
	
	public int getCurrentStrength() {
		return top();
	}
	
	public int getStrengthLevel() {
		return top;  // 0 means base, 14 means 20
	}
	
	
	
}
