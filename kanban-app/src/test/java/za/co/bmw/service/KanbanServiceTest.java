package za.co.bmw.service;

import za.co.bmw.kanban.model.Kanban;
import za.co.bmw.kanban.repository.KanbanRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.co.bmw.kanban.service.KanbanService;
import za.co.bmw.kanban.service.KanbanServiceImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KanbanServiceTest {

    KanbanService kanbanService;
    @Mock
    KanbanRepository kanbanRepository;

    @Before
    public void init() {
        kanbanService = new KanbanServiceImpl(kanbanRepository);
    }

    @Test
    public void when2KanbansInDatabase_thenGetListWithAllOfThem() {
        //given
        mockKanbanInDatabase(2);

        //when
        List<Kanban> kanbans = kanbanService.getAllKanbanBoards();

        //then
        assertEquals(2, kanbans.size());
    }

    @Test
    public void when2KanbansInDatabase_thenGetHasCreatedDate() {
        //given
        mockKanbanInDatabase(2);

        //when
        List<Kanban> kanbans = kanbanService.getAllKanbanBoards();

        //then
        assertEquals(2, kanbans.size());

        LocalDate expectedCreatedAt = LocalDate.now();
        LocalDate actualCreatedAt;
        ZoneId defaultZoneId = ZoneId.systemDefault();
        for (Kanban kan : kanbans) {
            assertNotNull(kan.getCreatedAt());
            Instant createdInstant = kan.getCreatedAt().toInstant();
            actualCreatedAt = createdInstant.atZone(defaultZoneId).toLocalDate();
            assertEquals(expectedCreatedAt, actualCreatedAt);
        }
    }

    private void mockKanbanInDatabase(int kanbanCount) {
        when(kanbanRepository.findAll())
                .thenReturn(createKanbanList(kanbanCount));
    }

    private List<Kanban> createKanbanList(int kanbanCount) {
        List<Kanban> kanbans = new ArrayList<>();
        IntStream.range(0, kanbanCount)
                .forEach(number ->{
                    Kanban kanban = new Kanban();
                    kanban.setId(Long.valueOf(number));
                    kanban.setTitle("Kanban " + number);
                    kanban.setTasks(new ArrayList<>());
                    kanban.setCreatedAt(new Date());
                    kanbans.add(kanban);
                });
        return kanbans;
    }
}
