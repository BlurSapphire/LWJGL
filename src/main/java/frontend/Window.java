package frontend;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.vector.Matrix4f;
import backend.shaderProgram;
import backend.Mesh;

public class Window {
    private long window;
    private int width, height;

    private String vertex   = "src/main/java/shaders/vertex.glsl";
    private String fragment = "src/main/java/shaders/fragment.glsl";

    private shaderProgram shader;
    private Mesh mesh;

    public Window(){
        width = 800;
        height = 800;
    }

    public void init(){
        GLFW.glfwInit();
        GLFW.glfwDefaultWindowHints();

        window = GLFW.glfwCreateWindow(width, height, "Engine", MemoryUtil.NULL, MemoryUtil.NULL);
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);

        GLFW.glfwShowWindow(window);
        GL.createCapabilities();

        // Вершины и индексы для квадрата
        float[] vertices = {
                // Передняя грань
                -0.5f,  0.5f,  0.5f,  // Вверх-лево
                0.5f,  0.5f,  0.5f,  // Вверх-право
                -0.5f, -0.5f,  0.5f,  // Вниз-лево
                0.5f, -0.5f,  0.5f,  // Вниз-право

                // Задняя грань
                -0.5f,  0.5f, -0.5f,  // Вверх-лево
                0.5f,  0.5f, -0.5f,  // Вверх-право
                -0.5f, -0.5f, -0.5f,  // Вниз-лево
                0.5f, -0.5f, -0.5f   // Вниз-право
        };

        // Индексы для рендеринга куба (2 треугольника для каждой грани)
        int[] indices = {
                0, 1, 2,  1, 3, 2,   // Передняя грань
                4, 5, 6,  5, 7, 6,   // Задняя грань
                4, 0, 6,  6, 0, 2,   // Левая грань
                1, 5, 3,  3, 5, 7,   // Правая грань
                4, 5, 0,  0, 5, 1,   // Верхняя грань
                2, 3, 6,  6, 3, 7    // Нижняя грань
        };

        mesh = new Mesh(vertices, indices);

        try {
            shader = new shaderProgram(vertex, fragment);
            System.out.println("Shader program successfully created.");
        } catch (Exception e) {
            e.printStackTrace();
            shader = null;
        }
    }

    public void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        if (shader != null) {
            shader.use();

        }

        mesh.render();

    }

    public void update(){
        while (!GLFW.glfwWindowShouldClose(window)){
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            render();

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    public void cleanup(){
        if (shader != null) {
            shader.cleanup();
        }
        if (mesh != null) {
            mesh.cleanup();
        }
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
