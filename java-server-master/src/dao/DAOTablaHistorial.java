package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vos.Historial;
import vos.ProductoFacturado;
import vos.ProductoMasVendido;


public class DAOTablaHistorial {
	
	private ArrayList<Object> recursos;
    private Connection conn;
    
    public DAOTablaHistorial() {
        recursos = new ArrayList<Object>();
    }
    
    public void cerrarRecursos() {
        for(Object ob : recursos){
            if(ob instanceof PreparedStatement)
                try {
                    ((PreparedStatement) ob).close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
    }

    public void setConn(Connection con){
        this.conn = con;
    }

    public ArrayList<Historial> darHistorial() throws SQLException, Exception {
        ArrayList<Historial> historial = new ArrayList<Historial>();

        String sql = "SELECT * FROM HISTORIAL";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarHistorial(rs,historial);
        }
        return historial;
    }

    public ArrayList<Historial> buscarHistorialPorId(Long idProducto, Long idUsuarioRegistrado) throws SQLException, Exception {
        ArrayList<Historial> historial = new ArrayList<Historial>();

        String sql = "SELECT * FROM HISTORIAL WHERE ID_PRODUCTO ='" + idProducto + "' AND ID_USUARIO_REGISTRADO ='"+idUsuarioRegistrado+"'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarHistorial(rs, historial);
        }

        return historial;
    }
    
    public ArrayList<Historial> buscarHistorialPorIdProducto(Long idProducto) throws SQLException, Exception {
        ArrayList<Historial> historial = new ArrayList<Historial>();

        String sql = "SELECT * FROM HISTORIAL WHERE ID_PRODUCTO ='" + idProducto +"'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarHistorial(rs, historial);
        }

