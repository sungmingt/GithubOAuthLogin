package oauth.GithubOAuthLogin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import oauth.GithubOAuthLogin.domain.Role;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String oauthId;
    private String name;
    private String email;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(String oauthId, String name, String email, String imageUrl, Role role) {
        this.oauthId = oauthId;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public Member(String oauthId, String name, String email, String imageUrl) {
        this.oauthId = oauthId;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    //업데이트 메서드
    public Member update(String name, String email, String imageUrl) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

