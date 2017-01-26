import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class PlayState extends BasicGameState {

    private int state;
    private ArrayList<Tile> tiles;
    private ArrayList<LightTile> tilelights;
    private ArrayList<LightSource> lights;
    private Image grass;
    private int lightsize = 32;
    private Input input;

    public PlayState(int state){
        this.state = state;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        grass = new Image("grass.png"); // Loads the grass image
        loadTiles(); // Loads the tiles
        input = container.getInput(); // Sets up the input
        lights = new ArrayList<>();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // Updates all of the tiles
        for(Tile t : tiles){
            t.update();
        }

        // Updates and sets the lux depending on how far away the tile is from
        // the light source. Also check how far away the tile is from the mouse
        // position and draw a light at that position.
        for(LightTile lt : tilelights){
            lt.lux = 0.9f;

            // Iterate through each light.
            for(LightSource l : lights){
                float distance = distance(l.x, l.y, lt.x + lt.size/2, lt.y + lt.size/2);
                if(distance < l.lightTravel){
                    float temp = map(distance, 0, l.lightTravel, 1f, 0f);
                    lt.lux -= temp;
                    lt.amounts.add(new LightData(temp, distance, l.lightTravel));
                }
            }

            // Check the position of the mouse and set the light based on the distance.
            float distance = distance(input.getMouseX(), input.getMouseY(), lt.x + lt.size/2, lt.y + lt.size/2);
            if(distance < 7 * 64){
                lt.lux -= map(distance, 0, 7 * 64, 1f, 0f);
            }

            lt.update();
        }

        // Create a light at the mouse position when left clicked.
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            lights.add(new LightSource(input.getMouseX() / 64, input.getMouseY() / 64, 7));
        }

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // Drawing the grass
        for(Tile t : tiles){
            t.render(g);
        }

        // Drawing the lights
        for(LightTile lt : tilelights){
            lt.render(g);
        }
    }

    // Gets the distance
    private static float distance(float x1, float y1, float x2, float y2){
        return (float)Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    // Maps a value between two numbers
    private static float map(float value, int minFrom, int maxFrom, float minTo, float maxTo) {
        return minTo + (maxTo - minTo) * ((value - minFrom) / (maxFrom - minFrom));
    }

    // Loads all of the tiles.
    private void loadTiles(){
        tiles = new ArrayList<>();
        tilelights = new ArrayList<>();
        int size = 64;
        // Create new tiles and light tiles for each position on the screen.
        for(int x = 0; x < Lighting.WIDTH; x += size){
            for(int y = 0; y < Lighting.HEIGHT; y += size){
                tiles.add(new Tile(x, y, size, grass));
                for(int xx = 0; xx < size; xx += lightsize){
                    for(int yy = 0; yy < size; yy += lightsize){
                        tilelights.add(new LightTile(x + xx, y + yy, lightsize));
                    }
                }
            }
        }

        System.out.println("Created " + tiles.size() + " tiles.");
        System.out.println("Created " + tilelights.size() + " light tiles.");
    }

    @Override
    public int getID() {
        return state;
    }
}
