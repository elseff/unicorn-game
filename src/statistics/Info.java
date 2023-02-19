package statistics;

public class Info {

    public void minusLife(double k) {
        if (life>0) life = life - k;
    }
    public void plusLife(double k) {
        if(life<100) {
            if (life > 0) life = life + k;
        }
    }
    private int amount;
    public int getAmount() {
        return amount;
    }

    public void addAmount(int value) {
        if (value > 0) amount = amount + value;
    }

    private double life;      //жизнь героя
    private int countStone;   //количество собранных камней

    public Info() {
        reset();
    }

    //сброс параметров для перехода на новый уровень
    public void reset() {
        life = 100;
        countStone = 0;
    }

    public void addStone() { countStone++; }

    public double getLife() { return life; }

    public int getCountStone() { return countStone; }
}
