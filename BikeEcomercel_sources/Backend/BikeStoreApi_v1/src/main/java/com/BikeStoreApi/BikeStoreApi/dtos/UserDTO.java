package com.BikeStoreApi.BikeStoreApi.dtos;


public class UserDTO {



    private Integer id;

    private String email;

    private String password;

    private String phone;
    private String first_name;
    private String last_name;
    private Integer roleId;
    private boolean disable;

    public UserDTO() {
    }

    public UserDTO(Integer id, String email, String password, String phone, String first_name, String last_name, Integer roleId, boolean disable) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
        this.roleId = roleId;
        this.disable = disable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean getDisable() {
        return false;
    }

}
