package ProjetGenieLogiciel.isepval.models;

import ProjetGenieLogiciel.isepval.models.enums.UserType;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "user")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}