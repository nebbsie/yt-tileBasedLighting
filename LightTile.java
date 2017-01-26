import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class LightTile {
    public int x, y;
    public float lux = 0.9f;
    public int size;
    private Color colour = new Color(0f, 0f, 0f, lux);
    public ArrayList<LightData> amounts;

    public LightTile(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        amounts = new ArrayList<>();
    }

    public void update(){
        // Set the alpha of the colour to the lux value that was determined
        // from the distance away from the light.
        colour.a = lux;
    }

    public void render(Graphics g){
        g.setColor(colour);
        g.fillRect(x, y, size, size);
        amounts.clear();
    }
}
