package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import annotations.ColumnAnnotation;
import annotations.PrimaryKeyAnnotation;
import annotations.TableAnnotation;
import excpetion.ORMException;

public class ORMUtil<T, I> {

	public void insert(T t) throws ORMException, IllegalArgumentException, IllegalAccessException {
		// demonstrating just creation of sql
		Class<? extends Object> entityClass = t.getClass();
		// class level should have @table annotation forcefully ->
		// we can have default name as classname or provide name of table
		if (!entityClass.isAnnotationPresent(TableAnnotation.class)) {
			throw new ORMException(
					"Class " + entityClass.getSimpleName() + " should have TableAnnotation annotation to class level");
		}

		StringBuilder insertQueryBuilder = new StringBuilder();
		// table name -> annotation has to be present
		TableAnnotation annotation = entityClass.getAnnotation(TableAnnotation.class);
		String tableNameField = annotation.tableName();
		String tableName = tableNameField.equals("defaultTable") ? entityClass.getSimpleName() : tableNameField;
		insertQueryBuilder.append("insert into " + tableName + "('");

		// fetch all feilds private or public but only declared in this class
		Field[] declaredFields = entityClass.getDeclaredFields();
		boolean primaryfound = Arrays.stream(declaredFields)
				.anyMatch(field -> field.isAnnotationPresent(PrimaryKeyAnnotation.class));
		if (!primaryfound) {
			throw new ORMException(
					"Class " + entityClass.getSimpleName() + " should have atleat one primary key at field level");
		}
		StringBuilder valuesBuilder = new StringBuilder();
		valuesBuilder.append(" values('");
		int size = declaredFields.length;
		int index = 0;
		for (Field field : declaredFields) {
			// can be primary or secondary columns
			// assuming all the fields are by default columns
			field.setAccessible(true);
			boolean isLast = index == (size - 1);

			if (field.isAnnotationPresent(PrimaryKeyAnnotation.class)) {
				PrimaryKeyAnnotation primaryKeyAnnotation = field.getAnnotation(PrimaryKeyAnnotation.class);
				String columnName = primaryKeyAnnotation.columnName().equals("defaultPrimaryKey") ? field.getName()
						: primaryKeyAnnotation.columnName();
				insertQueryBuilder.append(columnName).append("',");
				// assuming all columns have int as priamry key
				valuesBuilder.append(new Random().nextInt()).append("',");
			} else if (field.isAnnotationPresent(ColumnAnnotation.class)) {
				ColumnAnnotation columnAnnotation = field.getAnnotation(ColumnAnnotation.class);
				String columnName = columnAnnotation.columnName().equals("defaultColumn") ? field.getName()
						: columnAnnotation.columnName();
				insertQueryBuilder.append("'").append(columnName).append("'");
				valuesBuilder.append("'").append(field.get(t)).append("'");
				if (isLast) {
					valuesBuilder.append(");");
					insertQueryBuilder.append(")");
				} else {
					valuesBuilder.append(",");
					insertQueryBuilder.append(",");
				}
			} else {
				// forcing column as variable/oinstance variable name
				String columnName = field.getName();
				insertQueryBuilder.append("'").append(columnName).append("'");
				valuesBuilder.append("'").append(field.get(t)).append("'");
				if (isLast) {
					valuesBuilder.append(");");
					insertQueryBuilder.append(")");
				} else {
					valuesBuilder.append(",");
					insertQueryBuilder.append(",");
				}
			}
			index++;
		}
		insertQueryBuilder.append(valuesBuilder);

		System.out.println("Query created " + insertQueryBuilder.toString());
		// insert into tableNAme('id','column1','column2')
	}

