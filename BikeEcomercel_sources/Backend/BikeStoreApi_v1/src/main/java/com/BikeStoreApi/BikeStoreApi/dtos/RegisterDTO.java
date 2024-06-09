package com.BikeStoreApi.BikeStoreApi.dtos;

public class RegisterDTO {
    private String registerEmail;
    private String registerPassword;
    private String confirmPassword;
    private String registerFirstname;
    private String registerLastname;
    private String registerPhone;

    public RegisterDTO(String registerEmail, String registerPassword, String confirmPassword, String registerFirstname, String registerLastname, String registerPhone) {
        this.registerEmail = registerEmail;
        this.registerPassword = registerPassword;
        this.confirmPassword = confirmPassword;
        this.registerFirstname = registerFirstname;
        this.registerLastname = registerLastname;
        this.registerPhone = registerPhone;
    }

    public RegisterDTO() {
    }

    public String getRegisterEmail() {
        return registerEmail;
    }

    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRegisterFirstname() {
        return registerFirstname;
    }

    public void setRegisterFirstname(String registerFirstname) {
        this.registerFirstname = registerFirstname;
    }

    public String getRegisterLastname() {
        return registerLastname;
    }

    public void setRegisterLastname(String registerLastname) {
        this.registerLastname = registerLastname;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }
}
