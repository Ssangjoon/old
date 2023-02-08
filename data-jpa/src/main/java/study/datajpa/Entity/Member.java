package study.datajpa.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
@NamedQuery(
        name="Member.findByUsername",
        query="select m from Member m where m.userName = :username")
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String userName;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String userName) {
        this(userName,0);
    }

    public Member(String userName, int age){
        this(userName, age, null);
    }

    public Member(String userName, int age, Team team) {
        this.userName = userName;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    public void changeTeam(Team team){
        this.team =team;
        team.getMembers().add(this);
    }
}
