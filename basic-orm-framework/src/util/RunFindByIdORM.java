package util;

import entity.TransactionEntity;

public class RunFindByIdORM {
public static void main(String[] args) throws Exception {
	ORMUtil<TransactionEntity, Integer> ormUtil = new ORMUtil<TransactionEntity, Integer>();
	TransactionEntity findById = ormUtil.findById(TransactionEntity.class, 1212);
	System.out.println(findById);
}
}
