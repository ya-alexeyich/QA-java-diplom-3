package user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredentials {
    private String email;
    private String password;

    public static UserCredentials from(UserModel userModel) {
        return new UserCredentials(userModel.getEmail(), userModel.getPassword());
    }
}
