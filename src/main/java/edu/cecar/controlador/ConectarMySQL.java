package edu.cecar.controlador;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




/**
 * Esta clase permite conectar a una base de datos Mysql y ademas ejecutar sentencias DML
 */


final public class ConectarMySQL {

	//** Declaracion de variables
	private static Connection conexion;

	protected static Statement sentencia;


	/** 
	 *Constructor general que se conecta a la base de datos dependiendo de los parametros
	 *
	 *@param servidorNombre Nombre del servidor o direccion IP
	 *@param nombreBD  nombre de la base de datos
	 *@param usuario Usuario autorizado
	 *@param  password
	 *
	 */

	public static void getConectarMySQL(String servidorNombre,String nombreBD,String usuario,String password) throws Exception{

		if (conexion == null) {

			//** Se carga el driver para conectarse a la base de datos

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();

			}catch  (Exception e) {

				System.out.println("Error"+e);
			}


			// Se conecta a la base de datos
			// Se crea un URL hacia la maquina y la base de datos
			String url= "jdbc:mysql://" + servidorNombre + ":3306/" + nombreBD+"?serverTimezone=UTC"; 

			//se crea la conexion a la base de datos
			conexion=DriverManager.getConnection(url,usuario,password);

			//conexion.setAutoCommit(false);

		}

	}

	//Otros metodos


	/** 
	 * Devuelve el objeto que permite la conexion a la base de datos
	 *
	 * @return Connection Conexion a la base de datos
	 *
	 */

	public static Connection getConexion() {

		return conexion;
	}


	


	/** 
	 * Permite hacer los cambios permanentes en la BD
	 *
	 */

	public void commit() throws Exception {

		conexion.commit();
	}



	/** 
	 * Permite deshacer  cambios en la BD antes del ultimo commit
	 *
	 */

	public void rollback() {

		try {

			conexion.rollback();

		} catch (Exception e) {

			System.out.println("Error "+ e);
		}
	}

}


