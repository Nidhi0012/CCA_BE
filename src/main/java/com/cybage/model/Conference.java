package com.cybage.model;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
	
	
	@NotEmpty
	@Size(min = 2, max = 40, message="Placename should be minimum of 2 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_. -]+$")
    private String place;
	

    private Date date;
    
    @NotEmpty
    @Size(min = 2, max = 40, message="Name should be minimum of 2 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_. -]+$")
	private String name;
    
	@NotEmpty
    private String status;
    
    
    @NotEmpty
    @Pattern(regexp = "(https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)?[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})(\\.[a-zA-Z0-9]{2,})?", message="Enter a valid Link")
	private String link;
    
   
 }
