package com.example.tekushop.service;

import com.example.tekushop.common.PasswordUtil;
import com.example.tekushop.common.Validation;
import com.example.tekushop.model.User;
import com.example.tekushop.repository.UserRepo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserService {
    private UserRepo userRepository = new UserRepo();

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);


        if (user != null) {
            String hashedPassword = PasswordUtil.hashPassword(password);

            return hashedPassword.equals(user.getPassword());
        }
        return false;
    }
    public User getUserByUsername(String username,HttpServletRequest request) {
        if (!Validation.isUsername(username)) {
            request.setAttribute("error", "Username is not valid");
            return null;
            }
        if(userRepository.findByUsername(username) == null){
            request.setAttribute("error", "User not found");
            return null;
        }
        return userRepository.findByUsername(username);

    }
    public boolean addUser(String username, String password, String email, String role, HttpServletRequest request) {
        UserRepo userRepo = new UserRepo();

        if(!Validation.isUsername(username)){
            request.setAttribute("error", "Username is not valid");
            return false;
        }
        if(userRepo.findByUsername(username) != null){
            request.setAttribute("error", "Username is already taken");
            return false;
        }
        if(!Validation.isEmail(email)){
            request.setAttribute("error", "Email is not valid");
            return false;
        }
        if(userRepo.findByEmail(email) != null){
            request.setAttribute("error", "Email is already taken");
            return false;
        }
        if(!Validation.isPassword(password)){
            request.setAttribute("error", "Password is not valid");
            return false;
        }
        String hashedPassword = PasswordUtil.hashPassword(password);
        User user = new User(username, hashedPassword, email, role);
        userRepo.addUser(user);
        return true;
    }
    public List<User> listUser() {
        return userRepository.listUser();
    }
    public boolean deleteUser(String id,HttpServletRequest request) {
        boolean result = true;
        if(!Validation.isPrimaryId(id)){
            request.setAttribute("error", "ID is not valid");
            result = false;
        }
        int idInt = Integer.parseInt(id);
        if(userRepository.findById(idInt) == null){
            request.setAttribute("error", "User not found");
            result = false;
        }
        if(result){
            userRepository.deleteUser(idInt);
        }
        return result;
    }
    public boolean editUser(String id, String username, String password, String email, String role, HttpServletRequest request) {
        boolean result = true;
        if(!Validation.isPrimaryId(id)){
            request.setAttribute("error", "ID is not valid");
            result = false;
        }
        int idInt = Integer.parseInt(id);
        if(userRepository.findById(idInt) == null){
            request.setAttribute("error", "User not found");
            result = false;
        }
        if(!Validation.isUsername(username)){
            request.setAttribute("error", "Username is not valid");
            result = false;
        }
        if(userRepository.findUser("username", username, "string", idInt) != null){
            request.setAttribute("error", "Username is already taken");
            result = false;
        }
        if(!Validation.isEmail(email)){
            request.setAttribute("error", "Email is not valid");
            result = false;
        }
        if(userRepository.findUser("email", email, "string", idInt) != null){
            request.setAttribute("error", "Email is already taken");
            result = false;
        }
        String hashedPassword;
        if(password.equals("") || password == null){
            password = userRepository.findById(idInt).getPassword();
            hashedPassword = password;
        }else {
            if(!Validation.isPassword(password)){
                request.setAttribute("error", "Password is not valid");
                result = false;
            }
             hashedPassword = PasswordUtil.hashPassword(password);
        }

        if(result){

            User user = new User(idInt, username, hashedPassword, email, role);
            userRepository.editUser(user);
        }
        return result;
    }
    public User findUser(String collum,String value,String collumType,int excludeId,HttpServletRequest request) {


        if(collumType.equals("int")){ // only id can be int
            if(!Validation.isPrimaryId(value)){
                request.setAttribute("error", "ID is not valid");
                return null;
            }
            if (userRepository.findById(Integer.parseInt(value)) == null) {
                request.setAttribute("error", "User not found");
                return null;
            }
            return userRepository.findUser(collum, value, collumType, excludeId);
        }else {
            return null;
        }
    }

}
