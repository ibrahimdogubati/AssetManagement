package com.vektorel.assetman.web.service.firma;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.assetman.web.entity.Firma;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;
import com.vektorel.assetman.web.utilities.ex.DbException;

@Service
public class FirmaService implements IDataService<Firma> {
	
	@Autowired
	private transient BaseDao baseDao;
	
	@Override
	public Firma save(Firma entity) throws DbException {
		return (Firma)baseDao.save(entity);
	}

	@Override
	public Firma update(Firma entity) throws DbException {
		return (Firma)baseDao.update(entity);
	}

	@Override
	public boolean delete(Firma entity) throws DbException {
		return baseDao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Firma> getAll() {
		return (List<Firma>)baseDao.getAll(Firma.class);
	}

	@Override
	public Firma getById(Long entityId) {
		return (Firma)baseDao.getById(entityId, Firma.class);
	}
	public void delete(Long id) throws DbException {
		try {
			delete(getById(id));
		} catch (DbException e) {
			e.printStackTrace();
		}		
	}

	@Transactional
	public PagingResult getAllByPaging(int first, int pageSize, SortOrder sortOrder, Map<String, Object> filters) {		
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Firma.class);
		
		if(filters.get("adi")!=null){
			criteria.add(Restrictions.ilike("adi",filters.get("adi").toString(),  MatchMode.ANYWHERE));
		}
		
		int totalResult = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		
		criteria.setProjection(null);	
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Firma> acompFirma(String term) {
		Session session = baseDao.getSession();
		Criteria criteria = session.createCriteria(Firma.class);
		criteria.add(Restrictions.ilike("tanim", term,MatchMode.ANYWHERE));
		return (criteria.list());
	}
}
