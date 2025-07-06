package service.impl;

import dto.TaskRequestDTO;
import dto.TaskResponseDTO;
import entity.Task;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import repository.TaskRepository;
import service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;


    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = modelMapper.map(taskRequestDTO, Task.class);
        Task saved = taskRepository.save(task);
        return modelMapper.map(saved, TaskResponseDTO.class);
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(t -> modelMapper.map(t, TaskResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return modelMapper.map(task, TaskResponseDTO.class);
    }


    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        modelMapper.map(dto, task);

        Task updated = taskRepository.save(task);
        return modelMapper.map(updated, TaskResponseDTO.class);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.delete(task);

    }
}
