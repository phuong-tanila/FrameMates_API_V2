package fu.training.FrameMates_BE.mediafile;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fu.training.FrameMates_BE.studio.Studio;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Transactional
@JsonSerialize
@Entity
@Table(name="media_file")
public class MediaFile implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="file_id")
	private int file_id;
	
	@Column(name="file_path")
	private String file_path;
	
	@Column(name="upload_date")
	private java.sql.Timestamp upload_date;
	
	@ManyToOne(targetEntity= Studio.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="studio_id", referencedColumnName="studio_id") })
	private Studio studio;
}
