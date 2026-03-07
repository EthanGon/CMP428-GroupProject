public class DefenseUpgrade {
	// Data structure for health upgrade
	// Defense starts at 5. 
	// append + 1, max defense 20.
	// [0-5, 1-6, 2-7, ... 14-20] array max length of 15
	
	private int [] upgradeDefense = new int [15]; // 15 length. 
	int top = -1;
	
	private final int BASE_DEFENSE = 5;
	private final int INCREMENT_DEFENSE = 1;
	private final int MAX_DEFENSE = 20;
	
	
	public DefenseUpgrade() 
	{
		push(BASE_DEFENSE);
		
	}
	
	public void push(int base) 
	{
		if(!isDefenseFull()) {
			upgradeDefense[++top] = base;
		}
	}
	
	public void pushDefenseUpgrade() {
		if(isDefenseFull()) {
			System.out.println("Defense is maxed out");
			return;
		}
		int upgradedDefense = top() + INCREMENT_DEFENSE;
		
		if(upgradedDefense > MAX_DEFENSE) {
			System.out.println("Defense is maxed out");
			return;
		}
		upgradeDefense[++top] = upgradedDefense;
	}
	
	
	
	public int top() 
	{
		return upgradeDefense[top];
	}
	
	public boolean isEmpty() 
	{
		return top == -1;
	}
	
	public boolean isDefenseFull() {
		return top == upgradeDefense.length -1;
	}
	
	//Helper functions for GUI
	
	public int getCurrentDefense() {
		return top();
	}
	
	public int getDefenseLevel() {
		return top;  // 0 means base, 14 means 20
	}
	
	
	
}
