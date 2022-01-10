package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVO;

// CRUD = Create / Read / Update / Delete
// VisitorDAO : 데이터베이스 연동을 전담 -> XXXDAO (Data Access Object)
public class MemberDAO {
	public boolean memberInsert(MemberVO vo) {
		boolean result = false;
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into members (id, password, name, phone) values (?, ?, ?, ?)")) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
		return result;
	}
	
	public List<MemberVO> memberAll() { // 회원 전체 추출
		Connection conn = MySQL.connect();
		List<MemberVO> mlist = new ArrayList<MemberVO>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"select id, password, name, phone from members");
			MemberVO vo = new MemberVO();
			while (rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				mlist.add(vo);
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
		return mlist;
	}

	public MemberVO memberOne(String id) { // 회원 하나 추출
		Connection conn = MySQL.connect();
		MemberVO vo = null;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select id, password, name, phone from members where id = " + id);
			if (rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
		return vo;
	}

	public boolean memberUpdate(MemberVO vo) {
		boolean result = false;
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn
				.prepareStatement("update members set password = ?,  name = ? , phone = ? where id = ?")) {
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getId());
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
		return result;
	}

	public boolean memberDelete(String id, String password) {
		boolean result = false;
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("delete from members where id = ? and password = ?")) {
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
		return result;
	}
}