package com.vermeg.app.auth.entity;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The persistent class for the user database table.
 * 
 */
@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 65981149772133526L;
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";

	
	@Column(name = "USER_ID")
	@Id
	private Long id;

	@Column(name = "PROVIDER_USER_ID")
	private String providerUserId;

	@NotEmpty(message = "the email is mandatory")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@vermeg\\.com$", message = "email address must end with @vermeg.com")
	private String email;



	@Column(name = "enabled", columnDefinition = "BIT", length = 1)
	private boolean enabled;

	@Column(name = "DISPLAY_NAME")
	@NotEmpty(message="the display name is mandatory")
	@Pattern(regexp="^[A-Za-z][A-Za-z0-9]+$",message="displayName must not begin with a number nor contain special caracters")
	private String displayName;

	@Column(name = "created_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;

	@Size(min = 6, message = "{Size.userDto.password}")
	private String password;

	private String provider;

	private String resetPasswordToken;

	// bidirectional many-to-many association to Role
	//@DBRef
	  private Set<Role> roles = new HashSet<>();
	private String badge;
	@Field("nbrRep")
		private int nbrRep = 0;


	private List<Long> favoriteQuestions = new ArrayList<>();
	public User(Long id, String providerUserId, String email, boolean enabled, String displayName, Date createdDate,
			Date modifiedDate, String password, String provider, Set<Role> roles , int nbrRep, String badge ) {
		super();
		this.id = id;
		this.providerUserId = providerUserId;
		this.email = email;
		this.enabled = enabled;
		this.displayName = displayName;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.password = password;
		this.provider = provider;
		this.roles = roles;
		this.badge = badge;
		this.nbrRep = nbrRep;
	}


	public User() {
		super();
	}

	public User(Long id, String providerUserId, String email,
			boolean enabled, String displayName,Date createdDate,
			Date modifiedDate, String password, String provider, String resetPasswordToken, Set<Role> roles , String badge , int nbrRep) {
		super();
		this.id = id;
		this.providerUserId = providerUserId;
		this.email = email;
		this.enabled = enabled;
		this.displayName = displayName;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.password = password;
		this.provider = provider;
		this.resetPasswordToken = resetPasswordToken;
		this.roles = roles;
		this.badge = badge;
		this.nbrRep = nbrRep;
	}
}
