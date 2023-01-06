package oauth.GithubOAuthLogin.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {
//OAuth 서비스 이름과 얻어온 유저 정보를 통해 UserProfile을 생성하기 위해 enum 으로 관리.
// OAuth 서비스에 따라 얻어온 유저 정보의 key값이 다르기 때문에 각각 관리해준다.

    GITHUB("github", (attributes) -> {
        return new UserProfile(
                String.valueOf(attributes.get("id")),
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("avatar_url")
        );
    });


    private final String registrationId;
    private final Function<Map<String, Object>, UserProfile> of;

    OAuthAttributes(String registrationId, Function<Map<String, Object>, UserProfile> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static UserProfile extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> registrationId.equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of.apply(attributes);
    }
}
