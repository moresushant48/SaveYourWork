package io.moresushant48.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/*
 * Model Class for Roles
 */
@Entity
@Table(name = "file_access_modes")
public class Access {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "access_id")
	private long id;
	
	@Column(name = "access_name")
	private String access;
	
	@OneToMany(mappedBy = "access")
	private List<File> files = new ArrayList<File>();
	
	public Access() {}
	
	public Access(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	@JsonBackReference
	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}	
}
