package ws_pousada.model.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.Query;

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
		Query createQuery = sessionInstance().createQuery(query);

		for (int i = 0; i < params.length; i++) {
			createQuery.setParameter(i, params[i]);
		}

		return createQuery.getResultList();
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