/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DiadiemDAO;
import DTO.DiadiemDTO;
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
public class DiadiemBUS {
    @GET
    @Path("{diadiemID}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response getDiadiemById(@PathParam("diadiemID") int diadiem_id) {
        DiadiemDAO diadiemDAO = new DiadiemDAO();
        DiadiemDTO diadiemDTO = diadiemDAO.getDiadiemById(diadiem_id);
        if(diadiemDTO != null) {
            return Response.ok().entity(diadiemDTO).header("Access-Control-Allow-Origin", "*")
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
    
    public Response addDiadiem(DiadiemDTO diadiemJSON) {
        DiadiemDAO diadiemDAO = new DiadiemDAO();
        if(diadiemDAO.checkDiadiemOwnerEmail(diadiemJSON.getOwner_email())) {
            int result = diadiemDAO.addDiadiem(diadiemJSON);
            if(result != -1) {
                return Response.ok().header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();  //Nếu thêm thaàh công thì return status code 200
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
    @Path("{diadiemID}")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response updateDiadiem(@PathParam("diadiemID") int diadiem_id, DiadiemDTO diadiemJSON) {
        DiadiemDAO diadiemDAO = new DiadiemDAO();
        DiadiemDTO diadiemDTO = diadiemDAO.getDiadiemById(diadiem_id);
        diadiemJSON.setDiadiemId(diadiem_id);
        if(diadiemDTO != null) {
            if(diadiemDTO.getOwner_email().equals(diadiemJSON.getOwner_email())) {
                boolean result = diadiemDAO.updateDiadiem(diadiemJSON);
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
    @Path("{diadiemID}")
//    @Produces(MediaType.APPLICATION_JSON)
    
    public Response deleteDiadiemById(@PathParam("diadiemID") int diadiem_id) {
        DiadiemDAO diadiemDAO = new DiadiemDAO();
        DiadiemDTO diadiemDTO = diadiemDAO.getDiadiemById(diadiem_id);
        if(diadiemDTO != null) {
            boolean result = diadiemDAO.deleteDiadiem(diadiem_id);
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
