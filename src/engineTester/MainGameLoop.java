package engineTester;

import entities.Camera;
import entities.Entity;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

    public MainGameLoop()
    {
        
    }
    
    public static void main(String[] args)
    {
        System.err.println("Tutorial 1 - The Display" + "\n" +
        "Tutorial 2 - VAOs and VBOs" + "\n" +
        "Tutorial 3 - Rendering with Index Buffers" + "\n" +
        "Tutorial 4 - Introdution to Shaders" + "\n" +
        "Tutorial 5 - Coloring using Shaders" + "\n" +
        "Tutorial 6 - Texturing" + "\n" +
        "Tutorial 7 - Matrices and Uniform Variables" + "\n" + 
        "Tutorial 8 - Model, View & Projection Matrices" + "\n" + 
        "Tutorial 9 - OBJ File Format" + "\n" +
        "Tutorial 10 - Loading 3D Models");
        
        DisplayManager.createDisplay();
        Loader loader = new Loader();        
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
                
        RawModel model = OBJLoader.loadObjModel("stall", loader);
        
        ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
        
        TexturedModel texturedModel = new TexturedModel(model, texture);
        
        Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -50), 0, 0, 0, 1);
        
        Camera camera = new Camera();
        
        while(!Display.isCloseRequested())
        {            
//            entity.increaseRotation(0, 1, 0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        
        shader.cleanUp();
        loader.cleanUP();
        DisplayManager.closeDisplay();
    }
}
