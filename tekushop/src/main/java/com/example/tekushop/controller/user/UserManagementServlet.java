package com.example.tekushop.controller.user;

import com.example.tekushop.model.User;
import com.example.tekushop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserManagementServlet", urlPatterns = {"/user-management"})
public class UserManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        UserService userService = new UserService();
        String id;
        if(user == null){
            System.out.println("User is null");
        }
        if (user == null || user.getRole().equals("customer") || user.getRole().equals("editor")) {
            resp.sendRedirect("/index");
        }else {
            String action = "";
            action = req.getParameter("action");
            if (action == null) {
                action = "";
            }
            List<User> userList;

            switch (action) {
                case "add":
                    req.getRequestDispatcher("user-add.jsp").forward(req, resp);
                    break;
                case "edit":
                    id = req.getParameter("id");
                    User userDit = userService.findUser("user_id",id,"int",0,req);

                    if(userDit == null){
                        req.setAttribute("error", "User not found");
                        req.setAttribute("errorType", 1);
                        req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
                        break;
                    }else {
                        req.setAttribute("tempUser", userDit);
                        req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
                        break;
                    }

                case "delete":

                    id = req.getParameter("id");
                    deleteUser(id,req);
                    userList = listUser();
                    req.setAttribute("userList", userList);
                    req.getRequestDispatcher("user-mng.jsp").forward(req, resp);
                    break;
                default:
                    userList = listUser();
                    req.setAttribute("userList", userList);
                    req.getRequestDispatcher("user-mng.jsp").forward(req, resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        String actionPost = req.getParameter("action");
        if(actionPost == null){
            actionPost = "";
        }
        switch (actionPost){
            case "add":
                addUser(req,resp);
                break;
            case "edit":
                editUser(req,resp);
                break;
            case "delete":
                break;
            default:
                break;
        }
    }

    public List<User> listUser() {
        UserService userService = new UserService();
        return userService.listUser();
    }
    public void deleteUser(String id,HttpServletRequest request) {
        UserService userService = new UserService();
        boolean isDelete = userService.deleteUser(id,request);
        if(isDelete){
            request.setAttribute("success", "User (id = "+id+") has been deleted");
        }
    }
    public void addUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        UserService userService = new UserService();

        boolean isAdd = userService.addUser(username,password,email,role,request);
        List<User> userList = listUser();
        if(isAdd){
            request.setAttribute("success", "User has been added");
            request.setAttribute("userList", userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-mng.jsp");
            dispatcher.forward(request, response);

        }else {
            User tempUser = new User();
            tempUser.setUsername(username);
            tempUser.setPassword(password);
            tempUser.setEmail(email);
            tempUser.setRole(role);
            request.setAttribute("tempUser", tempUser);
            request.getRequestDispatcher("user-add.jsp").forward(request, response);
        }

    }
    public void editUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(password == null){
            password = "";
        }
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        UserService userService = new UserService();

        boolean isEdit = userService.editUser(id,username,password,email,role,request);
        List<User> userList = listUser();
        if(isEdit){
            request.setAttribute("success", "User has been edited");
            request.setAttribute("userList", userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-mng.jsp");
            dispatcher.forward(request, response);

        }else {
            User tempUser = new User();
            tempUser.setId(Integer.parseInt(id));
            tempUser.setUsername(username);
            tempUser.setPassword(password);
            tempUser.setEmail(email);
            tempUser.setRole(role);
            request.setAttribute("tempUser", tempUser);
            request.getRequestDispatcher("user-edit.jsp").forward(request, response);
        }

    }
}
