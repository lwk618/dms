package dms.db.dao;

import java.util.List;

public interface DAOInterface<T> {
	public T get(int id);
	public List<T> query();
	public boolean insert(T bean);
	public boolean update(T bean);
	public boolean delete(T bean);
	public boolean exist(T bean);
}
