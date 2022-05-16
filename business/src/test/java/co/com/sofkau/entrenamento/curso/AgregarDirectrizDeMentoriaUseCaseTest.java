package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizDeMentoria;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaDeMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AgregarDirectrizDeMentoriaUseCaseTest {

    @InjectMocks
    private AgregarDirectrizDeMentoriaUseCase useCase;
    @Mock
    private DomainEventRepository repository;

    @Test
    void agregrarUnaDirectrizHappyPass() {
        //Arrange
        CursoId courseId = CursoId.of("dddd");
        MentoriaId mentoriaId = MentoriaId.of("1");
        Directiz directiz = new Directiz("Los aprendices deben aprender DDD");
        var command = new AgregarDirectrizDeMentoria(courseId, mentoriaId, directiz);

        when(repository.getEventsBy("dddd")).thenReturn(history());
        useCase.addRepository(repository);
        // Act

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getCursoId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        // Assert
        var event = (DirectrizAgregadaDeMentoria) events.get(0);
        Assertions.assertEquals("Los aprendices deben aprender DDD", event.getDirectriz().value());

    }

    private List<DomainEvent> history() {
        Nombre nombre = new Nombre("DDD");
        Descripcion descripcion = new Descripcion("...");
        var event = new CursoCreado(
                nombre,
                descripcion
        );
        MentoriaId mentoriaId = new MentoriaId("1");
        Nombre nombreMentoria = new Nombre("Raúl");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        var evento = new MentoriaCreada(
                mentoriaId,
                nombreMentoria,
                fecha
        );
        event.setAggregateRootId("xxxxx");
        return List.of(event,evento);
    }
}