package com.kshrd.derphsar_api.rest.user.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.derphsar_api.repository.dto.RoleDto;
import com.kshrd.derphsar_api.rest.order.response.OrderUserResponse;
import com.kshrd.derphsar_api.rest.role.response.RoleResponse;

import java.util.List;

public class UserResponseModel {

    //@JsonIgnore
    private int id;
    private String userId;
    private String name;
    private String gender;
    private int age;
    private String phone;
    private String email;
    private String password;
    private boolean status;
    private String profile;
    //@JsonIgnore
    private List<RoleResponse> role;
    private List<OrderUserResponse> order;

    public  UserResponseModel(){}


    public UserResponseModel(int id, String userId, String name, String gender, int age, String phone, String email, String password, boolean status, String profile, List<RoleResponse> role, List<OrderUserResponse> order) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.status = status;
        this.profile = profile;
        this.role = role;
        this.order = order;
    }


    @Override
    public String toString() {
        return "UserResponseModel{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", profile='" + profile + '\'' +
                ", role=" + role +
                ", order=" + order +
                '}';
    }

    public List<OrderUserResponse> getOrder() {
        return order;
    }

    public void setOrder(List<OrderUserResponse> order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<RoleResponse> getRole() {
        return role;
    }

    public void setRole(List<RoleResponse> role) {
        this.role = role;
    }
}
