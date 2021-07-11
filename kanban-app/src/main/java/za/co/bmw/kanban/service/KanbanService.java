package za.co.bmw.kanban.service;

import za.co.bmw.kanban.model.Kanban;
import za.co.bmw.kanban.model.KanbanDTO;
import za.co.bmw.kanban.model.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface KanbanService {

    List<Kanban> getAllKanbanBoards();

    Optional<Kanban> getKanbanById(Long id);

    Optional<Kanban> getKanbanByTitle(String title);

    Kanban saveNewKanban(KanbanDTO kanbanDTO);

    Kanban updateKanban(Kanban oldKanban, KanbanDTO newKanbanDTO);

    void deleteKanban(Kanban kanban);

    Kanban addNewTaskToKanban(Long kanbanId, TaskDTO taskDTO);
}
