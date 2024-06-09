package ProjetGenieLogiciel.isepval.models;

import ProjetGenieLogiciel.isepval.models.enums.Mark;
import ProjetGenieLogiciel.isepval.models.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "skill_eval")
public class SkillEvaluated implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    @JsonIgnore
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private User student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Mark mark = Mark.NOTEVALUATED;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
