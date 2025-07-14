package com.dao;

import java.util.List;

import com.model.Menu;

public interface MenuDAO {

	void addMenu(Menu menu);

	Menu getMenu(int menuId);
	List<Menu> getAllMenuByRestId(int restId);
	List<Menu> getAllMenu();

	void updateMenu(Menu menu);

	void deleteMenu(int  menuId);
}
