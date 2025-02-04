package frontend.org;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class Window {

    //приватные переменные -> private vars for window, width and height
    private int width, height;
    private long window;


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
    }

    public void update(){

        //характеристики окна пока окно выводится на экран -> assigning to the win its characteristics while running
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Устанавливаем цвет фона -> bg colour setup
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            // Обновление окна -> window update
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    //функция для уборки/уничтожения окна -> function meant to close the window
    public void cleanUp(){
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
