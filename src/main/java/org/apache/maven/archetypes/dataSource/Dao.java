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
	public List<TouristAttraction> queryTouristAttraction(){
		String querySql = "SELECT * FROM touristattractions";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<TouristAttraction> touristList = new ArrayList();
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(querySql);
			rs = stmt.executeQuery();
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
}
