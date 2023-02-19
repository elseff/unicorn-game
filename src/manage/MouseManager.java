package manage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {
    public int userPressX = -1;
    public int userPressY = -1;
    public boolean isPressMouse = false;
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mousePressed(MouseEvent e) {
        userPressX = e.getX();
        userPressY = e.getY();
        isPressMouse = true;
        System.out.println("Нажатие мышки X,Y " + userPressX + ", " + userPressY);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        isPressMouse = false;
    }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
