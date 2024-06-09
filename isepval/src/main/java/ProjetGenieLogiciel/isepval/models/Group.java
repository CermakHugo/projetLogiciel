package ProjetGenieLogiciel.isepval.models;

import ProjetGenieLogiciel.isepval.models.enums.UserType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "groups")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> students = new ArrayList<>();

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
