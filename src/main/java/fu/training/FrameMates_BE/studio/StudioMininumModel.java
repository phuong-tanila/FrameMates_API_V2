package fu.training.FrameMates_BE.studio;

import fu.training.FrameMates_BE.account.AccountModel;
import fu.training.FrameMates_BE.slotbooking.SlotBookingModel;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
@Data
public class StudioMininumModel {

    private Integer studioId;
    @Size(min=1, max=500)
    private String name;
    @Size(min=1, max=500)
    private String address;
    @Size(min=1, max=500)
    private String description;
    private Double totalRating;
    @Size(min=1, max=500)
    private String avatarStudio;
}
