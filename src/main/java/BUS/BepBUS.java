/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.BepDAO;
import DTO.BepDTO;
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
public class BepBUS {
    @GET
    @Path("{bepID}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response getBepById(@PathParam("bepID") int bep_id) {
        BepDAO dao = new BepDAO();
        BepDTO dto = dao.getBepById(bep_id);
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
    
    public Response addBep(BepDTO bepJSON) {
        BepDAO dao = new BepDAO();
        //if(dao.checkBepEmail(bepJSON.getEmail())) {
            int result = dao.addBep(bepJSON);
            if(result != -1) {
                return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();  //Nếu thêm thaàh công thì return status code 201
            } else {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build(); //nếu thêm thất bại thì return status code 404
            }
            //để dành chỗ sau này check mỗi địa điểm chỉ có 1 bàn
//        } else {
//            return Response.status(Response.Status.CONFLICT).header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
//                .allow("OPTIONS").build(); //nếu email đã đăng ký rồi thì return status code 409, frontend xử lý vấn đề này.
//        }
    }
    
    @PUT
    @Path("{bepID}")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response updateBep(@PathParam("bepID") int bep_id, BepDTO bepJSON) {
        BepDAO dao = new BepDAO();
        BepDTO dto = dao.getBepById(bep_id);
        bepJSON.setBepId(bep_id);
        if(dto != null) {
//            if(dto.getUsername().equals(bepJSON.getUsername())) {
                boolean result = dao.updateBep(bepJSON);
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
//            } else {
//                return Response.status(Response.Status.FORBIDDEN).header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
//                .allow("OPTIONS").build(); //nếu email không khớp với ID thì return 403
//            }
        } else {
            return Response.status(Response.Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build(); //nếu không tìm thấy ID thì return 204
        }
    }
    
    @DELETE
    @Path("{bepID}")
//    @Produces(MediaType.APPLICATION_JSON)
    
    public Response deleteBepById(@PathParam("bepID") int bep_id) {
        BepDAO dao = new BepDAO();
        BepDTO dto = dao.getBepById(bep_id);
        if(dto != null) {
            boolean result = dao.deleteBep(bep_id);
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
