package shaders;

import org.lwjgl.opengl.GL20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShaderProgram {
    private final int programId;

    public ShaderProgram(String vertexPath, String fragmentPath) throws Exception {
        int vertexShader = createShader(vertexPath, GL20.GL_VERTEX_SHADER);
        int fragmentShader = createShader(fragmentPath, GL20.GL_FRAGMENT_SHADER);

        programId = GL20.glCreateProgram();
        GL20.glAttachShader(programId, vertexShader);
        GL20.glAttachShader(programId, fragmentShader);
        GL20.glLinkProgram(programId);

        GL20.glDeleteShader(vertexShader);
        GL20.glDeleteShader(fragmentShader);

    }

    private int  createShader(String filePath, int type) throws IOException {
        String source = new String(Files.readAllBytes(Paths.get(filePath)));
        int shaderId = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderId, source);
        GL20.glCompileShader(shaderId);

        return shaderId;
    }

    public void use(){
        GL20.glUseProgram(programId);
    }

    public void cleanUp(){
        GL20.glDeleteProgram(programId);
    }
}
