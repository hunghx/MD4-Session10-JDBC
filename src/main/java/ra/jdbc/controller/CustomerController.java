package ra.jdbc.controller;

import ra.jdbc.model.Customer;
import ra.jdbc.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/CustomerController")
public class CustomerController extends HttpServlet {
    protected CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService= new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action!=null){
            switch (action){
                case "GETALL":
                    showListCustomer(request,response);
                    break;
                case "CREATE":
                    request.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(request,response);
                    break;
                case "DELETE":
                    int idDel = Integer.parseInt(request.getParameter("id"));
                    customerService.delete(idDel);
                    response.sendRedirect("/");
                    break;
                case "EDIT":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    Customer c = customerService.findById(idEdit);
                    request.setAttribute("customer",c);
                    request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request,response);
                    break;

            }
        }
    }
    protected void showListCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lấy ra dnah sách
        // Lấy ra số trang hiện tại
        int page = Integer.parseInt(request.getParameter("page"));

        // Lấy ra số lượng sp trên 11 trang
        int size = Integer.parseInt(request.getParameter("size"));
            // giả sử có 6 người
        // tính tổng số trang
        int count = 6;
        int totalPage = ((int)(6/size))+1;
        List<Customer> customers = customerService.findByPageAndSize(page,size);

        request.setAttribute("customers",customers);
        request.setAttribute("page",page);
        request.setAttribute("size",size);
        request.setAttribute("totalPage",totalPage);
        request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action= request.getParameter("action");
        if(action!=null){
            switch (action){
                case "ADD":
                    String name = request.getParameter("name");
                    int age = Integer.parseInt(request.getParameter("age"));
                    Customer c = new Customer(0,name,age);
                    customerService.save(c);
                    break;
                case "UPDATE":
                    int idUp = Integer.parseInt(request.getParameter("id"));
                String nameUp = request.getParameter("name");
                int ageUP = Integer.parseInt(request.getParameter("age"));
                Customer cus = new Customer(idUp,nameUp,ageUP);
                customerService.save(cus);
                break;


            }
            response.sendRedirect("/");
        }
    }
}