package com.item.model;

public class ItemDetails {
	
	private int id;
    private String description;
    private int itemId; 

    // Constructor
    public ItemDetails() {}
    
    public ItemDetails(String description, int itemId) {
        this.description = description;
        this.itemId = itemId;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

}
