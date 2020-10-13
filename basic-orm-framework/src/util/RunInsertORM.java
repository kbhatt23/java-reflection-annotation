package util;

import entity.TransactionEntity;
import excpetion.ORMException;

public class RunInsertORM {
	public static void main(String[] args) {

		TransactionEntity entity = new TransactionEntity("radha madhav", "jai radha madhav");
		
		ORMUtil<TransactionEntity, Integer> ormUtil = new ORMUtil<TransactionEntity, Integer>();
		try {

			ormUtil.insert(entity);
			ormUtil.insertEfficient(entity);
		} catch (IllegalArgumentException | IllegalAccessException | ORMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
