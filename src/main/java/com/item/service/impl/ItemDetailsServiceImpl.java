package com.item.service.impl;

import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;
import javax.sql.DataSource;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDetailsServiceImpl implements ItemDetailsService{
	
	private DataSource dataSource;

    public ItemDetailsServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean hasItemDetails(int itemId) {
        String sql = "SELECT COUNT(*) FROM item_details WHERE item_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // لو فيه أي نتيجة، معناه موجود
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveItemDetails(ItemDetails itemDetails) {
        String sql = "INSERT INTO item_details (description, item_id) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, itemDetails.getDescription());
            stmt.setInt(2, itemDetails.getItemId());
            
            int result = stmt.executeUpdate();
            return result == 1; // لو أضاف صف واحد، يبقى نجح
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ItemDetails getItemDetailsByItemId(int itemId) {
        String sql = "SELECT * FROM item_details WHERE item_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                ItemDetails details = new ItemDetails();
                details.setId(rs.getInt("id"));
                details.setDescription(rs.getString("description"));
                details.setItemId(rs.getInt("item_id"));
                return details;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // لو ملقناش تفاصيل
    }
}