	public void insertEfficient(T t) throws ORMException, IllegalArgumentException, IllegalAccessException {
		// demonstrating just creation of sql
		Class<? extends Object> entityClass = t.getClass();
		// class level should have @table annotation forcefully ->
		// we can have default name as classname or provide name of table
		if (!entityClass.isAnnotationPresent(TableAnnotation.class)) {
			throw new ORMException(
					"Class " + entityClass.getSimpleName() + " should have TableAnnotation annotation to class level");
		}
		// fetch all feilds private or public but only declared in this class
		Field[] declaredFields = entityClass.getDeclaredFields();
		boolean primaryfound = Arrays.stream(declaredFields)
				.anyMatch(field -> field.isAnnotationPresent(PrimaryKeyAnnotation.class));
		if (!primaryfound) {
			throw new ORMException(
					"Class " + entityClass.getSimpleName() + " should have atleat one primary key at field level");
		}

		StringBuilder insertQueryBuilder = new StringBuilder();
		// table name -> annotation has to be present
		TableAnnotation annotation = entityClass.getAnnotation(TableAnnotation.class);
		String tableNameField = annotation.tableName();
		String tableName = tableNameField.equals("defaultTable") ? entityClass.getSimpleName() : tableNameField;
		insertQueryBuilder.append("insert into " + tableName);

		
		
		List<String> columnsSQL = new ArrayList<String>();
		List<String> valuesSQL = new ArrayList<String>();
		for (Field field : declaredFields) {
			// can be primary or secondary columns
			// assuming all the fields are by default columns
			field.setAccessible(true);
			String columnName = null;
			if (field.isAnnotationPresent(PrimaryKeyAnnotation.class)) {
				PrimaryKeyAnnotation primaryKeyAnnotation = field.getAnnotation(PrimaryKeyAnnotation.class);
				columnName = primaryKeyAnnotation.columnName().equals("defaultPrimaryKey") ? field.getName()
						: primaryKeyAnnotation.columnName();
				valuesSQL.add("'" + String.valueOf(new Random().nextInt()) + "'");
			} else if (field.isAnnotationPresent(ColumnAnnotation.class)) {
				ColumnAnnotation columnAnnotation = field.getAnnotation(ColumnAnnotation.class);
				columnName = columnAnnotation.columnName().equals("defaultColumn") ? field.getName()
						: columnAnnotation.columnName();
				valuesSQL.add("'" + field.get(t) + "'");
			} else {
				// forcing column as variable/oinstance variable name
				columnName = field.getName();
				valuesSQL.add("'" + field.get(t) + "'");
			}
			columnsSQL.add("'" + columnName + "'");
		}

		String sqlColumsnFullQuery = columnsSQL.stream().collect(Collectors.joining(",", "(", ")"));

		String sqlValuesFullQuery = valuesSQL.stream().collect(Collectors.joining(",", "(", ")"));
		insertQueryBuilder.append(sqlColumsnFullQuery).append(" values").append(sqlValuesFullQuery).append(";");
		System.out.println("Query created " + insertQueryBuilder.toString());
		// insert into tableNAme('id','column1','column2')
	}

	public List<T> findAll(Class<?> itemClass) throws Exception {
		if (!itemClass.isAnnotationPresent(TableAnnotation.class)) {
			throw new ORMException(
					"Class " + itemClass.getSimpleName() + " should have TableAnnotation annotation to class level");
		}
		// lets create sample query
		// select * from Tablename
		String tableName = itemClass.isAnnotationPresent(TableAnnotation.class)
				? ("defaultTable".equals(itemClass.getAnnotation(TableAnnotation.class).tableName())
						? itemClass.getSimpleName()
						: itemClass.getAnnotation(TableAnnotation.class).tableName())
				: itemClass.getSimpleName();
		String readQuery = "select * from " + tableName;
		// returning sample arraylist
		System.out.println("Query created " + readQuery);
		List<T> items = new ArrayList<>();

		// get the value from D.B
		// rows -> simulation
		List<Map<String, String>> rows = findResultListITems();

		// mappig using reflection
		// taking no arg constructor
		Constructor<?> noargConstructor = itemClass.getDeclaredConstructor();
		noargConstructor.setAccessible(true);

		Field[] declaredFields = itemClass.getDeclaredFields();
		for (Map<String, String> row : rows) {
			T newInstance = (T) noargConstructor.newInstance();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				String columnName = null;
				if (field.isAnnotationPresent(PrimaryKeyAnnotation.class)) {
					columnName = "defaultPrimaryKey"
							.equals(field.getAnnotation(PrimaryKeyAnnotation.class).columnName()) ? field.getName()
									: field.getAnnotation(PrimaryKeyAnnotation.class).columnName();
					field.set(newInstance, Integer.parseInt(row.get(columnName)));
				} else {
					columnName = field.isAnnotationPresent(ColumnAnnotation.class)
							? ("defaultColumn".equals(field.getAnnotation(ColumnAnnotation.class).columnName())
									? field.getName()
									: field.getAnnotation(ColumnAnnotation.class).columnName())
							: field.getName();
					field.set(newInstance, row.get(columnName));
				}
				// we support string and integers only

			}
			items.add(newInstance);
		}

