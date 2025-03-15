package backend;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class shader_config {
    private int vaoID;
    private int vboID;
    private int eboID;

    public shader_config(){
        vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        vboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER ,vboID);
        eboID = GL15.glGenBuffers();
    }

    public void render(){

    }

   public void cleanup(){
        GL30.glDeleteVertexArrays(vaoID);
        GL15.glDeleteBuffers(vboID);
        GL15.glDeleteBuffers(eboID);
   }
}
