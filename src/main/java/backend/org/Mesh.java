package backend.org;

import org.lwjgl.opengl.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import shaders.ShaderProgram;

public class Mesh {
    private int vaoID;
    private int vboID;
    private int eboID;
    private int vertexCount;
    private ShaderProgram shaderProgram;


    public Mesh(float[] vertices, int[] indices, ShaderProgram shader){

        vertexCount = indices.length;

        this.shaderProgram = shader;

        vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);

        vboID = GL30.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);

        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertices.length);
        vertexBuffer.put(vertices).flip();
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER,vertexBuffer, GL30.GL_STATIC_DRAW);
        MemoryUtil.memFree(vertexBuffer);

        eboID = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, eboID);

        IntBuffer indexBuffer = MemoryUtil.memAllocInt(indices.length);
        indexBuffer.put(indices).flip();
        GL30.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL15.GL_STATIC_DRAW);
        MemoryUtil.memFree(indexBuffer);

        GL30.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 3 * Float.BYTES, 0);
        GL30.glEnableVertexAttribArray(0);


        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);


        
    }

    public void render(){
        shaderProgram.use();
        GL30.glBindVertexArray(vaoID);
        GL30.glDrawElements(GL30.GL_TRIANGLES, vertexCount, GL30.GL_UNSIGNED_INT, 0);
        GL30.glBindVertexArray(0);

    }

    public void cleanUP(){
        shaderProgram.cleanUp();
        GL30.glDeleteBuffers(vboID);
        GL30.glDeleteBuffers(eboID);
        GL30.glDeleteVertexArrays(vaoID);

    }



}
