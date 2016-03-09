package engineTester;

import entities.Camera;
import entities.Entity;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
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
        "Tutorial 8 - Model, View & Projection Matrices");
        
        DisplayManager.createDisplay();
        Loader loader = new Loader();        
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        
//        float[] vertices = 
//        {
//            -0.5f, 0.5f, 0.0f,
//            -0.5f,-0.5f, 0.0f,
//             0.5f,-0.5f, 0.0f,
//             0.5f, 0.5f, 0.0f
//        };
//        
//        int[] indices = 
//        {
//            0, 1, 3,
//            3, 1, 2
//        };        
//        
//        float[] textureCoords = 
//        {
//            0, 0,
//            0, 1, 
//            1, 1,
//            1, 0
//        };
        
        float[] vertices = 
        {
            -0.5f, 0.5f, 0.0f,
            -0.5f,-0.5f, 0.0f,
             0.5f,-0.5f, 0.0f,
             0.5f, 0.5f, 0.0f,
             
            -0.5f, 0.5f, 1.0f,
            -0.5f,-0.5f, 1.0f,
             0.5f,-0.5f, 1.0f,
             0.5f, 0.5f, 1.0f,
             
             0.5f, 0.5f, 0.0f,
             0.5f,-0.5f, 0.0f,
             0.5f,-0.5f, 1.0f,
             0.5f, 0.5f, 1.0f,
             
            -0.5f, 0.5f, 0.0f,
            -0.5f,-0.5f, 0.0f,
            -0.5f,-0.5f, 1.0f,
            -0.5f, 0.5f, 1.0f,
            
            -0.5f, 0.5f, 1.0f,
            -0.5f, 0.5f, 0.0f,
             0.5f, 0.5f, 0.0f,
             0.5f, 0.5f, 1.0f,
             
            -0.5f,-0.5f, 1.0f,
            -0.5f,-0.5f, 0.0f,
             0.5f,-0.5f, 0.0f,
             0.5f,-0.5f, 1.0f
        };
        
        float[] textureCoords = 
        {
            0,0,
            0,1,
            1,1,
            1,0,
            
            0,0,
            0,1,
            1,1,
            1,0,
            
            0,0,
            0,1,
            1,1,
            1,0,
            
            0,0,
            0,1,
            1,1,
            1,0,
            
            0,0,
            0,1,
            1,1,
            1,0,
            
            0,0,
            0,1,
            1,1,
            1,0,                        
        };
        
        int[] indices = 
        {
            0, 1, 3,
            3, 1, 2,
            4, 5, 7,
            7, 5, 6,
            8, 9, 11,
            11, 9, 10,
            12, 13, 15,
            15, 13, 14,
            16, 17, 19,
            19, 17, 18,
            20, 21, 23,
            23, 21, 22
        };
        
        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        
        Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -5), 0, 0, 0, 1);
        
        Camera camera = new Camera();
        
        while(!Display.isCloseRequested())
        {            
            entity.increaseRotation(1, 0, 0);
//            entity.increaseRotation(0, 0, 1);
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
