package com.anz.cam.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Role Bean.
 * @author Keerthi Gadde
 *
 */
@Entity
@Table(name = "app_role")
@Getter
@Setter
public class Role {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "description")
	private String description;
}
