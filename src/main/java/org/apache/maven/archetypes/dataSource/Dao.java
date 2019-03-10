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
	public List<TouristAttraction> queryTouristAttraction(int page,int category_id){
		
	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<TouristAttraction> touristList = new ArrayList<TouristAttraction>();
		try{
			conn = dataSource.getConnection();
			String querySql = null;
			if (category_id == 0){
				querySql = "SELECT * FROM touristattractions ORDER BY id DESC LIMIT ?,?";
				stmt = conn.prepareStatement(querySql);
				stmt.setInt(1,(page -1) * 10);
				stmt.setInt(2,10);
			} else{
				querySql = "SELECT * FROM touristattractions WHERE category_id = ? ORDER BY id DESC LIMIT ?,?";
				stmt = conn.prepareStatement(querySql);
				stmt.setInt(1,category_id);
				stmt.setInt(2,(page -1) * 10);
				stmt.setInt(3,10);
			}
		
			rs=stmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("id"));
				TouristAttraction tourist = new TouristAttraction(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getString("coverImage"));
				 touristList.add(tourist);
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