        return historial;
    }
    
    public ArrayList<Historial> buscarHistorialPorIdUsuarioRegistrado(Long idUsuarioRegistrado) throws SQLException, Exception {
        ArrayList<Historial> historial = new ArrayList<Historial>();

        String sql = "SELECT * FROM HISTORIAL WHERE ID_USUARIO_REGISTRADO ='" + idUsuarioRegistrado +"'";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            insertarHistorial(rs, historial);
        }

        return historial;
    }

    public void addHistorial(Historial historial) throws SQLException, Exception {

        String sql = "INSERT INTO HISTORIAL (ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) VALUES (";
        sql += "'"+historial.getIdProducto() + "',";
        sql += "'"+historial.getIdUsuarioRegistrado() + "',";
        sql += "'SYSDATE');";
        
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();

    }

    public void deleteHistorial(Historial historial) throws SQLException, Exception {

        String sql = "DELETE FROM HISTORIAL";
        sql += " WHERE ID_PRODUCTO = " + historial.getIdProducto()+" AND ID_USUARIO_REGISTRADO= "+ historial.getIdUsuarioRegistrado();

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        prepStmt.executeQuery();
    }

    private void insertarHistorial(ResultSet rs,ArrayList<Historial> historial) throws SQLException{
    	Long idProducto=rs.getLong("ID_PRODUCTO");
        Long idUsuarioRegistrado = rs.getLong("ID_USUARIO_REGISTRADO");
        Date fecha=rs.getDate("FECHA");
        historial.add(new Historial(idProducto, idUsuarioRegistrado, fecha));
    }

    public List<ProductoFacturado> darTotProductosFacturados(Date fechaInicio,Date fechaFin) throws SQLException {
        ArrayList<ProductoFacturado> productoFacturados = new ArrayList<>();

        String sql = "SELECT ID_PRODUCTO, CATEGORIA, NOMBRE, RESTAURANTES_ID, SUM(COSTO) COSTOTOTAL, SUM(PRECIO) PRECIOTOTAL, COUNT(*) NUMPRODUCTOS " +
                "      FROM       HISTORIAL  H " +
                "            JOIN PRODUCTOS  P ON (H.ID_PRODUCTO=P.ID) " +
                "            JOIN PRODUCTOS_INDIVIDUALES PI ON (H.ID_PRODUCTO=PI.ID) " +
                "       WHERE FECHA> " + fechaInicio.toString() +" AND " + "FECHA <" + fechaFin.toString() + " " +
                "      GROUP BY ID_PRODUCTO, CATEGORIA ,NOMBRE,RESTAURANTES_ID " +
                "      ORDER BY PRECIOTOTAL DESC";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            Long idProducto=rs.getLong("ID_PRODUCTO");
            String categoria = rs.getString("CATEGORIA");
            String nombre =rs.getString("NOMBRE");
            Double costoTotal = rs.getDouble("COSTOTOTAL");
            Double precioTotal = rs.getDouble("PRECIOTOTAL");
            Long restauranteId = rs.getLong("RESTAURANTES_ID");

            productoFacturados.add(new ProductoFacturado(idProducto,nombre,costoTotal,precioTotal,restauranteId,categoria));

        }
        return productoFacturados;

    }

    public List<ProductoFacturado> darProductosFactoradosRestaurante(Long usuario_id,Date fechaInicio,Date fechaFin) throws SQLException{
        ArrayList<ProductoFacturado> productoFacturados = new ArrayList<>();

        String sql = "SELECT ID_PRODUCTO, CATEGORIA, NOMBRE, RESTAURANTES_ID, SUM(COSTO) COSTOTOTAL, SUM(PRECIO) PRECIOTOTAL, COUNT(*) NUMPRODUCTOS " +
                "      FROM       HISTORIAL  H " +
                "            JOIN PRODUCTOS  P ON (H.ID_PRODUCTO=P.ID) " +
                "            JOIN PRODUCTOS_INDIVIDUALES PI ON (H.ID_PRODUCTO=PI.ID) " +
                "       WHERE RESTAURANTES_ID = " + usuario_id+" AND FECHA> " + fechaInicio.toString() +" AND " + "FECHA <" + fechaFin.toString() + " " +
                "      GROUP BY ID_PRODUCTO, CATEGORIA ,NOMBRE,RESTAURANTES_ID " +
                "      ORDER BY PRECIOTOTAL DESC";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            Long idProducto=rs.getLong("ID_PRODUCTO");
            String categoria = rs.getString("CATEGORIA");
            String nombre =rs.getString("NOMBRE");
            Double costoTotal = rs.getDouble("COSTOTOTAL");
            Double precioTotal = rs.getDouble("PRECIOTOTAL");
            Long restauranteId = rs.getLong("RESTAURANTES_ID");

            productoFacturados.add(new ProductoFacturado(idProducto,nombre,costoTotal,precioTotal,restauranteId,categoria));

        }
        return productoFacturados;
    }

    public List<ProductoMasVendido> darProductosMasVendidos() throws SQLException {
        ArrayList<ProductoMasVendido> productos= new ArrayList<>();

        String sql = "SELECT * FROM(" +
                "SELECT *\n" +
                " FROM  (SELECT ID_PRODUCTO,ZONA, COUNT(ZONA) MASVENDPORZONA" +
                "    FROM HISTORIAL H" +
                "      JOIN PRODUCTOS P ON (H.ID_PRODUCTO=P.ID)" +
                "      JOIN RESTAURANTES R ON (R.ID = P.RESTAURANTES_ID)" +
                "    GROUP BY (ID_PRODUCTO, ZONA))" +
                "    JOIN PRODUCTOS ON (ID_PRODUCTO=PRODUCTOS.ID)" +
                "ORDER BY MASVENDPORZONA DESC)" +
                "WHERE ROWNUM <=3";

        PreparedStatement prepStmt = conn.prepareStatement(sql);
        recursos.add(prepStmt);
        ResultSet rs = prepStmt.executeQuery();

        while (rs.next()) {
            Long idProducto=rs.getLong("ID_PRODUCTO");
            String nombre =rs.getString("NOMBRE");
            String descrEsp = rs.getString("DESCRESP");
            String descrIng = rs.getString("DESCRING");
            Double tPrep = rs.getDouble("TPREP");
            Double costo = rs.getDouble("COSTO");
            Double precio= rs.getDouble("PRECIO");
            Long cantVendido = rs.getLong("MASVENDPORZONA");
            String zona = rs.getString("ZONA");
            Long restId = rs.getLong("RESTAURANTES_ID");

            productos.add(new ProductoMasVendido(idProducto,nombre,descrEsp,descrIng,tPrep,costo,precio,restId,zona,cantVendido));

        }
        return productos;
    }

}
