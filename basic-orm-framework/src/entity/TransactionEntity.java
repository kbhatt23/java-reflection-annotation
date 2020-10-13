package entity;

import annotations.ColumnAnnotation;
import annotations.PrimaryKeyAnnotation;
import annotations.TableAnnotation;

//@TableAnnotation(tableName = "transaction_table")
@TableAnnotation
public class TransactionEntity {
	//@PrimaryKeyAnnotation
	@PrimaryKeyAnnotation(columnName = "transaction_id")
	private int id;
	
	//@ColumnAnnotation
	@ColumnAnnotation(columnName = "transaction_name")
	private String name;
	
	@ColumnAnnotation
	//@ColumnAnnotation(columnName = "transaction_description")
	private String description;

	@Override
	public String toString() {
		return "TransactionEntity [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	

	public TransactionEntity() {
		super();
	}



	public TransactionEntity(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
