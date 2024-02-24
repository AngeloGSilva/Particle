import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import java.lang.Math;
import java.util.Random;

public class Particle {
    // Instance of random class
    Random rand = new Random();
    Raylib rlj = new Raylib();
    Vector2 pos = new Vector2();
    Vector2 vel = new Vector2();
    Color color;

    public Particle(int screenWidth, int screenHeight, Raylib rlj) {
        rlj = rlj;
        pos.x = rand.nextInt(screenWidth - 1);
        pos.y = rand.nextInt(screenHeight - 1);
        float velX = getRandomValue(-100, 100) / 100.0f;
        float velY = getRandomValue(-100, 100) / 100.0f;
        // color = (Color){0,0,0,100};
        color = color.BLACK;
    }

    // Function to mimic GetRandomValue function
    public static int getRandomValue(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public Particle(Vector2 _pos, Vector2 _vel, Color _color, Raylib rlj) {
        rlj = rlj;
        pos = _pos;
        vel = _vel;
        color = _color;
    }

    float getDist(Vector2 otherPos) {
        float dx = pos.x - otherPos.x;
        float dy = pos.y - otherPos.y;
        return (float) Math.sqrt((dx * dx) + (dy * dy));
    }

    Vector2 getNormal(Vector2 otherPos) {
        float dist = getDist(otherPos);
        if (dist == 0.0f)
            dist = 1;
        float dx = pos.x - otherPos.x;
        float dy = pos.y - otherPos.y;
        return new Vector2(dx * (1 / dist), dy * (1 / dist));
    }

    void attract(Vector2 posToAttract, float multiplier) {
        float dist = Math.max(getDist(posToAttract), 0.5f);
        Vector2 normal = getNormal(posToAttract);

        vel.x -= normal.x / dist;
        vel.y -= normal.y / dist;
    }

    void doFriction(float amount) {
        vel.x *= amount;
        vel.y *= amount;
    }

    void move(int screenWidth, int screenHeight) {
        pos.x += vel.x;
        pos.y += vel.y;
        if (pos.x < 0)
            pos.x += screenWidth;
        if (pos.x >= screenWidth)
            pos.x -= screenWidth;
        if (pos.y < 0)
            pos.y += screenHeight;
        if (pos.y >= screenHeight)
            pos.y -= screenHeight;
    }

    void drawPixel() {
        rlj.shapes.DrawCircleV(pos, 1, color);
    }
}