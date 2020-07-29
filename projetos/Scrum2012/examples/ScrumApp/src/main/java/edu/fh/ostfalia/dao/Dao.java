package edu.fh.ostfalia.dao;

import java.util.List;

public interface Dao<T> {
	public List<T> list();
	public T findById(Long id);
	public T save(T entity);
}
