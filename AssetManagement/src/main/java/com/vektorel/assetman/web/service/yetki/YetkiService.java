package com.vektorel.assetman.web.service.yetki;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.vektorel.assetman.web.entity.Yetki;
import com.vektorel.assetman.web.service.BaseDao;
import com.vektorel.assetman.web.utilities.IDataService;
import com.vektorel.assetman.web.utilities.PagingResult;

public class YetkiService implements IDataService<Yetki> {
	BaseDao baseDao = new BaseDao();

	@Override
	public Yetki save(Yetki entity) {
		return (Yetki) baseDao.save(entity);
	}

	@Override
	public Yetki update(Yetki entity) {
		return (Yetki) baseDao.update(entity);
	}

	@Override
	public boolean delete(Yetki entity) {
		return baseDao.delete(entity);
	}

	@Override
	public List<Yetki> getAll() {
		return (List<Yetki>) baseDao.getAll(Yetki.class);
	}

	@Override
	public Yetki getById(Long entityId) {
		return (Yetki) baseDao.getById(entityId, Yetki.class);
	}

	public void delete(Long id) {
		delete(getById(id));
	}

	public PagingResult getAllByPaging(int first, int pageSize,
			SortOrder sortOrder, Map<String, Object> filters) {
		Session s = baseDao.getSession();
		Criteria criteria = s.createCriteria(Yetki.class);
		if (filters.get("adi") != null) {
			criteria.add(Restrictions.ilike("adi",filters.get("adi").toString(), MatchMode.ANYWHERE));
		}
		int totalResult = Integer.parseInt(criteria
				.setProjection(Projections.rowCount()).uniqueResult()
				.toString());

		criteria.setProjection(null);
		criteria.setMaxResults(pageSize);
		criteria.setFirstResult(first);
		criteria.addOrder(Order.desc("id"));
		return new PagingResult(criteria.list(), totalResult);
	}
}