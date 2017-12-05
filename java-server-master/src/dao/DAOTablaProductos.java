package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.Producto;


public class DAOTablaProductos {

	private ArrayList<Object> recursos;
	private Connection conn;

	public DAOTablaProductos() {
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

	public ArrayList<Producto> darProductos() throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			insertarProducto(rs,productos);
		}
		return productos;
	}

	public Producto buscarProductosPorId(Long id) throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS WHERE ID ='" + id + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			insertarProducto(rs,productos);
		}

		return productos.get(0);
	}

	public void updateProducto(Producto producto) throws SQLException, Exception {

		String sql = "UPDATE PRODUCTOS SET ";
		sql += "NOMBRE="+"'"+producto.getNombre()+"',";
		sql += "DESCRESP="+"'"+producto.getDescrEsp()+"',";
		sql += "DESCRING="+"'"+producto.getDescrIng()+"',";
		sql += "TPREP="+"'"+producto.gettPrep()+"',";
		sql += "COSTO="+"'"+producto.getCosto()+"',";
		sql += "PRECIO="+"'"+producto.getPrecio()+"',";
		sql += "RESTAURANTES_ID="+"'"+producto.getRestauranteId()+"' ";
		sql += "WHERE ID="+"'"+producto.getId()+"';";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void addProducto(Producto producto) throws SQLException, Exception {

		String sql = "INSERT INTO PRODUCTOS (ID, NOMBRE, DESCRESP, DESCRING, TPREP, COSTO, PRECIO, RESTAURANTE_ID) VALUES (";
		sql += "'"+producto.getId() + "',";
		sql += "'"+producto.getNombre()+"',";
		sql += "'"+producto.getDescrEsp()+"',";
		sql += "'"+producto.getDescrIng()+"',";
		sql += "'"+producto.gettPrep()+"',";
		sql += "'"+producto.getCosto()+"',";
		sql += "'"+producto.getPrecio()+"',";
		sql += "'"+producto.getRestauranteId()+"');";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	public void deleteProducto(Producto producto) throws SQLException, Exception {

		String sql = "DELETE FROM PRODUCTOS";
		sql += " WHERE ID = " + producto.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	private void insertarProducto(ResultSet rs,ArrayList<Producto> productos) throws SQLException{
		Long id=rs.getLong("ID");
		String nombre = rs.getString("NOMBRE");
		String descrEsp = rs.getString("DESCRESP");
		String descrIng = rs.getString("DESCRING");
		Double tPrep = rs.getDouble("TPREP");
		Double costo = rs.getDouble("COSTO");
		Double precio = rs.getDouble("PRECIO");
		Long restauranteId = rs.getLong("RESTAURANTES_ID");
		productos.add(new Producto(id,nombre,descrEsp,descrIng,tPrep, costo, precio, restauranteId));
	}

	public List<Producto> buscarProductosRestaurante(Long idRestaurante) throws SQLException {

		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS WHERE RESTAURANTES_ID ='" + idRestaurante + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			insertarProducto(rs,productos);
		}

		return productos;
	}

	public List<Producto> buscarProductosRangoPrecios(Double menorRango, Double mayorRango) throws SQLException {

		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS WHERE PRECIO >'" + menorRango + "' AND PRECIO <'"+ mayorRango +"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			insertarProducto(rs,productos);
		}

		return productos;
	}

	public List<Producto> buscarProductosCategoria(String categoria ) throws SQLException {

		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS NATURAL JOIN PRODUCTOS_INDIVIDUALES WHERE CATEGORIA LIKE '"+categoria+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			insertarProducto(rs,productos);
		}

		return productos;
	}

	public List<Producto> buscarProductosGenerales(Long idRestaurante, String categoria, Double rangoMinimo,
			Double rangoMaximo, String[] criterios) throws SQLException {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		String sql="";
		if(categoria !=""){
			if(idRestaurante!=0){
				sql = "SELECT PRODUCTOS.* FROM (PRODUCTOS JOIN PRODUCTOS_INDIVIDUALES ON(PRODUCTOS.ID=PRODUCTOS_INDIVIDUALES.ID)) WHERE CATEGORIA LIKE '"+categoria+"' AND RESTAURANTES_ID = "+idRestaurante+" AND PRECIO >= "+rangoMinimo+" AND PRECIO <= "+rangoMaximo;
			}
			else{
				sql = "SELECT PRODUCTOS.* FROM (PRODUCTOS JOIN PRODUCTOS_INDIVIDUALES ON(PRODUCTOS.ID=PRODUCTOS_INDIVIDUALES.ID)) WHERE CATEGORIA LIKE '"+categoria+"' AND PRECIO >= "+rangoMinimo+" AND PRECIO <= "+rangoMaximo;
			}
		}
		else if(idRestaurante !=0){
			sql = "SELECT PRODUCTOS.* FROM (PRODUCTOS JOIN PRODUCTOS_INDIVIDUALES ON(PRODUCTOS.ID=PRODUCTOS_INDIVIDUALES.ID)) WHERE RESTAURANTES_ID = "+idRestaurante+" AND PRECIO >= "+rangoMinimo+" AND PRECIO <= "+rangoMaximo;
		}
		else{
			sql = "SELECT PRODUCTOS.* FROM (PRODUCTOS JOIN PRODUCTOS_INDIVIDUALES ON(PRODUCTOS.ID=PRODUCTOS_INDIVIDUALES.ID)) WHERE PRECIO >= "+rangoMinimo+" AND PRECIO <= "+rangoMaximo;
		}
		if(criterios.length!=0){
			sql+=" ORDER BY";
			for(int i=0; i<criterios.length; i++){
				if(i==criterios.length-1){
					if(criterios[i].equals("restaurante")){
						sql+=" RESTAURANTES_ID";
					}
					else if(criterios[i].equals("categoria")){
						sql+=" CATEGORIA";
					}
					else if(criterios[i].equals("precio")){
						sql+=" PRECIO";
					}
					else{
						sql+=" NOMBRE";
					}
				}
				else{
					if(criterios[i].equals("restaurante")){
						sql+=" RESTAURANTES_ID,";
					}
					else if(criterios[i].equals("categoria")){
						sql+=" CATEGORIA,";
					}
					else if(criterios[i].equals("precio")){
						sql+=" PRECIO,";
					}
					else{
						sql+=" NOMBRE,";
					}
				}				
			}
		}
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			insertarProducto(rs,productos);
		}

		return productos;
	}
}
