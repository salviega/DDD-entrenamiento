package co.com.sofkau.entrenamiento.curso.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.entrenamiento.curso.values.CursoId;
import co.com.sofkau.entrenamiento.curso.values.Directiz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;

public class AgregarDirectrizDeMentoria extends Command {
    private final CursoId cursoId;
    private final MentoriaId mentoriaId;
    private final Directiz directiz;


    public AgregarDirectrizDeMentoria(CursoId cursoId, MentoriaId mentoriaId, Directiz directiz) {
        this.cursoId = cursoId;
        this.mentoriaId = mentoriaId;
        this.directiz = directiz;

    }

    public Directiz getDirectiz() {
        return directiz;
    }

    public MentoriaId getMentoriaId() {
        return mentoriaId;
    }

    public CursoId getCursoId() {
        return cursoId;
    }
}
