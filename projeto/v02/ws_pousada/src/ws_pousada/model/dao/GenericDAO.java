package ws_pousada.model.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.Table;

import static ws_pousada.model.FactoryDAO.sessionInstance;

public class GenericDAO<T, PK> {

	protected Class<?> manipulada;

	public GenericDAO() {
		super();
		this.manipulada = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	protected Class<?> getManipulada() {
		return manipulada;
	}

	protected void setManipulada(Class<?> manipulada) {
		this.manipulada = manipulada;
	}

	// CRUD

	public T save(T entity) {
		try {
			sessionInstance().beginTransaction();
//			sessionInstance().load(entity, ((BaseEntity)entity).getId());
			sessionInstance().persist(entity);
			sessionInstance().getTransaction().commit();

		} catch (Exception e) {
			sessionInstance().getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			// sessionInstance().close();
		}
		return entity;
	}

	public T update(T entity) {
		try {
			sessionInstance().beginTransaction();
			sessionInstance().merge(entity);
			sessionInstance().getTransaction().commit();

		} catch (Exception e) {
			sessionInstance().getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			// sessionInstance().close();
		}
		return entity;
	}

	public void delete(PK pk) {
		try {
			sessionInstance().beginTransaction();
			sessionInstance().remove(findById(pk));
			sessionInstance().getTransaction().commit();

			// return true;

		} catch (Exception e) {
			sessionInstance().getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			// sessionInstance().close();
		}

	}

	public Object executeQuery(String query, Object... params) {
		Query q = sessionInstance().createQuery(query);

		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}

		return q.getResultList();
	}
	
	public List<T> executeQuery(Map<String, String> params) {
		
		StringBuilder sql = null;
//		Table table = this.manipulada.getAnnotation(Table.class);
		
//		if(table != null && table.name() != null && !table.name().trim().equals("")) {
//			sql = new StringBuilder("SELECT * FROM " + table.name() + " t");
//			// ALTERAÇÃO DE t para * na linha acima 
//		} else {
		sql = new StringBuilder(" FROM " + this.manipulada.getSimpleName() + " ");
//		}

		boolean frst = true;
		for ( Map.Entry<String, String> entry : params.entrySet() ) {

			if ( !frst ) {
				sql.append("AND ");
			} else {
				sql.append("WHERE ");
				frst = false;
			}

			if( entry.getValue().getClass().getSimpleName().contains("Date") ) {
				// EX.: initDataNascimento
				if( entry.getKey().contains("init" ) ){ 
					sql.append( entry.getKey() + " >= " + "'" + entry.getValue() + "'" );
					entry.getKey().substring(4);
				} else {
					sql.append( entry.getKey() + " <= " + "'" + entry.getValue() + "'" );
				}
			} else {
				sql.append( entry.getKey() + " LIKE " + "'" + entry.getValue() + "'" );
			}
		}

//		Query q = sessionInstance().createQuery(sql.toString());
		return sessionInstance().createQuery(sql.toString()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return sessionInstance().createQuery((" FROM " + this.manipulada.getName())).getResultList();
	}

	@SuppressWarnings("unchecked")
	public T findById(PK pk) {
		T retorno;

		try {
			retorno = (T) sessionInstance().find(this.manipulada, pk);
		} catch (Exception e) {
			throw e;
		} finally {
		}

		return retorno;
	}

}