package utn.dds.tpAnual.db.dto.usuario;

public class UserDTO {
    private String username;
    private String token;

    public UserDTO(){

    }

    public UserDTO(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
