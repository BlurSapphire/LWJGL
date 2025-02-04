package frontend.org;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import backend.org.Mesh;
import shaders.ShaderProgram;

public class Window {

    //приватные переменные -> private vars for window, width and height
    private int width, height;
    private long window;
    private Mesh mesh;

    public Window(){
        width = 800;
        height = 600;
    }

    //инициализация -> initialise the window and stuff
    public void init(){

        //вывод в консоль в случае ошибки инициализации окна
        if(!GLFW.glfwInit()){
            System.out.println("Failed to initialise window");
        }

        GLFW.glfwDefaultWindowHints();

        // создание окна -> window creation
        window = GLFW.glfwCreateWindow(width, height, "Game", MemoryUtil.NULL, MemoryUtil.NULL);

        GLFW.glfwMakeContextCurrent(window);

        GL.createCapabilities();

        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(window);


        ShaderProgram shaderProgram = new ShaderProgram("shaders/vertex.glsl", "shaders/fragment.glsl");

        float[] vertices = {
                -0.5f,  0.5f, 0.5f,  // 0
                0.5f,  0.5f, 0.5f,  // 1
                -0.5f, -0.5f, 0.5f,  // 2
                0.5f, -0.5f, 0.5f,  // 3
                -0.5f,  0.5f, -0.5f, // 4
                0.5f,  0.5f, -0.5f, // 5
                -0.5f, -0.5f, -0.5f, // 6
                0.5f, -0.5f, -0.5f  // 7
        };

        int[] indices = {
                0, 1, 2,  1, 3, 2,
                4, 6, 5,  5, 6, 7,
                4, 0, 6,  6, 0, 2,
                1, 5, 3,  3, 5, 7,
                4, 5, 0,  0, 5, 1,
                2, 3, 6,  6, 3, 7
        };


        mesh = new Mesh(vertices, indices, shaderProgram);

    }

    public void update(){

        //характеристики окна пока окно выводится на экран -> assigning to the win its characteristics while running
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Устанавливаем цвет фона -> bg colour setup
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            mesh.render();

            // Обновление окна -> window update
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    //функция для уборки/уничтожения окна -> function meant to close the window
    public void cleanUp(){
        mesh.cleanUP();
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    //функция для передачи в main нужная для запуска программы -> func meant to transfer all func to the main file needed for running the program
    public void run(){
        init();
        update();
        cleanUp();
    }
}
