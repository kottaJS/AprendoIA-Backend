package cl.duoc.aprendoia.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "El email o alias es obligatorio")
    private String emailOrAlias;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String emailOrAlias, String password) {
        this.emailOrAlias = emailOrAlias;
        this.password = password;
    }

    public String getEmailOrAlias() {
        return emailOrAlias;
    }

    public void setEmailOrAlias(String emailOrAlias) {
        this.emailOrAlias = emailOrAlias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
