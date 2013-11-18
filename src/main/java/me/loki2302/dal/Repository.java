package me.loki2302.dal;

import java.util.ArrayList;
import java.util.List;


public class Repository<TEntity extends Entity> {
	private final List<TEntity> entities = new ArrayList<TEntity>();
	
	public void add(TEntity entity) {
		entities.add(entity);
	}
	
	public List<TEntity> getAll() {
		return entities;
	}
	
	public List<TEntity> getWhere(Query<TEntity> query) {
		List<TEntity> matchedEntities = new ArrayList<TEntity>();
		for(TEntity entity : entities) {
			if(query.match(entity)) {
				matchedEntities.add(entity);
			}
		}
		
		return matchedEntities;
	}
	
	public TEntity getOne(int id) {
		for(TEntity entity : entities) {
			if(entity.id == id) {
				return entity;
			}
		}
		
		return null;
	}
}