package frontend;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import backend.shader_config;

public class Window {
    private long window;
    private int width, height;


    public Window(){
        width = 800;
        height = 800;
    }

    public void init(){
        GLFW.glfwInit();


        window = GLFW.glfwCreateWindow(width, height, "Engine", MemoryUtil.NULL, MemoryUtil.NULL);
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);

        GLFW.glfwShowWindow(window);
        GLFW.glfwDefaultWindowHints();
        GL.createCapabilities();
    }

    public void update(){
        while (!GLFW.glfwWindowShouldClose(window)){

            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);


            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    public void cleanup(){

        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
