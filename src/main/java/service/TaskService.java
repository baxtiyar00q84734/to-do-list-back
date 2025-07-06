package service;

import dto.TaskRequestDTO;
import dto.TaskResponseDTO;

import java.util.List;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);
    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO getTaskById(Long id);
    TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO);
    void deleteTask(Long id);
}
