package com.tnt.brewery.service.inventory;

import java.util.UUID;

public interface InventoryService {

	public Integer getOnHandInventory(UUID beerId);
}
