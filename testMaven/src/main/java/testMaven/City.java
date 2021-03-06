package testMaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class City {
	
	private Long id;
	
	private String name;
	
	private double longitude;
	
	private double latitude;
	
	public City(String name, double longitude, double latitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		
	}
	
	public City(Long id, String name, double longitude, double latitude) {
		this.id	=id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		
	}	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public static ArrayList<City> listCities() {				
		
		Connection connection = SqlQuery.getConnection();
		
		try {
			String sql = " SELECT "
					+ "	\"idCity\"			,"
					+ " \"nameCity\"		,"
					+ " \"longitudeCity\" 	,"
					+ " \"latitudeCity\"	"
					+ "	FROM	\"City\"	";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			
			ArrayList<City> cities = new ArrayList<City>();
			
			while (resultSet.next()) {
					Long id = resultSet.getLong("idCity");
					String name = resultSet.getString("nameCity");
					double longitude = resultSet.getDouble("longitudeCity");
					double latitude = resultSet.getDouble("latitudeCity");
					
					City myCity = new City(id,name,longitude,latitude);
					cities.add(myCity);
				}
			
				return cities;
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public boolean save() {
		
		if(this.id == null) {
			String sql = "INSERT INTO "
			+ "public.\"City\""
			+ "("
			
			+ "\"nameCity\"			,"
			+ "\"longitudeCity\"	,"
			+ "\"latitudeCity\""
			+ ") "
			+ "VALUES (?, ?, ?) RETURNING \"idCity\"";
			Connection connection = SqlQuery.getConnection();
			try {
				connection.setAutoCommit(false);
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, this.name );
				statement.setDouble(2, this.longitude);
				statement.setDouble(3, this.latitude);
				ResultSet res = statement.executeQuery();
				res.next();
				this.id = res.getLong("idCity");
				if(this.name.equals("waby")) {
					connection.commit();
				}else {
					connection.rollback();
				}
				
				System.out.println(this.id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else {
			System.out.println("Update");
			String sql = "UPDATE public.\"City\""
									
					+ "SET "
					+ "\"nameCity\" = ?		,	"
					+ "\"longitudeCity\" =?	,	"
					+ "\"latitudeCity\" = ?		"
					
					+ " WHERE \"idCity\" = ?";
					Connection connection = SqlQuery.getConnection();
					try {
						PreparedStatement statement = connection.prepareStatement(sql);
						statement.setString(1, this.name );
						statement.setDouble(2, this.longitude);
						statement.setDouble(3, this.latitude);
						statement.setLong(4, this.id);
						int res = statement.executeUpdate();
								
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		
		
		
		
		
		return true;
	}
	
	
}
