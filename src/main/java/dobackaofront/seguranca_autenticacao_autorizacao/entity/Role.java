package dobackaofront.seguranca_autenticacao_autorizacao.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Mapeamento inverso opcional:
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    // Construtores
    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    // Getters e Setters ...
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }
}
