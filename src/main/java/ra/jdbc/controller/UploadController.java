package ra.jdbc.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "UploadController", value = "/UploadController")
@MultipartConfig(
        maxRequestSize = 1024*1024*50,
        maxFileSize = 1024*1024*50,
        fileSizeThreshold = 1024*1024*1
)
public class UploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null){
            switch (action){
                case "Upload":
                    String videoUrls = request.getParameter("video_url");
                    String[] listVideoUrls = videoUrls.split(",");
                    request.setAttribute("video_url",listVideoUrls);
                    request.getRequestDispatcher("/WEB-INF/views/show.jsp").forward(request,response);
                    break;
            }
        }
    }
}