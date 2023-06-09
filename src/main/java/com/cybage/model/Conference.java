package com.cybage.model;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "conference")

	public class Conference {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer conferenceId;
	private String place;
	private Date date;
    private String name;
    private String status;
    private String link;
}
