package com.item.service;

import com.item.model.ItemDetails;

public interface ItemDetailsService {
	
boolean hasItemDetails(int itemId);
    
    boolean saveItemDetails(ItemDetails itemDetails);
    
    ItemDetails getItemDetailsByItemId(int itemId);
	
}
