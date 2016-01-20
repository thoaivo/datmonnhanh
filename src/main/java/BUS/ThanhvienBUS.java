/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ThanhvienDAO;
import DAO.ThanhvienDAO;
import DAO.ThanhvienDAO;
import DTO.ThanhvienDTO;
import DTO.ThanhvienDTO;
import DTO.ThanhvienDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Administrator
 */
@Path("/")
public class ThanhvienBUS {
    @GET
    @Path("{thanhvienID}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response getThanhvienById(@PathParam("thanhvienID") int thanhvien_id) {
        ThanhvienDAO dao = new ThanhvienDAO();
        ThanhvienDTO dto = dao.getThanhvienById(thanhvien_id);
        if(dto != null) {
            return Response.ok().entity(dto).header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .allow("OPTIONS").build();  //Nếu có kết quả thì return status code 200 và body là kết quả
        } else {
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .allow("OPTIONS").build(); //nếu ko có kết quả thì return status code 404
        }
    }
    
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response addThanhvien(ThanhvienDTO thanhvienJSON) {
        ThanhvienDAO dao = new ThanhvienDAO();
        if(dao.checkThanhvienEmail(thanhvienJSON.getEmail())) {
            int result = dao.addThanhvien(thanhvienJSON);
            if(result != -1) {
                return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();  //Nếu thêm thaàh công thì return status code 201
            } else {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build(); //nếu thêm thất bại thì return status code 404
            }
        } else {
            return Response.status(Response.Status.CONFLICT).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build(); //nếu email đã đăng ký rồi thì return status code 409, frontend xử lý vấn đề này.
        }
    }
    
    @PUT
    @Path("{thanhvienID}")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response updateThanhvien(@PathParam("thanhvienID") int thanhvien_id, ThanhvienDTO thanhvienJSON) {
        ThanhvienDAO dao = new ThanhvienDAO();
        ThanhvienDTO dto = dao.getThanhvienById(thanhvien_id);
        thanhvienJSON.setThanhvienId(thanhvien_id);
        if(dto != null) {
            if(dto.getUsername().equals(thanhvienJSON.getUsername())) {
                boolean result = dao.updateThanhvien(thanhvienJSON);
                System.out.println(result);
                if(result) {
                    return Response.ok().header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();  //Nếu sửa thành công thì return status code 200
                } else {
                    return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build(); //nếu sửa thất bại thì return status code 404
                }
            } else {
                return Response.status(Response.Status.FORBIDDEN).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build(); //nếu email không khớp với ID thì return 403
            }
        } else {
            return Response.status(Response.Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build(); //nếu không tìm thấy ID thì return 204
        }
    }
    
    @DELETE
    @Path("{thanhvienID}")
//    @Produces(MediaType.APPLICATION_JSON)
    
    public Response deleteThanhvienById(@PathParam("thanhvienID") int thanhvien_id) {
        ThanhvienDAO dao = new ThanhvienDAO();
        ThanhvienDTO dto = dao.getThanhvienById(thanhvien_id);
        if(dto != null) {
            boolean result = dao.deleteThanhvien(thanhvien_id);
            if(result) {
                return Response.ok().header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();  //Nếu xóa đuợc thì return 200
            } else {
                return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build(); //nếu không xóa đuợc thì return 404
            }
        } else {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build(); //nếu không xóa đuợc thì return 404
        }
    }
}