		return items;
	}

	private List<Map<String, String>> findResultListITems() {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		Map<String, String> row1 = new HashMap<String, String>();
		row1.put("transaction_id", "12323");
		// row1.put("id", "12323");
		row1.put("transaction_name", "sita ram");
		//row1.put("name", "sita ram");
		//row1.put("transaction_description", "jai sita ram and ramdut hanuman");
		row1.put("description", "jai sita ram and ramdut hanuman");
		Map<String, String> row2 = new HashMap<String, String>();
		row2.put("transaction_id", "12324");
		// row2.put("id", "12324");
		row2.put("transaction_name", "lakshmi narayan");
		//row2.put("name", "lakshmi narayan");
		//row2.put("transaction_description", "jai shree lakshmi narayan");
		row2.put("description", "jai shree lakshmi narayan");
		rows.add(row1);
		rows.add(row2);
		return rows;
	}

	public T findById(Class<?> entityClass, int id) throws Exception {

		if (!entityClass.isAnnotationPresent(TableAnnotation.class)) {
			throw new ORMException(
					"Class " + entityClass.getSimpleName() + " should have TableAnnotation annotation to class level");
		}

		String primareColumnName = Arrays.stream(entityClass.getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(PrimaryKeyAnnotation.class)).map(field -> {
					return "defaultPrimaryKey".equals(field.getAnnotation(PrimaryKeyAnnotation.class).columnName())
							? field.getName()
							: field.getAnnotation(PrimaryKeyAnnotation.class).columnName();
				}).findFirst().orElseThrow(() -> new ORMException(
						"Class " + entityClass.getSimpleName() + " should have atleat one primary key at field level"));

		String tableName = entityClass.isAnnotationPresent(TableAnnotation.class)
				? ("defaultTable".equals(entityClass.getAnnotation(TableAnnotation.class).tableName())
						? entityClass.getSimpleName()
						: entityClass.getAnnotation(TableAnnotation.class).tableName())
				: entityClass.getSimpleName();

		String query = "select * from " + tableName + " where " + primareColumnName + " = "+id;
		System.out.println("Query created " + query);

		Map<String, String> rowFound = findItemFromResultSet();

		Constructor<?> noargConstructor = entityClass.getDeclaredConstructor();
		noargConstructor.setAccessible(true);
		T newInstance = (T) noargConstructor.newInstance();
		for (Field field : entityClass.getDeclaredFields()) {
			field.setAccessible(true);
			String columnName = null;
			if (field.isAnnotationPresent(PrimaryKeyAnnotation.class)) {
				columnName = "defaultPrimaryKey".equals(field.getAnnotation(PrimaryKeyAnnotation.class).columnName())
						? field.getName()
						: field.getAnnotation(PrimaryKeyAnnotation.class).columnName();
				field.set(newInstance, Integer.parseInt(rowFound.get(columnName)));
			} else {
				columnName = field.isAnnotationPresent(ColumnAnnotation.class)
						? ("defaultColumn".equals(field.getAnnotation(ColumnAnnotation.class).columnName())
								? field.getName()
								: field.getAnnotation(ColumnAnnotation.class).columnName())
						: field.getName();
				field.set(newInstance, rowFound.get(columnName));
			}
			// we support string and integers only

		}
		return newInstance;
	}

	private Map<String, String> findItemFromResultSet() {
		Map<String, String> rowFound = new HashMap<String, String>();
		rowFound.put("transaction_id", "12323");
		// row1.put("id", "12323");
		// row1.put("transaction_name", "sita ram");
		rowFound.put("transaction_name", "sita ram");
		rowFound.put("description", "jai sita ram and ramdut hanuman");
		return rowFound;
	}
}
