package com.icc.common.model.baidu;

import java.io.Serializable;
import java.util.HashSet;

import com.icc.common.model.Location;

public class ResidenceModel implements Serializable {

	private static final long serialVersionUID = 9163527233450243510L;

	private String name;
	private Object location;
	
	public ResidenceModel() {
		// TODO Auto-generated constructor stub
	}
	public ResidenceModel(String name,Object l){
		this.name=name;
		this.location=l;
	}
	@Override 
    public boolean equals(Object obj) { 
        
        boolean flag = false; 
        
        if (null != obj && obj.getClass() == ResidenceModel.class) 
        { 
        	ResidenceModel a = (ResidenceModel)obj; 
            if (this.name.equals(a.name)) 
            { 
                flag = true; 
            } 
        } 
        
        return flag; 
    } 
    @Override 
    public int hashCode() { 
        
        return this.name.hashCode(); 
    } 

    @Override 
    public String toString() { 
        
        StringBuffer sb = new StringBuffer(); 
        sb.append("name=").append(name); 
        sb.append(" ").append("location=").append(location); 
        return sb.toString(); 
    } 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getLocation() {
		return location;
	}
	public void setLocation(Object location) {
		this.location = location;
	}
	
	public static void main(String[] args) {
		 HashSet<ResidenceModel> hs = new HashSet<ResidenceModel>(); 
	    
		 ResidenceModel a1 = new ResidenceModel("1",""); 
		 ResidenceModel a2 = new ResidenceModel("1",""); 
		 ResidenceModel a3 = new ResidenceModel("2",""); 
	        
	        hs.add(a1); 
	        hs.add(a2); 
	        hs.add(a3); 
	        
	        System.out.println(hs);
	}
}
