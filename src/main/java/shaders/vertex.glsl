#version 130

in vec3 position; // входная вершина
uniform mat4 model; // модельная матрица
uniform mat4 view; // видовая матрица
uniform mat4 projection; // проекционная матрица

void main() {
    gl_Position = projection * view * model * vec4(position, 1.0);
}
