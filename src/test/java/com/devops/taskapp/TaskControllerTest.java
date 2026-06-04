package com.devops.taskapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class TaskControllerTest {

    private TaskController controller;

    @BeforeEach
    public void setup() {
        controller = new TaskController();
    }

    @Test
    public void deveRetornarListaVaziaNoInicio() {
        List<Task> tasks = controller.getAllTasks();
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void deveCriarUmaTarefa() {
        Map<String, String> body = Map.of("title", "Estudar DevOps");
        Task task = controller.createTask(body);

        assertEquals("Estudar DevOps", task.getTitle());
        assertFalse(task.isCompleted());
        assertNotNull(task.getId());
    }

    @Test
    public void deveDeletarUmaTarefa() {
        Map<String, String> body = Map.of("title", "Tarefa para deletar");
        Task task = controller.createTask(body);

        String resultado = controller.deleteTask(task.getId());

        assertEquals("Deleted", resultado);
        assertTrue(controller.getAllTasks().isEmpty());
    }
}