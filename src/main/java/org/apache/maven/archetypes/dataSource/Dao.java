package org.apache.maven.archetypes.dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.maven.archetypes.tourist.TouristAttraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dao {
	@Autowired
	private DataSource dataSource;
	public List<TouristAttraction> queryTouristAttraction(int page){
		String querySql = "SELECT * FROM touristattractions ORDER BY id LIMIT ?,?";
		String queryCount = "SELECT COUNT(id) AS count FROM touristattractions;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn2 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		
		List<TouristAttraction> touristList = new ArrayList<TouristAttraction>();
		int totalCount;
		try{
			conn = dataSource.getConnection();
			conn2 = dataSource.getConnection();
			stmt = conn.prepareStatement(querySql);
			stmt2 = conn2.prepareStatement(queryCount);
			stmt.setInt(1,(page -1) * 10);
			stmt.setInt(2,10);
			rs = stmt.executeQuery();
			rs2 = stmt2.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("id"));
				TouristAttraction tourist = new TouristAttraction(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getString("coverImage"));
				 touristList.add(tourist);
			}
			while(rs2.next()){
				totalCount = rs2.findColumn("count");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(rs2 != null){
					rs2.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(stmt != null){
						stmt.close();
					}
					if(stmt2 != null){
						stmt2.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						if(conn != null){
							conn.close();
						}
						if(conn2 != null){
							conn2.close();
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}
	
		return touristList;
	}
	public int queryCount(){
		String queryCount = "SELECT COUNT(id) AS count FROM touristattractions;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(queryCount);
			rs = stmt.executeQuery();
			while(rs.next()){
				totalCount = rs.getInt("count");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(stmt != null){
						stmt.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						if(conn != null){
							conn.close();
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}
	
		return totalCount;
	}
	public TouristAttraction queryTouristDescription(int id){
		String sqlString = "SELECT id,name,description,coverImage FROM touristattractions WHERE id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TouristAttraction tourist = null; 
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sqlString);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()){
				tourist = new TouristAttraction(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getString("coverImage"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null) {
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if( stmt != null){
						stmt.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						if(conn != null){
							conn.close();
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
				
			}
		}
		return tourist;
	}
}
