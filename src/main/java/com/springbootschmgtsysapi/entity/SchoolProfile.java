package com.springbootschmgtsysapi.entity;
import javax.persistence.*;
import javax.validation.constraints.Size;
@Entity
@Table(name = "schoolprofile")
public class SchoolProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Size(max = 70, message = "{validation.name.size.too_long}")
	private String schoolName;
	@Size(max = 200, message = "{validation.name.size.too_long}")
	private String schoolAddress;
	@Size(max = 50, message = "{validation.name.size.too_long}")
	private String telephone;
	@Size(max = 100, message = "{validation.name.size.too_long}")
    private String poAddress;
    @Size(max = 50, message = "{validation.name.size.too_long}")
    private String emailAddress;
    @Size(max = 70, message = "{validation.name.size.too_long}")
    private String website;
    @Size(max = 40, message = "{validation.name.size.too_long}")
    private String schoolLogoString;
    @Size(max = 70, message = "{validation.name.size.too_long}")
    private String motto;
}
