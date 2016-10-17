package com.amanda.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.amanda.domain.Servico;
import com.amanda.domain.ServicoFilter;


import com.amanda.dao.HibernateGenericDAO;


@SuppressWarnings("serial")
public class ServicoDAO extends HibernateGenericDAO<Servico, Long> implements Serializable  {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Servico> filtrar(ServicoFilter filtro){
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Servico.class);
		
		if(filtro.getDataInicial() != null && filtro.getDataFinal() != null){
			
			criteria.add(Restrictions.ge("data", filtro.getDataInicial()))
					.add(Restrictions.le("data", filtro.getDataFinal()));
				
			
		}
		
		return criteria.setCacheable(true).list() ;
	}
	
	
public BigDecimal somarTotal(ServicoFilter sfiltro){
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Servico.class);
		
		if(sfiltro.getDataInicial() == null && sfiltro.getDataFinal() == null){
			
			criteria.setProjection(Projections.sum("valor"));
			
			return (BigDecimal) criteria.uniqueResult();
			
		}
		
		criteria.setProjection(Projections.sum("valor"))
				.add(Restrictions.ge("data", sfiltro.getDataInicial()))
				.add(Restrictions.le("data", sfiltro.getDataFinal()));
		
		return (BigDecimal)criteria.uniqueResult();
		
	}	
		
	

}
