package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;

import fabric.ChaincodeManager;
import fabric.FabricConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import utils.DBUtils;
import utils.FabricManager;

public class LogAction extends Action {
	private ResultSet rs;

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		ChaincodeManager fm;
		try {
			fm = FabricManager.obtain().getManager();
			String args[] = { "A", "B", "2", "3" };
			try {
				fm.invoke("invoke", args);
			} catch (ProposalException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} catch (CryptoException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (TransactionException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		int result = isValid(user);
		if (result == 1) {
			session.setAttribute("userName", user.getUserName());

			session.setAttribute("user", user);
			request.getRequestDispatcher("projects.jsp").forward(request, response);
		} else {
			request.setAttribute("loginRes", "N");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	public int isValid(User user) {

		Connection conn = DBUtils.getConnection();
		int result = 0;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			this.rs = stmt.executeQuery("select *from user where userName='" + user.getUserName() + "'and password='"
					+ user.getPassword() + "'");
			System.out.print(rs.getString(1));
			if (this.rs.next())
				result = 1;
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DBUtils.close(this.rs, stmt, conn);
		}
		return result;

	}
}