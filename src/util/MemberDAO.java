package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Member;

public class MemberDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public void connect() throws NamingException, SQLException {
		Context context=new InitialContext();
		DataSource ds=(DataSource)context.lookup("java:comp/env/jdbc/meibo");
		con=ds.getConnection();
	}
	public void disconnect() throws SQLException {
		if(rs !=null) {
			rs.close();
		}
		if(ps !=null) {
			ps.close();
		}
		if(con !=null) {
			con.close();
		}
	}
	public void insertAll(String path) {
		BufferedReader br=null;
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr=new InputStreamReader(fis,"sjis");
			br=new BufferedReader(isr);
			this.connect();
			ps=con.prepareStatement("INSERT INTO members(name,kana,email,age) VALUES(?,?,?,?)");
			String line;
			while((line=br.readLine())!=null) {
				if(line.startsWith("名前")) {
					continue;
				}
				String[] vals=line.split(",",-1);
				ps.setString(1, vals[0]);
				ps.setString(2, vals[1]);
				ps.setString(3, vals[2]);
				ps.setInt(4, Integer.parseInt(vals[3]));
				ps.execute();
			}
		} catch (IOException  | NamingException | SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				if(br !=null) {
					br.close();
				}
				this.disconnect();
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println("done!");
		}
	}
	public void insertFromCSV(String path) {
		BufferedReader br=null;
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr=new InputStreamReader(fis,"sjis");
			br=new BufferedReader(isr);
			this.connect();
			ps=con.prepareStatement("TRUNCATE TABLE members");
			ps.execute();
			ps=con.prepareStatement("INSERT INTO members(name,kana,email,age) VALUES(?,?,?,?)");
			String line;
			while((line=br.readLine())!=null) {
				if(line.startsWith("名前")) {
					continue;
				}
				String[] vals=line.split(",",-1);
				ps.setString(1, vals[0]);
				ps.setString(2, vals[1]);
				ps.setString(3, vals[2]);
				ps.setInt(4, Integer.parseInt(vals[3]));
				ps.execute();
			}
		} catch (IOException | NamingException |SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br !=null) {
					br.close();
				}
				this.disconnect();
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println("done!");
		}
	}
	public List<Member> findAll(){
		List<Member> list=new ArrayList<>();
		try {
			this.connect();
			ps=con.prepareStatement("SELECT * FROM members");
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String kana=rs.getString("kana");
				String email=rs.getString("email");
				int age=rs.getInt("age");
				Member member=new Member(id,name,kana,email,age);
				list.add(member);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	public void insertOne(Member member) {
		try {
			this.connect();
			ps=con.prepareStatement("INSERT INTO members(name,kana,email,age) VALUES(?,?,?,?)");
			ps.setString(1, member.getName());
			ps.setString(2, member.getKana());
			ps.setString(3, member.getEmail());
			ps.setInt(4, member.getAge());
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				this.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public Member findOne(int id) {
		Member member=null;
		try {
			this.connect();
			ps=con.prepareStatement("SELECT * FROM members WHERE id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				String name=rs.getString("name");
				String kana=rs.getString("kana");
				String email=rs.getString("email");
				int age=rs.getInt("age");
				member=new Member(id,name,kana,email,age);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		return member;
	}
	public void updateOne(Member member) {
		try {
			this.connect();
			ps=con.prepareStatement("UPDATE members SET name=?,kana=?,email=?,age=? WHERE id=?");
			ps.setString(1, member.getName());
			ps.setString(2, member.getKana());
			ps.setString(3, member.getEmail());
			ps.setInt(4, member.getAge());
			ps.setInt(5, member.getId());
			ps.execute();

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				this.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void deleteOne(int id) {
		try {
			this.connect();
			ps=con.prepareStatement("DELETE FROM members WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				this.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void writeCSV(String path) {
		BufferedWriter bw=null;
		try {
			FileOutputStream fos=new FileOutputStream(path);
			OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
			bw=new BufferedWriter(osw);
			List<Member> list=this.findAll();
			for(Member m:list) {
				bw.append(m.getId()+","+m.getName()+","+m.getKana()+","+m.getEmail()+","+m.getAge());
				bw.newLine();
			}
		} catch (IOException  e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
