package backend;

import org.lwjgl.opengl.GL20;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class shaderProgram {
    private final int programID;

    public shaderProgram(String vertexPath, String fragmentPath) throws Exception{
        int vertexShader = createShader(vertexPath, GL20.GL_VERTEX_SHADER);
        int fragmentShader = createShader(fragmentPath, GL20.GL_FRAGMENT_SHADER);


        programID = GL20.glCreateProgram();

        GL20.glAttachShader(programID, vertexShader);
        GL20.glAttachShader(programID, fragmentShader);

        GL20.glLinkProgram(programID);
        int success = GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS);
        if (success == GL20.GL_FALSE) {
            String errorMessage = GL20.glGetProgramInfoLog(programID);
            System.err.println("ERROR LINKING SHADER PROGRAM: " + errorMessage);
            throw new Exception("Program linking failed");
        }


    }

    private int createShader(String filePath, int type) throws Exception {
        String source = new String(Files.readAllBytes(Paths.get(filePath)));
        int shaderId = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderId, source);
        GL20.glCompileShader(shaderId);

        int success = GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS);
        if (success == GL20.GL_FALSE) {
            String errorMessage = GL20.glGetShaderInfoLog(shaderId);
            System.err.println("ERROR COMPILING SHADER: " + errorMessage);
            throw new Exception("Shader compilation failed");
        }
        return shaderId;
    }


    public void use(){
        GL20.glUseProgram(programID);
    }

    public void cleanup(){
        GL20.glDeleteProgram(programID);
    }

    public int getProgramID(){
        return programID;
    }
}
