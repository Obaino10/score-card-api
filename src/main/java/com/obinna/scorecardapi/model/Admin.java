package com.obinna.scorecardapi.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.obinna.scorecardapi.enums.AssignRole;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "admin")
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User{
    @Enumerated(EnumType.STRING)
    private AssignRole assignRole;

    @JsonManagedReference
    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "admin_squad", referencedColumnName = "id")
    private List<Squad> squads;
    @JsonManagedReference
    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "stack_admin",referencedColumnName = "id")
    private List<Stack> stacks;
    @JsonBackReference
    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "admin_pod",referencedColumnName = "id")
    private List<Pod> pods;


}
