import java.util.ArrayList;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;

public class main {

    public static void main(String[] args) {
        Raylib rlj = new Raylib();

        int screenW = 800;
        int screenH = 800;
        int targetFps = 60;

        int particleCount = 500;
        ArrayList<Particle> particles = new ArrayList<Particle>();

        for (int i = 0; i < particleCount; i++) {
            particles.add(new Particle(screenW, screenH, rlj));
        }

        rlj.core.InitWindow(screenW, screenH, "ParticleToy");

        rlj.core.SetTargetFPS(targetFps);

        // Main Loop
        while (!rlj.core.WindowShouldClose()) // Detect window close button or ESC key
        {
            Vector2 mousePos = rCore.GetMousePosition();
            for (int i = 0; i < particleCount; i++) {
                particles.get(i).attract(mousePos, 1);

                particles.get(i).doFriction((float) 0.99);

                particles.get(i).move(screenW, screenH);

            }

            // Desenhar
            rlj.core.BeginDrawing();

            rlj.core.ClearBackground(Color.RAYWHITE);

            for (int i = 0; i < particleCount; i++) {
                particles.get(i).drawPixel();
            }

            rlj.text.DrawFPS(10, 10);

            rlj.core.EndDrawing();
        }
        rlj.core.CloseWindow(); // Close window and OpenGL context
    }
}