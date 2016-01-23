/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.MenuchitietDAO;
import DTO.MenuchitietDTO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author MrD
 */
public class MenuchitietBUS {
    
    @GET
    @Path("{menuchitietID}")
//    @Produces(MediaType.APPLICATION_JSON)    
    public Response getMenuchitietById(@PathParam("menuchitietID") int menu_chitiet_id) {
        MenuchitietDAO dao = new MenuchitietDAO();
        MenuchitietDTO dto =  dao.getMenuchitietById(menu_chitiet_id);
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
    
    @GET
    @Path("{menuID}")
//    @Produces(MediaType.APPLICATION_JSON)
    public Response getMenuchitietByMenuId(@PathParam("menuID") int menu_id) {
        MenuchitietDAO dao = new MenuchitietDAO();
        List<MenuchitietDTO> listdto =  dao.getListMenuchitietByMenuId(menu_id);
        if(listdto != null) {
            return Response.ok().entity(listdto).header("Access-Control-Allow-Origin", "*")
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
    public Response addMenuchitiet(MenuchitietDTO menuchitietJSON) {
        MenuchitietDAO dao = new MenuchitietDAO();
        int result = dao.addMenuchitiet(menuchitietJSON);
        if(result != -1) {
            return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .allow("OPTIONS").build();  //Nếu thêm thaàh công thì return status code 200
        } else {
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .allow("OPTIONS").build(); //nếu thêm thất bại thì return status code 404
        }
    }
    
    @PUT
    @Path("{menuchitietID}")
    @Consumes(MediaType.APPLICATION_JSON)    
    public Response updateMenuchitiet(@PathParam("menuchitietID") int menu_chitiet_id, MenuchitietDTO menuchitietJSON) {
        MenuchitietDAO dao = new MenuchitietDAO();
        MenuchitietDTO dto = dao.getMenuchitietById(menu_chitiet_id);
        menuchitietJSON.setMenuchitietId(menu_chitiet_id);
        if(dto != null) {
            boolean result = dao.updateMenuchitiet(menuchitietJSON);
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
    }
    
    @DELETE
    @Path("{menuchitietID}")
//    @Produces(MediaType.APPLICATION_JSON)    
    public Response deleteMenuchitietById(@PathParam("menuchitietID") int menu_chitiet_id) {
        MenuchitietDAO dao = new MenuchitietDAO();
        MenuchitietDTO dto = dao.getMenuchitietById(menu_chitiet_id);
        if(dto != null) {
            boolean result = dao.deleteMenuchitiet(menu_chitiet_id);
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
