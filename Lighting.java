import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Lighting extends StateBasedGame {

    public static int WIDTH = 1280;
    public static int HEIGHT = 720;
    public static int FPS = 60;

    public static final int lighting = 1;


    public Lighting(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new PlayState(lighting));
        enterState(lighting);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Lighting("Tile Lighting"));
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setShowFPS(true);
        //app.setTargetFrameRate(FPS);
        app.setVSync(false);
        app.start();
    }
}
