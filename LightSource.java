
public class LightSource {

    public float x, y;
    public int lightTravel;

    public LightSource(float x, float y, int lightTravel) {
        this.x = x * 64;
        this.y = y * 64;
        this.lightTravel = lightTravel * 64;
    }

}
