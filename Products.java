package com.project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.bean.ProductDetails;
import com.project.util.DatabaseConnection;

public class Products 
{

		Scanner in = new Scanner(System.in);
		Connection c=null;
		PreparedStatement s=  null;
		 public Products() throws SQLException, ClassNotFoundException
		{
			c = DatabaseConnection.getDB();
			s = c.prepareStatement(" ");
		}
		public void createProductTable() throws SQLException
		{
			String create_query = "create table XBBNHHK_product_details(product_id varchar2(10),product_name varchar2(25),product_price number(6,2),quantity number,seller_id number,manufacturer_id number)";
//			PreparedStatement s = c.prepareStatement(create_query);
			s=c.prepareStatement(create_query);
			s.executeQuery();
			System.out.println("Table is created");
		}
		public void insert() throws SQLException 
		{
			System.out.println("Enter the product Detials");
			ProductDetails pd = new ProductDetails();
			System.out.println("Enter product_id");
			pd.setProduct_id(in.next());
			System.out.println("Enter product_name");
			pd.setProduct_name(in.next());
			System.out.println("Enter product_price");
			pd.setProduct_price(in.nextFloat());
			System.out.println("Enter quantity");
			pd.setQuantity(in.nextInt());
			System.out.println("Enter seller_id");
			pd.setSeller_id(in.nextInt());
			System.out.println("Enter manufacturer_id");
			pd.setManufacturer_id(in.nextInt());
			String query = "insert into XBBNHHK_product_details(product_id,product_name,product_price,quantity,seller_id,manufacturer_id) values (?,?,?,?,?,?)";
			s=c.prepareStatement(query);
			s.setString(1, pd.getProduct_id());
			s.setString(2, pd.getProduct_name());
			s.setFloat(3, pd.getProduct_price());
			s.setInt(4, pd.getQuantity());
			s.setInt(5, pd.getSeller_id());
			s.setInt(6, pd.getManufacturer_id());
			s.executeQuery();
			System.out.println("Values are inserted");
			
		}
		public void viewProducts() throws SQLException 
		{
			List<ResultSet> viewProducts = new ArrayList<ResultSet>();
			String query = "select * from XBBNHHK_product_details";
			System.out.println("List of products");
			s=c.prepareStatement(query);
			ResultSet rs = s.executeQuery();
			try
			{
				while(rs.next())
				{
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getFloat(3));
					System.out.println(rs.getInt(4));
					System.out.println(rs.getInt(5));
					System.out.println(rs.getInt(6));
					viewProducts.add(rs);
				}
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				rs.close();
				s.close();
				c.commit();
				c.close();
			}
		}
		public void deleteProduct() throws SQLException 
		{
			String query = "drop table XBBNHHK_product_details";
			s=c.prepareStatement(query);
			s.executeQuery();
			
		}
		
		


}
