package cefsa.ftt.ec.salesdb;

public class ProductEntity {
	
	private int ID;
	private String partNumber,
	               description,
	               size,
	               status;
	private float value;
	
	private String[] columnNames = {"ID",
			                        "Part Number",
			                        "Description",
			                        "Size",
			                        "Value"};
	
	
	//Constructors
	ProductEntity(){
		super();
	}
	
	ProductEntity(String iD, 
	              String partNumber, 
	              String description, 
	              String size, 
	              String status, 
	              String value) {
		
		super();
		
		this.setID(iD);
		this.setPartNumber(partNumber);
		this.setDescription(description);
		this.setSize(size);
		this.setStatus(status);
		this.setValue(value);
		
	}
	
	//geters & setters
	
	public int getID() {
		return ID;
	}
	public void setID(String iD) {
		this.ID = Integer.valueOf(iD);
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public void setValue(String value) {
		this.value = Float.valueOf(value);
	}
    public String[] getColumnNames() {
    	return this.columnNames;
    }

} //ProductEntity
