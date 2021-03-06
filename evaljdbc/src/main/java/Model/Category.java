package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
  * Classe de gestion des cat�gories
  * 
  * @author Xavier Tagliarino <xavier.tagliarino@gmail.com>
  * @name Category
  * @access public
  *
  */


public class Category {
		
	/**
	  * Id de la cat�gorie
	  *
	  * @access private
	  * @name id 
	  * @var int 
	  *
	  */
	
	private int id;
	
	/**
	  * Nom de la cat�gorie
	  *
	  * @access private
	  * @name name 
	  * @var String
	  *
	  */
	
	private String name;
	
	/**
	  * Les aliments de notre cat�gorie
	  * 
	  * 
	  * @access private 
	  * @name foods
	  * @var ArrayList<Food>
	  * 
	  */
	
	
	private ArrayList<Food> foods = new ArrayList<Food>();

	/**
	  * Constructeur hyper light 
	  * 
	  * @access public
	  * @name Category
	  */
	
	public Category( ) {		
		
	}
	
	/**
	  * Constructeur light 
	  * 
	  * @access public
	  * @name Category
	  * @param String name
	  */
	
	public Category( String name) {		
		this.setName(name);
	}
	
	/**
	  * Constructeur complet 
	  * 
	  * @name Category
	  * @param int id : id de la cat�gorie
	  * @param String name : nom de la cat�gorie
	  */
	
	public Category(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	
	
	public ArrayList<Food> getFoods() {
		return foods;
	}

	public void addFood(Food food) {
		this.foods.add(food);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	  * Tente de trouver un cat�gorie via un id
	  * 
	  * @access public
	  * @name find
	  * @param id
	  * @return boolean : indique si la cat�gory a �t� trouv�e
	  * 
	  */
	
	
	public boolean find(int id) {	
		
		Connection conn = SqlQuery.getConnection();
		
		if(conn == null) { return false; }
		
		try {
			
			conn.setAutoCommit(false);
			
			PreparedStatement statement = conn.prepareStatement(
					"SELECT "
					+ "	id		,	"
					+ " name		"
					+ " FROM category"
					+ " WHERE id = ? "
					);
			statement.setInt(1, id);			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				this.setId(result.getInt("id"));
				this.setName(result.getString("name"));
			
			
			 statement = conn.prepareStatement(
					"SELECT "
					+ "	id		,	"
					+ "	name	,	"
					+ "	protein	,	"
					+ "	glucid	,	"
					+ "	lipid	,	"
					+ " id_category	"
					+ " FROM foods 	"
					+ "WHERE id_category = ? "
					);
			 statement.setInt(1, id);
			
			 ResultSet result2 = statement.executeQuery();
				
				while(result2.next()) {
					
					Food food = new Food(
											result2.getInt("id_category") ,
											result2.getString("name"),
											result2.getInt("protein"),
											result2.getInt("glucid"),
											result2.getInt("lipid")
											
										);
					this.addFood(food);
					food.setCategory(this);
					
				}
				conn.commit();
				return true;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
		}
		return false;
		
	}
	
	/**
	 * Tente de cr�er un cat�gorie
	 * 
	 * @access public
	 * @name create
	 * @return boolean : indique si tout s'est bien d�roul�
	 *
	 */
	
	public boolean create() {
		
		Connection conn = SqlQuery.getConnection();
		
		if(conn == null) { return false ; }
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement("INSERT INTO public.category(name) VALUES ( ?);");
			statement.setString(1,this.getName());
			statement.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {			
			e.printStackTrace();			
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			return false; 
		}
		return true;
	}
	
	/**
	 * Tente de mettre � jour une cat�gorie
	 * 
	 * @access public
	 * @name update
	 * @return boolean : indique si tout s'est bien d�roul�
	 *
	 */
	
	public boolean update() {
		
		
		Connection conn = SqlQuery.getConnection();
		
		if(conn == null) { return false; }
			
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement("UPDATE category SET name= ? WHERE id = ?");
			statement.setString(1,this.getName());
			statement.setInt(2,(int) this.getId());
			
			statement.executeUpdate();	
			
			conn.commit();
			
		} catch (SQLException e) {	
			
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tente de supprimer une cat�gorie
	 * 
	 * @access public
	 * @name delete
	 * @return boolean : indique si tout s'est bien d�roul�
	 *
	 */
	
	public boolean delete() {
		
		Connection conn = SqlQuery.getConnection();
		
		if(conn == null) { return false ; }
		
		try {
			
			conn.setAutoCommit(false);
			
			PreparedStatement statement = conn.prepareStatement("DELETE FROM category WHERE id = ?");
			statement.setInt(1,this.getId());
			statement.executeUpdate();	
			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			
			return false;
			
		}
		
		return true;
	}
	
	/**	   
	  * Liste les cat�gories
	  *
	  * @access public static
	  * @name list
	  * @return ArrayList<Category> : Les cat�gories
	  *
	  */
	
	public static ArrayList<Category> list() {
		
		ArrayList<Category> listCategory = new ArrayList<Category>();
		
		Connection conn = SqlQuery.getConnection();
		
		if(conn == null) { return listCategory; }
		
		try {		
			
			conn.setAutoCommit(false);
			
			PreparedStatement statement = conn.prepareStatement(
					"SELECT "
					+ "id	,"
					+ "name"					
					+ " FROM category "					
					);
			
			ResultSet result = statement.executeQuery();
			
			for(boolean res = result.next() ; res ;res = result.next()) {
				
				Category category = new Category(result.getInt("id"), result.getString("name"));
 				
				statement = conn.prepareStatement(
						"SELECT "
						+ "c.id as idc		,"
						+ "c.name as namec	,"
						+ "f.id as idf		,"
						+ "f.name as namef	,"
						+ "f.protein 		,"
						+ "f.glucid			,"
						+ "f.lipid			"
						+ " FROM category c "
						+ " INNER JOIN foods f ON f.id_category = c.id "
						+ " WHERE c.id = ?"
						);
				statement.setLong(1, category.getId());
				
				ResultSet resultFoods = statement.executeQuery();
				
				while(resultFoods.next()) {
					Food food = new Food(
											resultFoods.getInt("idf")	, 
											resultFoods.getString("namef")	,	
											resultFoods.getInt("protein"),
											resultFoods.getInt("glucid")	,
											resultFoods.getInt("lipid")
											);
					category.addFood(food);
				}
				
				
				listCategory.add(category);
				
			}
			
			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();			
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
		}
		
		return listCategory;
		
	}
	
	/**	   
	  * Affiche la cat�gorie
	  *
	  * @access public static
	  * @name toString
	  * @return String : pour un affichage
	  *
	  */
	
	
	
	public String toString() {
		
		String str = "";
		
		String[] tabString =  {
									String.valueOf(this.getId())	,
									this.getName()
							};
		
		int lengthCol = 14;
		
		for(int i = 0 ; i < (lengthCol + 2) * tabString.length ; i++) {
			str += "-";
		}
		
		str += "\n";		
		
		for(String string : tabString) {			
			
			str += "|"+String.format("%-14s", string)+"|";
		}
		
		str += "\n";
				
		for(int i = 0 ; i < (lengthCol + 2) * tabString.length ; i++) {
			str += "-";
		}
		
		str += "\n";
		
		return str;


		
	}
}
