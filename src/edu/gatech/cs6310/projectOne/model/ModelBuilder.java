package edu.gatech.cs6310.projectOne.model;

import java.util.List;

public abstract class ModelBuilder<T extends Model> {
	
	private List<T> list; 
	
	public List<T> list(){
		if(list == null){
			list = buildModel();
		}
		return list;
	}
	
	public T findById(int id){
		return findById(id, list());
	}
	
	protected T findById(int id, List<T> list){
		for(T item : list){
			if(item.getId() == id){
				return item;
			}
		}
		return null;
	}
	
	protected abstract List<T> buildModel();

}
