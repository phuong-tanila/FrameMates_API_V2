package fu.training.FrameMates_BE.mediafile;

import lombok.Data;

@Data
public class MediaFileModel {

    private Integer fileId;

    private String filePath;

    private String title;

    private String description;

    private java.sql.Timestamp uploadDate;

    private Integer view;


//    private CustomerModel customer;
//
//    private StudioModel studio;


}
