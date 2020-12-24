package com.kshrd.derphsar_api.repository.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDto implements UserDetails {
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
    private RoleDto role;
    private OrderDto order;

    public UserDto(){}

    public UserDto(int id, String userId, String name, String gender, int age, String phone, String email, String password, boolean status, String profile, RoleDto role, OrderDto order) {
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
        return "UserDto{" +
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


    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
        list.add(()->role.getName());
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }
}
