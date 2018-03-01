package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;

public class SequenceEcritureComptableRM implements RowMapper<SequenceEcritureComptable> {
	
	@Override
	public SequenceEcritureComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		SequenceEcritureComptable vSequenceEcritureComptable = new SequenceEcritureComptable();
		vSequenceEcritureComptable.setAnnee(pRS.getInt("annee"));
		vSequenceEcritureComptable.setDerniereValeur(pRS.getInt("derniere_valeur"));
		return vSequenceEcritureComptable;
	}

}
