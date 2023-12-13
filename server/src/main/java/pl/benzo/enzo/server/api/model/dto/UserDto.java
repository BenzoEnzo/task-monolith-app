package pl.benzo.enzo.server.api.model.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private Long user_id;
    private String name;
    private Integer score;
    private String photoId;

    public UserDto(Long user_id, String name, Integer score) {
        this.user_id = user_id;
        this.name = name;
        this.score = score;
    }
}
