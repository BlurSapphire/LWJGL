package backend.org;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.BufferUtils;

public class Mesh {
    private int vaoID;
    private int vboID;
    private int eboID;
    private int BufferCount;

    public Mesh(float[] vertices, int[] indices){
        createMesh(vertices, indices);
    }

    private void createMesh(float[] vertices, int[] indices){

        vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        
    }




}
