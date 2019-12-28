package io.moresushant48.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_files")
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private Long id;

	@Column(name = "file_name")
    private String fileName;

	@Column(name = "file_type")
    private String fileType;
	
	@Column(name = "file_size")
	private String fileSize;
	
	@ManyToOne
	@JoinColumn(name = "access_id")
	private Access access;
	
	@ManyToOne
	@JoinColumn(name = "auth_user_id")
	private User user;
	
    public File() {

    }

    public File(String fileName, String fileType, String fileSize) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", fileSize=" + fileSize + "]";
	}
	
	
}
