public class FastMonster extends Monster {
    public FastMonster(int x, int y) {
        super(x, y, 48, 48);
        maxHp = 15;
        currentHp = 15;
        moveSpeed = 4;
    }
}