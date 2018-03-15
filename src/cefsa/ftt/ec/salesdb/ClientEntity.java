package cefsa.ftt.ec.salesdb;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientEntity {

    //ID;Name;Index;DoB;Hire Date; Credit ;Days;ID Company;ID Occupation;

    private int id;
    private String name;
    private float index;
    private Date dob; //Day Of Birth
    private Date hireDate;
    private float credit;
    private int days,
                idCompany,
                idOccupation;

    String[] columnNames = {"ID",
    		                "Name",
    		                "Index",
    		                "DoB",
    		                "Hire Date",
    		                "Credit",
    		                "Days",
    		                "Company",
    		                "Occupation"};
    
    // Construtores
    
    ClientEntity() {
    	super();
    } //ClientEntity
    
    //ID	Name	Index	DoB	Hire Date	 Credit 	Days	ID Company	ID Occupation
    ClientEntity(String id, 
    		     String name, 
    		     String index, 
    		     String dob, 
    		     String hireDate, 
    		     String credit, 
    		     String days, 
    		     String idCompany, 
    		     String idOccupation) {
    	
    	super();
    	
    	this.setId(id);
    	this.setName(name);
    	this.setIndex(index);
    	this.setDob(dob);
    	this.setHireDate(hireDate);
    	this.setCredit(credit);
    	this.setDays(days);
    	this.setIdCompany(idCompany);
    	this.setIdOccupation(idOccupation);
    	
    } //ClientEntity
    
    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setId(String id) {
        this.setId(Integer.parseInt(id));
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the index
     */
    public float getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(float index) {
        this.index = index;
    }
    
    public void setIndex(String index) {
    	
        setIndex(Float.parseFloat(index));
        
    }
    /**
     * @return the dob
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }


	public Date toDate(String dateString) {
        //Mask: 24/3/1983  05:02

        try {

            Date dataFormat = new SimpleDateFormat("dd/MM/yyyy KK:mm").parse(dateString);

            return dataFormat;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } //try

	} //toDate


    public void setDob(String dob) {

        this.setDob(toDate(dob));

    } //setDob

    /**
     * @return the hireDate
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate the hireDate to set
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setHireDate(String hireDate) {
        this.setHireDate(toDate(hireDate));
    }

    /**
     * @return the credit
     */
    public float getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(float credit) {
        this.credit = credit;
    } //setCredit

    public void setCredit(String credit) {
        setCredit(Float.parseFloat(credit));
    } //setCredit

    /**
     * @return the days
     */
    public int getDays() {
        return days;
    } //getDays
    
    /**
     * @param days the days to set
     */
    public void setDays(int days) {
        this.days = days;
    } //setDays

    public void setDays(String days) {
        setDays(Integer.parseInt(days));
    } //setDays    
    
    /**
     * @return the idCompany
     */
    public int getIdCompany() {
        return idCompany;
    }

    /**
     * @param idCompany the idCompany to set
     */
    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    } //setIdCompany

    public void setIdCompany(String idCompany) {
        setIdCompany(Integer.parseInt(idCompany));
    } //setIdCompany    
    
    /**
     * @return the idOccupation
     */
    public int getIdOccupation() {
        return idOccupation;
    }

    /**
     * @param idOccupation the idOccupation to set
     */
    public void setIdOccupation(int idOccupation) {
        this.idOccupation = idOccupation;
    } //setIdOccupation
    
    public void setIdOccupation(String idOccupation) {
        setIdOccupation(Integer.parseInt(idOccupation));
    }  //setIdOccupation  
    
    public String[] getColumnNames() {
    	return this.columnNames;
    }
    
    

} //clientEntity
