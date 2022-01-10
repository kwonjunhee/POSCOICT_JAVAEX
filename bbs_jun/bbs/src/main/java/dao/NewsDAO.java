package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.NewsVO;

// CRUD = Create / Read / Update / Delete
// VisitorDAO : 데이터베이스 연동을 전담 -> XXXDAO (Data Access Object)
public class NewsDAO {

	public void newsInsert(NewsVO vo) {
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn.prepareStatement(
				"insert into news (title, writedate, content, "
				+ " writer, cnt) values (?, now(), ?, ?, 0)")) {
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
	}

	public List<NewsVO> newsListAll(int page) {
		Connection conn = MySQL.connect();
		List<NewsVO> nlist = null;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"select id, title, writer, date_format(writedate, '%Y-%m-%d') writedate, "
					+ " cnt from news order by writedate desc, id desc limit " +page+ ", 5 ");
			nlist = new ArrayList<NewsVO>();
			NewsVO vo = new NewsVO();
			while (rs.next()) {
				vo = new NewsVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writedate"));
				vo.setCnt(rs.getInt("cnt"));
				nlist.add(vo);
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
		return nlist;
	}

	public NewsVO newsListOne(int id) {
		Connection conn = MySQL.connect();
		NewsVO vo = null;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"select id, title, writer, date_format(writedate, '%Y년 %m월 %d일')"
					+ " writedate, content, cnt from news where id = " + id);
			if (rs.next()) {
				vo = new NewsVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writedate"));
				vo.setContent(rs.getString("content"));
				vo.setCnt(rs.getInt("cnt"));
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
		return vo;
	}

	public void newsUpdate(NewsVO vo) {
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("update news set title = ?, content = ? where id = ?")) {
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
	}

	public void newsDelete(int id) {
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("delete from news where id = ?")) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
	}

	public void newsUpdateCnt(NewsVO vo) {
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("update news set cnt=ifnull(cnt,0)+1 where id = ?")) {
			pstmt.setInt(1, vo.getId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
	}

	public List<NewsVO> getSearch(String searchField, String searchText) {
		Connection conn = MySQL.connect();
		ArrayList<NewsVO> list = new ArrayList<NewsVO>();
		String SQL = "select id, title, writer, date_format(writedate, '%Y-%m-%d') writedate, cnt, content from news WHERE "
				+ searchField.trim();
		try {
			if (searchText != null && !searchText.equals("")) {
				if (searchField.trim().equals("writer")) {
					SQL += " = '" + searchText.trim() + "' order by writedate desc, id desc";
				} else {
					SQL += " LIKE '%" + searchText.trim() + "%' order by writedate desc, id desc";
				}
			}
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			NewsVO vo = new NewsVO();
			while (rs.next()) {
				vo = new NewsVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writedate"));
				vo.setContent(rs.getString("content"));
				vo.setCnt(rs.getInt("cnt"));
				list.add(vo);
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQL.close(conn);
		return list;
	}
	public int getCount() {
		Connection conn = MySQL.connect();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select count(*) from news");
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}