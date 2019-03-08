package org.apache.maven.archetypes.dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.maven.archetypes.theme.ThemeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ThemeDao {
	@Autowired
	private DataSource dataSource;
	public List<ThemeObject> queryTheme(){
		String sql = "SELECT id,title,imageUrl FROM theme LIMIT 0,4";
		List<ThemeObject> list = new ArrayList<ThemeObject>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				list.add(new ThemeObject(rs.getInt("id"),rs.getString("title"),rs.getString("imageUrl")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if (stmt != null) {
						stmt.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						if (conn != null){
							conn.close();
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}
}
