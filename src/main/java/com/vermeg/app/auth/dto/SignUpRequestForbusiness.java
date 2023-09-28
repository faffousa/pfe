package com.vermeg.app.auth.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class SignUpRequestForbusiness {
	@NotBlank
	@Pattern(regexp="^[A-Za-z][A-Za-z0-9]+$",message="companyName must not begin with a number nor contain special caracters") 
    @Size(min = 3, max = 20)
	@NotEmpty
    private String companyName;
 /*9
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;*/

    /*
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;*/
    
   /* private boolean active;
    
    private boolean enabled;*/
	@NotEmpty
	@NotBlank
    private String website;
	@NotEmpty
    private String nbEmployee;
	@NotEmpty
    private String firstName;
	@NotEmpty
    private String lastName;

	@NotEmpty
    private String recrutementRole;
	
	@NotEmpty
   
    private String phone;
	@NotEmpty
    private String industry;
	@NotEmpty
    private String country;
 
	@NotEmpty
    private String address;
    
	@NotEmpty
    private String logo;
    
	private boolean verified;

	@NotEmpty
    private String description;


    
    private Date creationDate;


  
    public String getCompanyName() {
        return companyName;
    }
 
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
 /*
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }*/


    
   /* 

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
*/
	public String getWebsite() {
		return website;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNbEmployee() {
		return nbEmployee;
	}

	public void setNbEmployee(String nbEmployee) {
		this.nbEmployee = nbEmployee;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRecrutementRole() {
		return recrutementRole;
	}

	public void setRecrutementRole(String recrutementRole) {
		this.recrutementRole = recrutementRole;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}





	
	
	
	
	
	
	public SignUpRequestForbusiness() {
		super();
	}

	public SignUpRequestForbusiness(
			@NotBlank @Pattern(regexp = "^[A-Za-z][A-Za-z0-9]+$", message = "displayName must not begin with a number nor contain special caracters") @Size(min = 3, max = 20) @NotEmpty String companyName,
			@NotEmpty String website, @NotEmpty String nbEmployee, @NotEmpty String firstName,
			@NotEmpty String lastName, @NotEmpty String recrutementRole, @NotEmpty String phone,
			@NotEmpty String industry, @NotEmpty String country, @NotEmpty String address, @NotEmpty String logo,
			@NotEmpty String description) {
		super();
		this.companyName = companyName;
		this.website = website;
		this.nbEmployee = nbEmployee;
		this.firstName = firstName;
		this.lastName = lastName;
		this.recrutementRole = recrutementRole;
		this.phone = phone;
		this.industry = industry;
		this.country = country;
		this.address = address;
		this.logo = logo;
		this.description = description;
	}


/*
	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {
		private String companyName;
		private String website;
		private String nbEmployee;
		private String firstName;
		private String lastName;
		private String recrutementRole;
		private String phone;
		private String industry;
		private String country;
		private String address;
		
		private String logo;
		private String description;

		public Builder addcompanyName(final String companyName) {
			this.companyName = companyName;
			return this;
		}

	

		public Builder addWebsite(final String website) {
			this.website = website;
			return this;
		}
		public Builder addNbEmployee(final String nbEmployee) {
			this.nbEmployee = nbEmployee;
			return this;
		}
		public Builder addFirstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder addLastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}
		public Builder addphone(final String phone) {
			this.phone = phone;
			return this;
		}
		public Builder addIndustry(final String industry) {
			this.industry = industry;
			return this;
		}
		public Builder addCountry(final String country) {
			this.country = country;
			return this;
		}
		public Builder addAddress(final String address) {
			this.address = address;
			return this;
		}
		public Builder addLogo(final String logo) {
			this.logo = logo;
			return this;
		}
		public Builder addDescription(final String description) {
			this.description = description;
			return this;
		}
		public SignUpRequestForbusiness build() {
			//return new SignUpRequest(providerUserID,displayName,email,socialProvider,password,roles);
			return new SignUpRequestForbusiness(companyName,  website,  nbEmployee,  firstName,  lastName,
					 recrutementRole,  phone,  industry,  country,  address,  logo,
					 description);
		
		}
	}*/
    
    
  
}
