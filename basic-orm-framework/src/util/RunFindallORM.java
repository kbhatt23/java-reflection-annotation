package util;

import java.util.List;

import entity.TransactionEntity;

public class RunFindallORM {
public static void main(String[] args) throws Exception {
	ORMUtil<TransactionEntity, Integer> ormUtil = new ORMUtil<TransactionEntity, Integer>();
	List<TransactionEntity> findAll = ormUtil.findAll(TransactionEntity.class);
	System.out.println(findAll);
}
}
