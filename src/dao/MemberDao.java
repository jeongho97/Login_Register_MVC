package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao {
	private static MemberDao dao=null;
	
	public MemberDao() {
		DBConnection.initConnection();
	}
	public static MemberDao getInstance() {
		if(dao==null) {
			dao=new MemberDao();
		}
		return dao;
	}
	public boolean addMember(MemberDto dto) {
		String sql=" INSERT INTO MEMBER2(ID,PWD,NAME,AGE,BIRTH,ADDRESS,HEIGHT)"
				+ " VALUES(?,?,?,?,?,?,?) ";
		Connection conn = null;
		PreparedStatement psmt=null;
		
		int count=0;
		
		try {
		conn=DBConnection.getConnection();
		System.out.println("1/3 addMember success");
		
		psmt=conn.prepareStatement(sql);
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getPwd());
		psmt.setString(3, dto.getName());
		psmt.setInt(4, dto.getAge());
		psmt.setString(5, dto.getBirth());
		psmt.setString(6, dto.getAddress());
		psmt.setInt(7, dto.getHeight());
		
		System.out.println("2/3 addMember success");
		
		count=psmt.executeUpdate();
		System.out.println("3/3 addMember success");
		
		}catch(SQLException e) {
			System.out.println("addMember fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	public boolean getId(String id) {
		String sql=" SELECT ID FROM MEMBER2 WHERE ID=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs=null;
		
		boolean findId=false;
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 checkID success");
			psmt=conn.prepareStatement(sql);
			System.out.println(id);
			psmt.setString(1,id);
			System.out.println("2/3 checkID success");
			rs=psmt.executeQuery();
			System.out.println("3/3 checkID success");
			if(rs.next()) {
				System.out.println(rs.next());
				findId=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("checkID fail");
			e.printStackTrace();
		}finally {
			   if(conn != null) {
		            try {
		               if(conn!= null) {
		                  conn.close();
		               }
		               if(psmt != null) {
		            	   psmt.close();
		               }
		               if(rs!=null) {
		            	   rs.close();
		               }
		            } catch (SQLException e) {
		               // TODO Auto-generated catch block
		               e.printStackTrace();
		            }
		         }
				}
		return findId;
	}
	public MemberDto login(MemberDto dto) {
		String sql=" SELECT ID,NAME,AGE,BIRTH,ADDRESS,HEIGHT FROM MEMBER2 WHERE ID=? AND PWD=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs=null;
		
		MemberDto mem = null;
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 login success");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1,dto.getId());
			psmt.setString(2, dto.getPwd());
			System.out.println("2/3 login success");
			rs=psmt.executeQuery();
			System.out.println("3/3 login success");
			if(rs.next()) {
				String id=rs.getString(1);
				String name=rs.getString(2);
				int age=rs.getInt(3);
				String birth=rs.getString(4);
				String address=rs.getString(5);
				int height=rs.getInt(6);
				
				mem=new MemberDto(id,null,name,age,birth,address,height);
				System.out.println(mem.toString());
			}
			System.out.println("login success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("login fail");
			e.printStackTrace();
		}finally {
			  DBClose.close(conn, psmt, rs);
				}
		return mem;
	}
	public MemberDto getinfo(MemberDto dto) {
		String sql=" SELECT ID,NAME,AGE,BIRTH,ADDRESS,HEIGHT FROM MEMBER2 WHERE ID=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs=null;
		
		MemberDto mem = null;
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getinfo success");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1,dto.getId());
			System.out.println("2/3 getinfo success");
			rs=psmt.executeQuery();
			System.out.println("3/3 getinfo success");
			if(rs.next()) {
				String id=rs.getString(1);
				String name=rs.getString(2);
				int age=rs.getInt(3);
				String birth=rs.getString(4);
				String address=rs.getString(5);
				int height=rs.getInt(6);
				
				mem=new MemberDto(id,null,name,age,birth,address,height);
				System.out.println(mem.toString());
			}
			System.out.println("getinfo success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getinfo fail");
			e.printStackTrace();
		}finally {
			  DBClose.close(conn, psmt, rs);
				}
		return mem;
	}
	public boolean delete(MemberDto dto) {
		String sql=" DELETE FROM MEMBER2 WHERE ID=? AND PWD=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		int count=0;
		
		MemberDto mem = null;
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 delete success");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1,dto.getId());
			psmt.setString(2,dto.getPwd());
			System.out.println("2/3 delete success");
			count=psmt.executeUpdate();
			System.out.println("3/3 delete success");
			System.out.println("count: "+count);
			System.out.println("delete success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getinfo fail");
			e.printStackTrace();
		}finally {
			  DBClose.close(conn, psmt, null);
				}
		return count>0?true:false;
	}

}
