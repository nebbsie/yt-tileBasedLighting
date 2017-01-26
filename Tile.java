import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile {

    public float x, y;
    public int size;
    public Image img;

    public Tile(float x, float y, int size, Image img) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.img = img;
    }

    public void update(){}

    public void render(Graphics g){
        g.drawImage(img, x, y);
    }
}
